package com.nttdatacasefirst.stockAPI.exceptions;

public class DividendDistributionNotFoundException extends RuntimeException{
    public DividendDistributionNotFoundException(String message) {
        super(message);
    }
}
