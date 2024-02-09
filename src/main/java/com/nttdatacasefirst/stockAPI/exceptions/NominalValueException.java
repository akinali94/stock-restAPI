package com.nttdatacasefirst.stockAPI.exceptions;

public class NominalValueException extends RuntimeException{
    public NominalValueException(String message) {
        super(message);
    }
}
