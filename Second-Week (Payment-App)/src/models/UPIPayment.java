package models;

import exceptions.*;
import interfaces.Retryable;

public class UPIPayment extends Payment implements Retryable {
    private String upiPin;

    public UPIPayment(double amount, String payer, String payee, String upiPin) {
        super(amount, payer, payee);
        this.upiPin = upiPin;
    }

    @Override
    public void processPayment() throws PaymentException {
        if (payee == null) throw new BeneficiaryNotFoundException("Beneficiary not provided.");
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive.");
        if (!"1234".equals(upiPin)) throw new InvalidCredentialsException("Invalid UPI PIN.");
        System.out.println("UPI processed: " + summary());
    }

    @Override
    public boolean retry() {
        try {
            System.out.println("Retrying UPI: " + txnId);
            processPayment();
            return true;
        } catch (PaymentException e) {
            System.out.println("UPI retry failed: " + e.getMessage());
            return false;
        }
    }
}
