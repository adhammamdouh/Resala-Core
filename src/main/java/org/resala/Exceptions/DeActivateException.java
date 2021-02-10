package org.resala.Exceptions;

public class DeActivateException extends RuntimeException {
    String message;

    public DeActivateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
