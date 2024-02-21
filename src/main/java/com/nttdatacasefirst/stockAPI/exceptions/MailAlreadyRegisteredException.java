package com.nttdatacasefirst.stockAPI.exceptions;

public class MailAlreadyRegisteredException extends RuntimeException {
    public MailAlreadyRegisteredException(String message) {
        super(message);
    }
}
