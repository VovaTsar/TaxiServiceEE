package com.mytaxi.model.customExceptions;


public class EmailIsAlreadyTaken extends Exception {
    public EmailIsAlreadyTaken() {
    }

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
