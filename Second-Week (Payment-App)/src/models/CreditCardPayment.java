package models;
import interfaces.*;
import exceptions.*;

public class CreditCardPayment extends Payment implements Retryable, Refundable {
    private String cardNumber;
    private boolean simulateTimeout;

    public CreditCardPayment(double amount, String payer, String payee, String cardNumber, boolean simulateTimeout) {
        super(amount, payer, payee);
        this.cardNumber = cardNumber;
        this.simulateTimeout = simulateTimeout;
    }

    @Override
    public void processPayment() throws PaymentException {
        if (payee == null) throw new BeneficiaryNotFoundException("Beneficiary required.");
        if (amount <= 0) throw new InvalidAmountException("Invalid amount.");
        if (simulateTimeout) throw new PaymentGatewayTimeoutException("Gateway timeout.");
        char last = cardNumber.charAt(cardNumber.length()-1);
        if (!Character.isDigit(last) || ((last - '0') % 2 != 0)) {
            throw new InvalidCredentialsException("Card authorization failed.");
        }
        System.out.println("Card processed: " + summary());
    }

    @Override
    public boolean retry() {
        if (simulateTimeout) {
            simulateTimeout = false; // simulate recovery
            System.out.println("Gateway recovered for retry on txn " + txnId);
        }
        try {
            processPayment();
            return true;
        } catch (PaymentException e) {
            System.out.println("Card retry failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean refund() {
        System.out.println("Refunded to card for txn " + txnId + " amount ₹" + amount);
        return true;
    }
}

