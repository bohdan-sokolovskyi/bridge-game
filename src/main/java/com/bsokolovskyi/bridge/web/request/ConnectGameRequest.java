package com.bsokolovskyi.bridge.web.request;

public class ConnectGameRequest {

    private String email;
    private String gameId;

    public String getEmail() {
        return email;
    }

    public String getGameId() {
        return gameId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
