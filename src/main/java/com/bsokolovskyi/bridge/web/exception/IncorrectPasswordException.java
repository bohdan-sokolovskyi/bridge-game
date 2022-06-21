package com.bsokolovskyi.bridge.web.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String email) {
        super(String.format("incorrect password for user by email %s", email));
    }
}
