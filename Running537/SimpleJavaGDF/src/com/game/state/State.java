package com.game.state;

import com.game.main.GameMain;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    public abstract void init();//初始化
    public abstract void update(float delta);//更新
    public abstract void render(Graphics g);//渲染
    public abstract void onclick(MouseEvent e);//鼠标触发
    public abstract void onkeyPress(KeyEvent e);//按下按钮
    public abstract void onkeyRelease(KeyEvent e);//释放按钮
    public void setCurrentState(State newState) {
        GameMain.sGame.setCurrentState(newState);
    }

}
