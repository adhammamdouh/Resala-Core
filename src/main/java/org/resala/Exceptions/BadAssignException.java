package org.resala.Exceptions;

public class BadAssignException extends RuntimeException{
    String message;

    public BadAssignException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
