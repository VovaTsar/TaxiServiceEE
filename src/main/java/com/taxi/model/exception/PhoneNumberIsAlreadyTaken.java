package com.taxi.model.exception;

public class PhoneNumberIsAlreadyTaken extends RuntimeException {

    public PhoneNumberIsAlreadyTaken() { }

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
