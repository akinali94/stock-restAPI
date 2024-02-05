package com.nttdatacasefirst.stockAPI.exceptions;

public class OperationNotFoundException extends RuntimeException {
    public OperationNotFoundException(String message) {
        super(message);
    }
}
