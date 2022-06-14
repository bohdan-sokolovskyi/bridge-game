package com.bsokolovskyi.bridge.core;

import java.util.*;

public final class Game {

    public static final int MAX_PLAYERS = 4;

    private final UUID id = UUID.randomUUID();
    private final List<Player> players = new ArrayList<>();
    private boolean started = false;

    public UUID getId() {
        return id;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean addPlayer(Player player) {
        if(started) {
            throw new IllegalStateException(String.format("Game %s is started", id.toString()));
        }

        if(players.size() == MAX_PLAYERS) {
            return false;
        }

        players.add(player);
        return true;
    }

    public void startGame() {
        started = true;

        GameController controller = new GameController(players);

    }
}
