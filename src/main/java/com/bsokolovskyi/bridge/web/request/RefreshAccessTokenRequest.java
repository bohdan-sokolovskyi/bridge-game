package com.bsokolovskyi.bridge.web.request;

public class RefreshAccessTokenRequest {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
