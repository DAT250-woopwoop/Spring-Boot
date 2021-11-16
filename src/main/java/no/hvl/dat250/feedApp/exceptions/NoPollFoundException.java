package no.hvl.dat250.feedApp.exceptions;

public class NoPollFoundException extends RuntimeException{
    public NoPollFoundException() {
        super();
    }
    public NoPollFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoPollFoundException(String message) {
        super(message);
    }
    public NoPollFoundException(Throwable cause) {
        super(cause);
    }
}
