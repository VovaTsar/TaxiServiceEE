package com.taxi.model.exception;

public class InputDataUncorrectRuntimeExeption extends RuntimeException {
    public InputDataUncorrectRuntimeExeption() {
    }

    public InputDataUncorrectRuntimeExeption(String message) {
        super(message);
    }

    public InputDataUncorrectRuntimeExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
