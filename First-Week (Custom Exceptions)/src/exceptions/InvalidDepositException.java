package exceptions;

public class InvalidDepositException extends BankException {
    public InvalidDepositException(String message) {
        super(message);
    }
}
