package models;

import java.util.UUID;
import exceptions.PaymentException;

public abstract class Payment {
    public double amount;
    protected String payer;
    protected String payee;
    protected String txnId;

    public Payment(double amount, String payer, String payee) {
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
        this.txnId = UUID.randomUUID().toString().substring(0,8);
    }

    public abstract void processPayment() throws PaymentException;

    public String summary() {
        return String.format("[%s] %s -> %s : ₹%.0f (id:%s)",
                this.getClass().getSimpleName(),
                payer,
                payee == null ? "<null>" : payee,
                amount,
                txnId);
    }
}

