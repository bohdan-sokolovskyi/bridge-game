package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.core.enums.Card;
import com.bsokolovskyi.bridge.web.db.entity.Player;
import com.bsokolovskyi.bridge.web.db.entity.User;
import com.bsokolovskyi.bridge.web.db.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(@Autowired PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(User user, Set<Card> cards) {
        Player player = new Player();

        player.setUser(user);
        player.setScore(0);
        player.setCards(cards);

        playerRepository.save(player);
        return player;
    }
}
