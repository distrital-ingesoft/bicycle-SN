package com.cyship.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceAlreadyRegistredException extends RuntimeException {

    public ResourceAlreadyRegistredException() {

        super();
    }
    public ResourceAlreadyRegistredException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourceAlreadyRegistredException(String message) {
        super(message);
    }
    public ResourceAlreadyRegistredException(Throwable cause) {
        super(cause);
    }
}
