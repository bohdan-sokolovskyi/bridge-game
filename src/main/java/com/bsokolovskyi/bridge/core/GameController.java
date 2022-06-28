package com.bsokolovskyi.bridge.core;

import com.bsokolovskyi.bridge.core.enums.Card;

import java.util.*;

final class GameController {

    public static final int COUNT_OF_STARTED_CARDS = 5;
    public static final int COUNT_OF_STARTED_CARDS_FOR_FIRST_PLAYER = 4;

    private final Deque<Player> playerQue;
    private Player currentPlayer;
    private List<Card> lastCards;
    private Deque<Card> deck;
    private Queue<Card> usedDeck;

    GameController(List<Player> playerList) {
        playerQue = new ArrayDeque<>(playerList);
        initCards();
    }

    private void initCards() {
        List<Card> cards = Arrays.asList(Card.values());

        Collections.shuffle(cards);

        deck = new ArrayDeque<>(cards);
        usedDeck = new ArrayDeque<>();

        currentPlayer = playerQue.pollFirst();

        for(Player player : playerQue) {
            player.setCurrentCards(getCards(COUNT_OF_STARTED_CARDS));
        }

        //TODO: fix null problem
        currentPlayer.setCurrentCards(getCards(COUNT_OF_STARTED_CARDS_FOR_FIRST_PLAYER));
        usedDeck.addAll(getCards(1));
    }

    private List<Card> getCards(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("n < 0");
        }

        List<Card> cards = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            cards.add(deck.pollFirst());
        }

        return cards;
    }
}
