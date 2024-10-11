package com.game.main;

import java.awt.*;

import javax.swing.JPanel;

import com.game.state.LoadState;
import com.game.state.State;

import com.game.framework.util.InputHandler;

public class Game extends JPanel implements Runnable{
    private volatile State currentState;

    private Thread gameThread;
    private volatile boolean running;//游戏是否运行

    private int gameWidth, gameHeight;
    private Image gameImage;//缓冲图片对象

    private InputHandler inputHandler;//事件处理对象

    private void initInput(){
        inputHandler = new InputHandler();
        this.addMouseListener(inputHandler);
        this.addKeyListener(inputHandler);
    }

    public Game(int gameWidth, int gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    public void setCurrentState(State newstate){
        System.gc();
        newstate.init();
        currentState = newstate;
        inputHandler.setCurrentState(currentState);
    }

    public void addNotify(){
        super.addNotify();
        initInput();
        setCurrentState(new LoadState());
        initGame();
    }

    private void initGame(){
        running = true;
        gameThread = new Thread(this,"游戏线程");
        gameThread.start();
    }

    @Override
    public void run() {
        
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while(running){
            long beforeUpdateRender=System.nanoTime();
            long deltaMillis=updateDurationMillis+sleepDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis=Math.max(2, 17 - updateDurationMillis);
            try {
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    private void updateAndRender(long deltaMillis) {
        currentState.update(deltaMillis /1000f);
        prepareGameImage();
        currentState.render(gameImage.getGraphics());
        //repaint();
        renderGameImage(getGraphics());
    }

    public void exit(){
        running = false;
    }

    private void prepareGameImage(){
        if(gameImage == null)//图片不存在
            gameImage=createImage(gameWidth, gameHeight);
        Graphics g=gameImage.getGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);//清除图片内容
    }

//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//        if(gameImage==null)
//            return;
//        g.drawImage(gameImage, 0, 0, null);
//    }
    private void renderGameImage(Graphics g){
        if(gameImage!=null)
            g.drawImage(gameImage, 0, 0, null);
        g.dispose();
    }
}
