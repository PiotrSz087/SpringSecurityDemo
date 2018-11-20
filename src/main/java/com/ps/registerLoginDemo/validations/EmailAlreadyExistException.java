package com.ps.registerLoginDemo.validations;

@SuppressWarnings("serial")
public class EmailAlreadyExistException extends Throwable {
    public EmailAlreadyExistException(final String message) {
        super(message);
    }
}
