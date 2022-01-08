package com.example.health.Model;

public class DirectModel {
    private String nick , action ;
    private int image ;
    private boolean stories , click ;

    public DirectModel(String nick, String action, int image, boolean stories , boolean click) {
        this.nick = nick;
        this.action = action;
        this.image = image;
        this.stories = stories;
        this.click = click;
    }

    public String getNick() {
        return nick;
    }

    public String getAction() {
        return action;
    }

    public int getImage() {
        return image;
    }

    public boolean isStories() {
        return stories;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public void setStories(boolean stories) {
        this.stories = stories;
    }
}
