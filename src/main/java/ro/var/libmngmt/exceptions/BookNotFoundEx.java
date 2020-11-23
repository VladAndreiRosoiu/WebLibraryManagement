package ro.var.libmngmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundEx extends RuntimeException {
    public BookNotFoundEx(String message) {
        super(message);
    }
}
