package org.resala.resala.Exceptions;

public class MyEntityNotFoundException extends RuntimeException{
    String message;
    public MyEntityNotFoundException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
