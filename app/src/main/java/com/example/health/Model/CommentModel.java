package com.example.health.Model;

public class CommentModel {
    private String nick , comment ;
    private int image ;

    public CommentModel(String nick, String comment, int image) {
        this.nick = nick;
        this.comment = comment;
        this.image = image;
    }

    public String getNick() {
        return nick;
    }

    public String getComment() {
        return comment;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "nick='" + nick + '\'' +
                ", comment='" + comment + '\'' +
                ", image=" + image +
                '}';
    }
}
