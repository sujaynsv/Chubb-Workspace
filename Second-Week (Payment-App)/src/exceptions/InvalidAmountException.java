package exceptions;

public class InvalidAmountException extends PaymentException {
	 public InvalidAmountException(String msg) { super(msg); }
}