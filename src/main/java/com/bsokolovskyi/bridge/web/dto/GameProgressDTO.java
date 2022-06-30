package com.bsokolovskyi.bridge.web.dto;

import com.bsokolovskyi.bridge.core.enums.Card;

public class GameProgressDTO {

    private String gameId;
    private Card card;

    public String getGameId() {
        return gameId;
    }

    public Card getCard() {
        return card;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
