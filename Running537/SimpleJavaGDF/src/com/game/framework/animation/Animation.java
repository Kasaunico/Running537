package com.game.framework.animation;

import java.awt.Graphics;
import java.awt.Image;

public class Animation {
    private Frame[] frames;
    private double[] frameEndTimes;
    private int currentFrameIndex;

    private double totalDuration;
    private double currentTime;

    public Animation(Frame...frames){
        this.frames = frames;
        frameEndTimes = new double[frames.length];
        for(int i = 0; i < frames.length; i++){
            Frame f=frames[i];
            totalDuration += f.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }

    public synchronized void update(float increment){
        currentTime += increment;
        if(currentTime > totalDuration)
            wrapAnimation();
        while(currentTime > frameEndTimes[currentFrameIndex])
            currentFrameIndex++;
    }

    private void wrapAnimation(){
        currentFrameIndex = 0;
        currentTime%=totalDuration;
    }

    public synchronized void render(Graphics g, int x, int y){
        Frame f = frames[currentFrameIndex];
        g.drawImage(f.getImage(), x, y, null);
    }

    public synchronized void render(Graphics g, int x, int y , int width, int height){
        Frame f = frames[currentFrameIndex];
        g.drawImage(f.getImage(), x, y, width, height, null);
    }
}
