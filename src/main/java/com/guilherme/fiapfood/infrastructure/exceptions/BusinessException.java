package com.guilherme.fiapfood.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
