package com.example.health.Model;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class StoriesModel  implements Serializable {
    private List<Integer> Stories;
    private boolean add, viewed;
    private List<Bitmap> photoStories;
    private String nick ;

    public StoriesModel(List<Integer> stories, boolean add, boolean viewed , String nick) {
        Stories = stories;
        this.add = add;
        this.viewed = viewed;
        this.nick = nick ;
    }

    public StoriesModel(boolean add) {
        this.add = add;
    }

    public StoriesModel( boolean add, boolean viewed,List<Bitmap>photoStories) {
        this.add = add;
        this.viewed = viewed;
        this.photoStories = photoStories;
    }

    public List<Integer> getStories() {
        return Stories;
    }

    public boolean isAdd() {
        return add;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public List<Bitmap> getPhotoStories() {
        return photoStories;
    }

    public void setPhotoStories(List<Bitmap> photoStories) {
        this.photoStories = photoStories;
    }

    public String getNick() {
        return nick;
    }
}
