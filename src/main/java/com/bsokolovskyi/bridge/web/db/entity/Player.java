package com.bsokolovskyi.bridge.web.db.entity;


import com.bsokolovskyi.bridge.core.enums.Card;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Set;

@Document(collection = "player_tb")
public class Player {

    @Id
    private String id;
    @Indexed(unique = true)
    @DocumentReference(collection = "user_tb")
    private User user;
    private Set<Card> cards;
    private int score;

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
