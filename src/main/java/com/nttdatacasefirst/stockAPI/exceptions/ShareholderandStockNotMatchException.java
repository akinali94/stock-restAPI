package com.nttdatacasefirst.stockAPI.exceptions;

public class ShareholderandStockNotMatchException extends RuntimeException{
    public ShareholderandStockNotMatchException(String message) {
        super(message);
    }
}
