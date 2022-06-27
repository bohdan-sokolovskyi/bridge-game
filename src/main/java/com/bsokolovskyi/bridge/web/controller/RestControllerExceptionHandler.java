package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TEXT_PARAM = "text";
    private static final HttpHeaders STD_HEADERS = new HttpHeaders();

    @ExceptionHandler({UserNotExistException.class})
    protected ResponseEntity<Object> userNotExistHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "user not exist"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({UserExistException.class})
    protected ResponseEntity<Object> userExistHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "user exist"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    protected ResponseEntity<Object> incorrectPasswordHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "incorrect password"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameNotExistException.class})
    protected ResponseEntity<Object> gameNotExistHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game not exist"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameExistException.class})
    protected ResponseEntity<Object> gameExistHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game exist"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameAlreadyStartedException.class})
    protected ResponseEntity<Object> gameAlreadyStartedHandle(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game already started"),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}
