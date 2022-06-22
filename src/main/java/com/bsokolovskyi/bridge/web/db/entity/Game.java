package com.bsokolovskyi.bridge.web.db.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Set;

@Document(collection = "game_tb")
public class Game {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String gameId;

    @DocumentReference(collection = "user_tb")
    private Set<User> players;

    public void setId(String id) {
        this.id = id;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public Set<User> getPlayers() {
        return players;
    }
}
