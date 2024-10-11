package com.game.model;

import com.game.framework.util.RandomNumberGenerator;

import java.awt.*;

public class Block {
    private float x,y;
    private int width,height;
    private Rectangle rect;
    private boolean visible;

    private static final int UPPER_Y=275;
    private static final int LOWER_Y=355;

    public Block(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle((int)x,(int)y,width,height);
        this.visible = false;
    }

    public void update(float delta,float vleX) {
        x+=vleX*delta;
        if(x<=-50)
            reset();
        updateRect();
    }
    public void reset() {
        visible=true;
        if(RandomNumberGenerator.getRandomInt(3)==0)
            y=UPPER_Y;
        else
            y=LOWER_Y;
        x+=1000;
    }

    public void updateRect() {
        rect.setBounds((int)x,(int)y,width,height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isVisible() {
        return visible;
    }

    public void onCollide(Player p){
        visible=false;
        p.pushBack(50);
    }
}
