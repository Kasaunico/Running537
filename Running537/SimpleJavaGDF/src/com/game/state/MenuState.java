package com.game.state;

import com.game.main.GameMain;
import com.game.main.Resources;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public class MenuState extends State{

    private int currentSelection=0;
    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {
        //System.out.println("经过时间"+delta+"秒");
        Resources.runAnim.update(delta);
    }

    @Override
    public void render(Graphics g) {
    g.drawImage(Resources.welcome,0,0,null);



    if(currentSelection==0)
        Resources.runAnim.render(g, 480,271);
    else
        Resources.runAnim.render(g, 480,341);
    }

    @Override
    public void onclick(MouseEvent e) {
        //System.out.println("点击了鼠标");
    }

    @Override
    public void onkeyPress(KeyEvent e) {
        //System.out.printf("按下键盘按键");

        int key=e.getKeyCode();
        if(key==KeyEvent.VK_UP||key==KeyEvent.VK_W)
            currentSelection=0;
        else if(key==KeyEvent.VK_DOWN||key==KeyEvent.VK_S)
            currentSelection=1;
        else if(key==KeyEvent.VK_SPACE||key==KeyEvent.VK_ENTER){
            if(currentSelection==1)
                GameMain.sGame.exit();
            else
                setCurrentState(new PlayState());
        }

    }

    @Override
    public void onkeyRelease(KeyEvent e) {
        //System.out.println("松开键盘按键");
    }
}
