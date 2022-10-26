package com.MoreiraJunior.cloud.temafinal2.appservice.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {


    private String id;
    private List<String> songs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }
}
