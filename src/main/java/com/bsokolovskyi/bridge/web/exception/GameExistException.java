package com.bsokolovskyi.bridge.web.exception;

public class GameExistException extends RuntimeException {

    public GameExistException(String gameName) {
        super(String.format("%s game exist", gameName));
    }
}
