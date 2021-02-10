package org.resala.Exceptions;

public class ConstraintViolationException extends RuntimeException {
    String message;

    public ConstraintViolationException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
