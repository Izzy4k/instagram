package com.example.health.Model;

public class ShapeModel {
    private String nick , nickMilk ;
    private int image ;

    public ShapeModel(String nick, String nickMilk, int image) {
        this.nick = nick;
        this.nickMilk = nickMilk;
        this.image = image;
    }

    public String getNick() {
        return nick;
    }

    public String getNickMilk() {
        return nickMilk;
    }

    public int getImage() {
        return image;
    }

}
