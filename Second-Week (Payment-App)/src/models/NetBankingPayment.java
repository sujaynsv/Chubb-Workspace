package models;
import exceptions.*;

public class NetBankingPayment extends Payment {
    private String password;

    public NetBankingPayment(double amount, String payer, String payee, String password) {
        super(amount, payer, payee);
        this.password = password;
    }

    @Override
    public void processPayment() throws PaymentException {
        if (payee == null) throw new BeneficiaryNotFoundException("Beneficiary missing.");
        if (amount <= 0) throw new InvalidAmountException("Invalid amount.");
        if (!"bankpass".equals(password)) throw new InvalidCredentialsException("Invalid credentials (masked).");
        System.out.println("NetBanking processed: " + summary());
    }
}
