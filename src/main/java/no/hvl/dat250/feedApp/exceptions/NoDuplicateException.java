package no.hvl.dat250.feedApp.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NoDuplicateException extends RuntimeException {
    public NoDuplicateException() {
        super();
    }
    public NoDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoDuplicateException(String message) {
        super(message);
    }
    public NoDuplicateException(Throwable cause) {
        super(cause);
    }
}
