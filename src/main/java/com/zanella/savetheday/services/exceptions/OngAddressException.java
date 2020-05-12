package com.zanella.savetheday.services.exceptions;

public class OngAddressException extends RuntimeException{

    public OngAddressException(String message) {
        super(message);
    }

    public OngAddressException(String message, Throwable cause) {
        super(message, cause);
    }
}
