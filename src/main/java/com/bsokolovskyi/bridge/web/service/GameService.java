package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.web.db.entity.Game;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.GameRepository;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(@Autowired GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void createGame(User user) {
        Game game = new Game();
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayers(new HashSet<>(List.of(user)));

    }

    public void connectToGame() {

    }
}
