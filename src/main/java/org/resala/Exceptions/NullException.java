package org.resala.Exceptions;

public class NullException extends RuntimeException {
    String message;

    public NullException(String className) {
        super(className+" Can't be null");
        this.message=className+" Can't be null";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
