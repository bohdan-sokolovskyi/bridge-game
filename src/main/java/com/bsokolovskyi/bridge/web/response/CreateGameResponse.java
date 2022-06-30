package com.bsokolovskyi.bridge.web.response;

import com.bsokolovskyi.bridge.core.enums.Card;

import java.util.List;

public class CreateGameResponse {

    private String gameId;
    private List<Card> cards;

    public String getGameId() {
        return gameId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
