package com.bsokolovskyi.bridge.web.request;

import com.bsokolovskyi.bridge.core.enums.Card;

import java.util.Set;

public class GameDataRequest {

    private String gameId;

    private Set<Card> cards;

    public String getGameId() {
        return gameId;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
