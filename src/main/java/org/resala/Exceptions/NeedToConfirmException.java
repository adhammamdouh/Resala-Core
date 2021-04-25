package org.resala.Exceptions;

public class NeedToConfirmException extends RuntimeException {
    String message;

    public NeedToConfirmException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
