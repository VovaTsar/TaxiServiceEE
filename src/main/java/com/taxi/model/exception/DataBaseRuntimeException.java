package com.taxi.model.exception;

public class DataBaseRuntimeException extends RuntimeException {

    public DataBaseRuntimeException() {
    }

    public DataBaseRuntimeException(String message) {
        super(message);
    }

    public DataBaseRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public DataBaseRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
