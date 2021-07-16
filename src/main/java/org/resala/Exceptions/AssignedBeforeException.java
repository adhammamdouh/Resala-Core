package org.resala.Exceptions;

public class AssignedBeforeException extends RuntimeException{
    String message;

    public AssignedBeforeException(String className) {
        super(className+" Assigned Before");
        this.message=className+" Assigned Before";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
