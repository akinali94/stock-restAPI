package com.nttdatacasefirst.stockAPI.exceptions;

public class ShareholderNotFoundException extends RuntimeException{
    public ShareholderNotFoundException(String message){
        super(message);
    }
}
