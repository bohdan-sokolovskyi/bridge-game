package com.bsokolovskyi.bridge.core.enums;

import com.bsokolovskyi.bridge.core.CardAction;

public enum Card {

    // hearts
    H_A(Card.CARD_A_DEFAULT_POINTS, null),
    H_6(Card.CARD_DEFAULT_POINTS, null),
    H_7(Card.CARD_DEFAULT_POINTS, null),
    H_8(Card.CARD_DEFAULT_POINTS, null),
    H_9(Card.CARD_DEFAULT_POINTS, null),
    H_10(Card.CARD_10_DEFAULT_POINTS, null),
    H_J(Card.CARD_J_DEFAULT_POINTS, null),
    H_Q(Card.CARD_Q_DEFAULT_POINTS, null),
    H_K(Card.CARD_H_K_DEFAULT_POINTS, null),

    // tiles
    T_A(Card.CARD_A_DEFAULT_POINTS, null),
    T_6(Card.CARD_DEFAULT_POINTS, null),
    T_7(Card.CARD_DEFAULT_POINTS, null),
    T_8(Card.CARD_DEFAULT_POINTS, null),
    T_9(Card.CARD_DEFAULT_POINTS, null),
    T_10(Card.CARD_10_DEFAULT_POINTS, null),
    T_J(Card.CARD_J_DEFAULT_POINTS, null),
    T_Q(Card.CARD_Q_DEFAULT_POINTS, null),
    T_K(Card.CARD_K_DEFAULT_POINTS, null),

    // clovers
    C_A(Card.CARD_A_DEFAULT_POINTS, null),
    C_6(Card.CARD_DEFAULT_POINTS, null),
    C_7(Card.CARD_DEFAULT_POINTS, null),
    C_8(Card.CARD_DEFAULT_POINTS, null),
    C_9(Card.CARD_DEFAULT_POINTS, null),
    C_10(Card.CARD_10_DEFAULT_POINTS, null),
    C_J(Card.CARD_J_DEFAULT_POINTS, null),
    C_Q(Card.CARD_Q_DEFAULT_POINTS, null),
    C_K(Card.CARD_K_DEFAULT_POINTS, null),

    // pikes
    P_A(Card.CARD_A_DEFAULT_POINTS, null),
    P_6(Card.CARD_DEFAULT_POINTS, null),
    P_7(Card.CARD_DEFAULT_POINTS, null),
    P_8(Card.CARD_DEFAULT_POINTS, null),
    P_9(Card.CARD_DEFAULT_POINTS, null),
    P_10(Card.CARD_10_DEFAULT_POINTS, null),
    P_J(Card.CARD_P_J_DEFAULT_POINTS, null),
    P_Q(Card.CARD_Q_DEFAULT_POINTS, null),
    P_K(Card.CARD_K_DEFAULT_POINTS, null);

    public static final int CARD_DEFAULT_POINTS = 0;

    public static final int CARD_10_DEFAULT_POINTS = 10;
    public static final int CARD_J_DEFAULT_POINTS = 20;
    public static final int CARD_Q_DEFAULT_POINTS = 10;
    public static final int CARD_K_DEFAULT_POINTS = 10;
    public static final int CARD_A_DEFAULT_POINTS = 15;

    public static final int CARD_P_J_DEFAULT_POINTS = 40;
    public static final int CARD_H_K_DEFAULT_POINTS = 50;

    int points;
    CardAction cardAction;

    Card(int points, CardAction cardAction) {
        this.points = points;
        this.cardAction = cardAction;
    }

    public int getPoints() {
        return points;
    }

    public void execCardAction() {
        //TODO: implement me
    }

    public static boolean isJCard(Card card) {
        return card.equals(H_J) || card.equals(T_J) || card.equals(C_J) || card.equals(P_J);
    }
}
