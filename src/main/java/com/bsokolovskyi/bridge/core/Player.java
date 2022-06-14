package com.bsokolovskyi.bridge.core;

import java.util.EnumSet;
import java.util.List;

public final class Player {

    private final String nick;
    private final boolean isComputer;
    private List<Card> currentCards;
    private int currentPoints = 0;

    public Player(String nick) {
        this.nick = nick;
        this.isComputer = false;
    }

    public Player(String nick, boolean isComputer) {
        this.nick = nick;
        this.isComputer = isComputer;
    }

    public String getNick() {
        return nick;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public List<Card> getCurrentCards() {
        return currentCards;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentCards(List<Card> currentCards) {
        this.currentCards = currentCards;
    }

    public void addCard(Card card) {
        currentCards.add(card);
    }

    public void throwCard(Card card) {
        currentCards.remove(card);
    }

    public boolean isEmptyCards() {
        return currentCards.size() == 0;
    }

    public int sumPoints() {
        return currentCards.stream().map(Card::getPoints).reduce(0, Integer::sum);
    }
}
