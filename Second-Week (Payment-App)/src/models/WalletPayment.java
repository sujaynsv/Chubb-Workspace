package models;

import interfaces.Refundable;
import exceptions.*;

public class WalletPayment extends Payment implements Refundable {
    private double walletBalance;

    public WalletPayment(double amount, String payer, String payee, double walletBalance) {
        super(amount, payer, payee);
        this.walletBalance = walletBalance;
    }

    @Override
    public void processPayment() throws PaymentException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be > 0 for wallet.");
        if (walletBalance < amount) throw new InsufficientBalanceException("Wallet balance ₹" + walletBalance + " insufficient.");
        walletBalance -= amount;
        System.out.println("Wallet processed: " + summary() + " | remaining balance: ₹" + walletBalance);
    }

    @Override
    public boolean refund() {
        walletBalance += amount;
        System.out.println("Wallet refunded ₹" + amount + " | new balance: ₹" + walletBalance);
        return true;
    }
}
