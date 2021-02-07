package org.resala.resala.Exceptions;

public class TokenException extends RuntimeException{
    String message;
    public TokenException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
