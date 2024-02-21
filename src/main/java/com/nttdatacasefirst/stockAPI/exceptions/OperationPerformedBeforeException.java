package com.nttdatacasefirst.stockAPI.exceptions;

public class OperationPerformedBeforeException extends RuntimeException {
    public OperationPerformedBeforeException(String message) {
        super(message);
    }
}
