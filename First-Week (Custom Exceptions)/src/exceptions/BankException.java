package exceptions;

// Base exception for all bank-related issues
public class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}
