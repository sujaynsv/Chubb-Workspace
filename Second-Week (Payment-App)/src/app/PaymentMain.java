package app;
import java.util.Scanner;

import models.*;
import exceptions.*;
import interfaces.*;

public class PaymentMain {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Payment[] stored = new Payment[20];
        int idx = 0;



        System.out.println("\nScenario: UPI with 500 (InvalidAmountException expected)");
        UPIPayment upiBad = new UPIPayment(-500, "Sujay", "Merchant1", "1234");
        try {
            upiBad.processPayment();
            stored[idx++]=upiBad;
        } catch (PaymentException e) {
//        	upiBad.retry();
            System.out.println("LOG: " + e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
        
        System.out.println("\n-----------\n");
        
        WalletPayment wallet = new WalletPayment(250, "Sujay", "Merchant2", 100.0);
        try {
            wallet.processPayment();
            stored[idx++] = wallet;
        } catch (InsufficientBalanceException e) {
            System.out.println("Wallet insufficient: " + e.getMessage());
            CreditCardPayment alt = new CreditCardPayment(250, "Sujay", "Merchant2", "41111112", false);
            try {
                alt.processPayment();
                stored[idx++] = alt;
            } catch (PaymentException ex) {
                System.out.println("Alternate failed: " + ex.getMessage());
            }
        } catch (PaymentException e) {
            System.out.println("Wallet error: " + e.getMessage());
        }


        System.out.println("\nScenario: NetBanking with wrong password (mask logs)");
        NetBankingPayment nb = new NetBankingPayment(500, "Sujay", "Merchant3", "wrongpass");
        try {
            nb.processPayment();
            stored[idx++] = nb;
        } catch (InvalidCredentialsException e) {
            System.out.println("Authentication failed. Please check credentials.");
            System.out.println("LOG: " + e.getClass().getSimpleName() + " -> <masked>");
        } catch (PaymentException e) {
            System.out.println("NetBanking error: " + e.getMessage());
        }


        System.out.println("\nScenario: Missing beneficiary (UPI)");
        UPIPayment upiNoBene = new UPIPayment(100, "Sujay", null, "1234");
        try {
            upiNoBene.processPayment();
            stored[idx++] = upiNoBene;
        } catch (PaymentException e) {
            System.out.println("Please provide beneficiary: " + e.getMessage());
        }


        System.out.println("\nScenario: CreditCard timeout then retry");
        CreditCardPayment cc = new CreditCardPayment(1200, "Sujay", "Merchant4", "41111112", true);
        try {
            cc.processPayment();
            stored[idx++] = cc;
        } catch (PaymentGatewayTimeoutException e) {
            System.out.println("Timeout: " + e.getMessage() + " - attempting retry...");
            if (cc.retry()) stored[idx++] = cc;
        } catch (PaymentException e) {
            System.out.println("Card error: " + e.getMessage());
        }

        System.out.println("\nScenario: Wrap UPI wrong PIN into TransactionFailedException");
        UPIPayment upiBadPin = new UPIPayment(200, "Sujay", "Merchant5", "0000");
        try {
            try {
                upiBadPin.processPayment();
                stored[idx++] = upiBadPin;
            } catch (PaymentException inner) {
                throw new TransactionFailedException("UPI failed for Merchant5", inner);
            }
        } catch (TransactionFailedException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }


        System.out.println("\nStored successful transactions:");
        double grand = 0;
        for (int i = 0; i < idx; i++) {
            Payment p = stored[i];
            System.out.println((i+1) + ") " + p.summary());
            grand += p.amount;
        }
        System.out.printf("Grand total: ₹%.0f%n", grand);

        System.out.println("\nRefund for refundable transactions:");
        for (int i = 0; i < idx; i++) {
            Payment p = stored[i];
            if (p instanceof Refundable) {
                ((Refundable) p).refund();
            }
        }

        sc.close();
    }
}
