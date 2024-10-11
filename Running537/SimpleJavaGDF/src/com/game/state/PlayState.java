package com.game.state;

import com.game.main.GameMain;
import com.game.main.Resources;
import com.game.model.Block;
import com.game.model.Cloud;
import com.game.model.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayState extends State {

    private Player player;

    private static final int PLAYER_WIDTH=66;
    private static final int PLAYER_HEIGHT=92;

    private Cloud cloud1,cloud2,cloud3;

    private ArrayList<Block> blocks;
    private static final int BLOCK_WIDTH=20;
    private static final int BLOCK_HEIGHT=50;
    private int blockSpeed=-200;

    private Font scoreFont;
    private int playerScore=0;

    @Override
    public void init() {
        player=new Player(160,GameMain.GAME_HEIGHT-45-PLAYER_HEIGHT,PLAYER_WIDTH,PLAYER_HEIGHT);
        cloud1=new Cloud(100,100);
        cloud2=new Cloud(500,50);
        cloud3=new Cloud(800,80);
        blocks=new ArrayList<Block>();
        for(int i=0;i<5;i++){//循环添加五个间隔200的砖块
            Block b=new Block(i*200,GameMain.GAME_HEIGHT-95,BLOCK_WIDTH,BLOCK_HEIGHT);
            blocks.add(b);
        }
        scoreFont=new Font("SansSerif",Font.BOLD,25);
    }

    @Override
    public void update(float delta) {

        if(!player.isAlive())
            setCurrentState(new GameOverState(playerScore/100));

        playerScore+=1;
        if(playerScore%500==0&&blockSpeed>=-280){
            blockSpeed-=10;
        }

        player.update(delta);
        Resources.runAnim.update(delta);
        cloud1.update(delta);
        cloud2.update(delta);
        cloud3.update(delta);
        updateBlocks(delta);
    }

    private void updateBlocks(float delta) {
        for(Block b:blocks){
            b.update(delta,blockSpeed);
            if(b.isVisible()){
                if(player.isDucked()&&player.getDuckRect().intersects(b.getRect()))
                    b.onCollide(player);
                else if(!player.isDucked()&&player.getRect().intersects(b.getRect()))
                    b.onCollide(player);
            }
        }
    }

    @Override
    public void render(Graphics g) {
    g.setColor(Resources.skyBlue);
    g.fillRect(0,0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);

    g.drawImage(Resources.grass,0,405,null);

    renderPlayer(g);

    renderClouds(g);

    renderBlocks(g);

    renderScore(g);


    }




    private void renderScore(Graphics g) {
        g.setFont(scoreFont);
        g.setColor(Color.gray);
        g.drawString(""+playerScore/60, 20, 30);
        g.drawString("使用w跳跃", 600, 30);
        g.drawString("使用s蹲下", 600, 60);
        g.drawString("躲避砖块!", 600, 90);
    }
    private void renderBlocks(Graphics g) {
        for(Block b:blocks){
            if(b.isVisible()){
                g.drawImage(Resources.block, (int) b.getX(), (int) b.getY(), BLOCK_WIDTH,BLOCK_HEIGHT, null);
            }
        }
    }
    private void renderClouds(Graphics g) {
        g.drawImage(Resources.cloud1, (int) cloud1.getX(), (int) cloud1.getY(), 100,60,null);
        g.drawImage(Resources.cloud2, (int) cloud2.getX(), (int) cloud2.getY(), 100,60,null);
        g.drawImage(Resources.cloud3, (int) cloud3.getX(), (int) cloud3.getY(), 100,60,null);
    }

    private void renderPlayer(Graphics g) {
        if(player.isGrounded()) {
            if (player.isDucked()) {
                g.drawImage(Resources.duck, (int) player.getX(), (int) player.getY(),
                        player.getWidth(), player.getHeight(), null);
            } else {
                Resources.runAnim.render(g, (int) player.getX(), (int) player.getY(),
                        player.getWidth(), player.getHeight());
            }
        }else {
            g.drawImage(Resources.jump, (int) player.getX(), (int) player.getY(),
                    player.getWidth(), player.getHeight(), null);
        }
    }

    @Override
    public void onclick(MouseEvent e) {

    }

    @Override
    public void onkeyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            player.jump();
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            player.duck();
        }

    }

    @Override
    public void onkeyRelease(KeyEvent e) {

    }
}
