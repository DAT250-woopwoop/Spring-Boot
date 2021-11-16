package no.hvl.dat250.feedApp.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAccountFoundException extends RuntimeException{

    public NoAccountFoundException() {
        super();
    }
    public NoAccountFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoAccountFoundException(String message) {
        super(message);
    }
    public NoAccountFoundException(Throwable cause) {
        super(cause);
    }
}
