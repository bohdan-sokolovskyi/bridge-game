package com.bsokolovskyi.bridge.web.db.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "game")
public class Game {

    @Id
    private String id;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
