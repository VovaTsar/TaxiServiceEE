package com.taxi.model.exception;

public class InputDataUnCorrectRuntimeException extends RuntimeException {

    public InputDataUnCorrectRuntimeException() {
    }

    public InputDataUnCorrectRuntimeException(String message) {
        super(message);
    }

    public InputDataUnCorrectRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
