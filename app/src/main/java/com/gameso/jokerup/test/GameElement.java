package com.gameso.jokerup.test;

import android.graphics.drawable.Drawable;

public class GameElement {
    private int speed;
    private String tag;
    private Drawable drawable;


    public GameElement(String tag, Drawable drawable) {
        this.tag = tag;
        this.drawable = drawable;
    }

    public GameElement(int speed, String tag, Drawable drawable) {
        this.speed = speed;
        this.tag = tag;
        this.drawable = drawable;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }


}
