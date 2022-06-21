package com.bsokolovskyi.bridge.web.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String email) {
        super(String.format("user not exist by email %s", email));
    }
}
