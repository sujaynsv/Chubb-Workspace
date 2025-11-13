package exceptions;

public class TransactionFailedException extends PaymentException {
	 public TransactionFailedException(String msg, Throwable cause) {
	     super(msg + " | cause: " + cause.getClass().getSimpleName() + ":" + cause.getMessage());
	     initCause(cause);
	 }
	}

