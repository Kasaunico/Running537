package com.game.model;

import com.game.framework.util.RandomNumberGenerator;
public class Cloud {
    private float x,y;
    private static final int VEL_X=-20;//云朵移动速度

    public Cloud(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        x+=VEL_X*delta;
        if(x<=-200){
            x+=1000;
            y=RandomNumberGenerator.getRandIntBetween(20, 100);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
