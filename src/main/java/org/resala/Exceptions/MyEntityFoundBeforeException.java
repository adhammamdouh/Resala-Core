package org.resala.Exceptions;

public class MyEntityFoundBeforeException extends RuntimeException{
    String message;
    public MyEntityFoundBeforeException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}