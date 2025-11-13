package exceptions;

public class BookNotAvailableException extends BusinessException {
    public BookNotAvailableException(String message) { super(message); }
}