package com.nttdatacasefirst.stockAPI.exceptions;

public class ShareholderAlreadyExistException extends RuntimeException{

    public ShareholderAlreadyExistException(String message){
        super(message);
    }
}
