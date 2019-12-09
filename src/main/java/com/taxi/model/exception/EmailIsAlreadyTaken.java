package com.taxi.model.exception;


public class EmailIsAlreadyTaken extends RuntimeException {

    public EmailIsAlreadyTaken() { }

    public EmailIsAlreadyTaken(String message) {
        super(message);
    }

    public EmailIsAlreadyTaken(Throwable throwable) {
        super(throwable);
    }

    public EmailIsAlreadyTaken(String message, Throwable throwable) {
        super(message, throwable);
    }

}
