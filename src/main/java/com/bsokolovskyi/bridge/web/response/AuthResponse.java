package com.bsokolovskyi.bridge.web.response;

import org.springframework.http.HttpStatus;

public class AuthResponse implements ServerResponse {

    private HttpStatus status = HttpStatus.OK;
    private String text = "Ok";
    private String accessToken;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return String.format("auth-resp: {%s, %s, %s}", status, text, accessToken);
    }
}
