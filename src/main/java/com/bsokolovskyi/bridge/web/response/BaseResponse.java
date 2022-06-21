package com.bsokolovskyi.bridge.web.response;

import org.springframework.http.HttpStatus;

public class BaseResponse implements ServerResponse {

    private HttpStatus status = HttpStatus.OK;
    private String text = "Ok";

    @Override
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("base-resp: {%s, %s}", status, text);
    }
}
