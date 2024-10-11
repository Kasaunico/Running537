package com.game.state;
import  java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.game.main.Resources;

public class LoadState extends State{
    @Override
    public void init() {
        System.out.printf("开始加载资源");
        Resources.load();
        System.out.printf("加载资源完成");
    }

    @Override
    public void update(float delta) {
    setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onclick(MouseEvent e) {

    }

    @Override
    public void onkeyPress(KeyEvent e) {

    }

    @Override
    public void onkeyRelease(KeyEvent e) {

    }
}
