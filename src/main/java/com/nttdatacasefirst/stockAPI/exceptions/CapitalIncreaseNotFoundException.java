package com.nttdatacasefirst.stockAPI.exceptions;


public class CapitalIncreaseNotFoundException extends RuntimeException{

    public CapitalIncreaseNotFoundException(String message){
        super(message);
    }
}
