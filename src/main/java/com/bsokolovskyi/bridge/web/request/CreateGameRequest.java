package com.bsokolovskyi.bridge.web.request;

public class CreateGameRequest {

    private String email;
    private String gameName;

    public String getEmail() {
        return email;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameName() {
        return gameName;
    }
}
