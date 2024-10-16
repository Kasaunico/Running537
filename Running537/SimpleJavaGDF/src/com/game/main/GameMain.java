package com.game.main;

import javax.swing.*;

public class GameMain {
    private static final String GAME_TITLE="奔跑吧乌萨奇";
    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT=450;
    public static Game sGame;

    public static void main(String[] args) {
        JFrame frame=new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        sGame=new Game(GAME_WIDTH,GAME_HEIGHT);
        frame.add(sGame);
        frame.pack();

        frame.setVisible(true);
        frame.setIconImage(Resources.iconimage);
    }
}
