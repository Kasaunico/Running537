package com.game.model;

import com.game.main.Resources;

import  java.awt.Rectangle;
public class Player {
    private float x, y;
    private int width, height,velY ;

    private Rectangle rect,duckRect,ground;

    private boolean isAlive;
    private boolean isDucked;//是否下蹲
    private float duckDuration=0.6f;//下蹲时间

    private static final int JUMP_VELOCITY=-600;//跳跃速度
    private static final int ACCEL_GRAVITY=1800;//重力加速度

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ground=new Rectangle(0,405,800,45);
        rect=new Rectangle();
        duckRect=new Rectangle();
        isAlive=true;
        isDucked=false;
        updateRect();
    }
    public void updateRect() {
        rect.setBounds((int)x+10, (int)y, width-20, height);
        duckRect.setBounds((int)x, (int)y+20, width, height+20);
    }

    public boolean isGrounded(){
        return rect.intersects(ground);
    }

    public void update(float delta) {
        if(isGrounded()){
            y=406-height;
            velY=0;
            if (duckDuration>0&&isDucked)
                duckDuration-=delta;
            else {
                isDucked=false;
                duckDuration=0.6f;
            }
        }else{
            velY+=ACCEL_GRAVITY*delta;
            y+=velY*delta;
        }
        updateRect();
    }

    public void jump() {
        if(isGrounded()){
            Resources.onjump.play();
            isDucked=false;
            duckDuration=0.6f;
            y-=20;
            velY=JUMP_VELOCITY;
            updateRect();
        }
    }

    public void duck() {
        if(isGrounded()){
            Resources.onduck.play();
            isDucked=true;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getDuckRect() {
        return duckRect;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isDucked() {
        return isDucked;
    }

    public void pushBack(int dx){
        Resources.hit.play();
        x-=dx;
        if(x<-width/2)
            isAlive=false;
        updateRect();
    }


}
