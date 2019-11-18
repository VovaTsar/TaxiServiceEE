package com.robosh.model.customExceptions;

public class DatabaseRuntimeException extends RuntimeException {
    public DatabaseRuntimeException() {
    }

    public DatabaseRuntimeException(String message) {
        super(message);
    }

    public DatabaseRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public DatabaseRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
