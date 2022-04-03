package com.example.demo.exception;

public class RcrException extends RuntimeException{

    private final ErrorCode errorCode;

    public RcrException(ErrorCode errorCode) {
        super(errorCode.getCode() + ": " + errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
