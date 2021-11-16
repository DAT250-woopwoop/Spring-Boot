package no.hvl.dat250.feedApp.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UsernameTakenException extends RuntimeException{
    public UsernameTakenException() {
        super();
    }
    public UsernameTakenException(String message, Throwable cause) {
        super(message, cause);
    }
    public UsernameTakenException(String message) {
        super(message);
    }
    public UsernameTakenException(Throwable cause) {
        super(cause);
    }

}
