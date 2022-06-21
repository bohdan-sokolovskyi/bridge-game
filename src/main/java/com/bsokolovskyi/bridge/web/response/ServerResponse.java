package com.bsokolovskyi.bridge.web.response;

import org.springframework.http.HttpStatus;

public interface ServerResponse {

    void setStatus(HttpStatus status);
    void setText(String text);

    HttpStatus getStatus();
    String getText();
}
