package org.resala.Exceptions;

public class TenantHeaderNotFoundException extends RuntimeException{
    String message;
    public TenantHeaderNotFoundException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
