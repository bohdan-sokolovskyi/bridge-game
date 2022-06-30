package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = LogManager.getLogger(RestControllerExceptionHandler.class);

    private static final String TEXT_PARAM = "text";
    private static final HttpHeaders STD_HEADERS = new HttpHeaders();

    @ExceptionHandler({UserNotExistException.class})
    protected ResponseEntity<Object> userNotExistHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "user not exist " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({UserExistException.class})
    protected ResponseEntity<Object> userExistHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "user exist " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    protected ResponseEntity<Object> incorrectPasswordHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "incorrect password " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameNotExistException.class})
    protected ResponseEntity<Object> gameNotExistHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game not exist " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameExistException.class})
    protected ResponseEntity<Object> gameExistHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game exist " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({GameAlreadyStartedException.class})
    protected ResponseEntity<Object> gameAlreadyStartedHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "game already started " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({IllegalStateException.class, InterruptedException.class, MailException.class})
    protected ResponseEntity<Object> internalHandle(RuntimeException e, WebRequest request) {
        logger.info(e.getMessage());
        return handleExceptionInternal(
                e,
                Collections.singletonMap(TEXT_PARAM, "internal error: " + e.getMessage()),
                STD_HEADERS,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
