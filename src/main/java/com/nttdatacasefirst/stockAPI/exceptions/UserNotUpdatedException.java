package com.nttdatacasefirst.stockAPI.exceptions;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException(String message) {
        super(message);
    }
}
