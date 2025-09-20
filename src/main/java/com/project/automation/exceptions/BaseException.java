package com.project.automation.exceptions;

import java.time.LocalDateTime;

/**
 * This class includes common properties like errorCode and timeStamp.
 * It provides constructors to initialize these properties along with the exception message and cause.
 */
public class BaseException  extends RuntimeException{
    private final String errorCode;
    private final LocalDateTime timeStamp;


    public BaseException(String message){
            super(message);
            this.errorCode = null;
            this.timeStamp = LocalDateTime.now();
    }
    public BaseException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
        this.timeStamp = LocalDateTime.now();
    }
    public BaseException(String message, String errorCode, Throwable cause){
        super(message, cause);
        this.errorCode = errorCode;
        this.timeStamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }
    public LocalDateTime getTimeStamp() { return timeStamp;}


    @Override
    public String toString() {
        return String.format("Base Exception { ErrorCode: %s, TimeStamp: %s", getErrorCode(), getTimeStamp());
    }
}
