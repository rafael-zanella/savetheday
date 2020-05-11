package com.zanella.savetheday.controllers.exceptions;

public class DataIntegrityException extends Throwable {

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

}
