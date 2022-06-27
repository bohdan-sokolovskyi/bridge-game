package com.bsokolovskyi.bridge.web.exception;

public class GameNotExistException extends RuntimeException {

    public GameNotExistException(String gameName) {
        super(String.format("%s game not exist", gameName));
    }
}
