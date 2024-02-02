package com.nttdatacasefirst.stockAPI.exceptions;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message){
        super(message);
    }
}
