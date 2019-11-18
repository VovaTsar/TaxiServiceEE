package com.robosh.model.customExceptions;

public class WrongPasswordOrPhoneNumber extends Exception {
    public WrongPasswordOrPhoneNumber() {
    }

    public WrongPasswordOrPhoneNumber(String message) {
        super(message);
    }

    public WrongPasswordOrPhoneNumber(Throwable throwable) {
        super(throwable);
    }

    public WrongPasswordOrPhoneNumber(String message, Throwable throwable) {
        super(message, throwable);
    }
}
