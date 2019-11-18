package com.robosh.model.customExceptions;

public class PhoneNumberIsAlreadyTaken extends Exception {
    public PhoneNumberIsAlreadyTaken() {
    }

    public PhoneNumberIsAlreadyTaken(String message) {
        super(message);
    }

    public PhoneNumberIsAlreadyTaken(Throwable throwable) {
        super(throwable);
    }

    public PhoneNumberIsAlreadyTaken(String message, Throwable throwable) {
        super(message, throwable);
    }
}
