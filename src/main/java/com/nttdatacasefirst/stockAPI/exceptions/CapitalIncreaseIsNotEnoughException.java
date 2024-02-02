package com.nttdatacasefirst.stockAPI.exceptions;

public class CapitalIncreaseIsNotEnoughException extends RuntimeException {
    public CapitalIncreaseIsNotEnoughException(String message) {
        super(message);
    }
}
