package exceptions;

public class InsufficientBalanceException extends PaymentException {
	 public InsufficientBalanceException(String msg) { super(msg); }
}
