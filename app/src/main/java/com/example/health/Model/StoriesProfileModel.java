package com.example.health.Model;

import java.util.List;

public class StoriesProfileModel {
    private List<Integer> images ;
    private boolean add ;
    private String nick ;

    public StoriesProfileModel(List<Integer> images, boolean add, String nick) {
        this.images = images;
        this.add = add;
        this.nick = nick;
    }

    public StoriesProfileModel(boolean add) {
        this.add = add;
    }

    public List<Integer> getImages() {
        return images;
    }

    public boolean isAdd() {
        return add;
    }

    public String getNick() {
        return nick;
    }
}
