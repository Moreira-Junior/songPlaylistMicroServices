package com.MoreiraJunior.cloud.temafinal2.songservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "song")
public class Song {
    @Id
    private String id;
    private String name;

    public Song(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Song(String name) {
        this.name = name;
    }

    public Song(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + this.name;
    }
}
