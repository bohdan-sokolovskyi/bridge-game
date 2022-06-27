package com.bsokolovskyi.bridge.web.db.entity;

import com.bsokolovskyi.bridge.core.GameStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Collections;
import java.util.Set;

@Document(collection = "game_tb")
public class Game {

    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String gameId;
    private String gameName;
    @DocumentReference(collection = "user_tb")
    private Set<User> players;
    private GameStatus gameStatus;

    public void setId(String id) {
        this.id = id;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }

    public void setFirstPlayer(User player) {
        this.players = Collections.singleton(player);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public Set<User> getPlayers() {
        return players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
