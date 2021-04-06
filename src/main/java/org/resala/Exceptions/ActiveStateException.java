package org.resala.Exceptions;

public class ActiveStateException extends RuntimeException {
    String message;

    public ActiveStateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
