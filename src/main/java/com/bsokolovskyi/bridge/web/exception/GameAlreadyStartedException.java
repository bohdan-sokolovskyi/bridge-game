package com.bsokolovskyi.bridge.web.exception;

public class GameAlreadyStartedException extends RuntimeException {

    public GameAlreadyStartedException(String gameName) {
        super(String.format("%s game already started", gameName));
    }
}
