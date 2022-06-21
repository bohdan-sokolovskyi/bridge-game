package com.bsokolovskyi.bridge.web.exception;

public class UserExistException extends RuntimeException {

    public UserExistException(String email) {
        super(String.format("user already exist by email %s", email));
    }

}
