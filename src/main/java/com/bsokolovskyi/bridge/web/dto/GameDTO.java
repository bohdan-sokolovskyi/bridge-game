package com.bsokolovskyi.bridge.web.dto;

import com.bsokolovskyi.bridge.web.db.entity.Game;
import com.bsokolovskyi.bridge.web.db.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class GameDTO {

    private String gameId;
    private String gameName;
    private Set<String> players;

    public GameDTO() {}

    public GameDTO(Game game) {
        gameId = game.getGameId();
        gameName = game.getGameName();
        players = game.getPlayers().stream().map(User::getEmail).collect(Collectors.toSet());
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }
}
