package com.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import com.game.framework.animation.Animation;
import com.game.framework.animation.Frame;

import javax.imageio.ImageIO;

public class Resources {

    public static BufferedImage welcome, iconimage,selector,grass,duck,jump,cloud1,cloud2,cloud3,block;

    public static AudioClip onjump, onduck,hit;

    public static Color skyBlue;

    public static Animation runAnim;

    public static void load() {
        welcome = loadImage("welcome.png");
        iconimage = loadImage("iconimage.png");
        selector = loadImage("selector.png");
        grass = loadImage("grass.png");
        skyBlue = new Color(208,244,247);
        duck=loadImage("duck.png");
        jump=loadImage("jump.png");
        onjump = loadAudio("onjump.wav");
        onduck = loadAudio("onduck.wav");
        hit = loadAudio("hit.wav");
        cloud1=loadImage("cloud1.png");
        cloud2=loadImage("cloud2.png");
        cloud3=loadImage("yjr.png");
        block=loadImage("block.png");

        Frame f1=new Frame(loadImage("run_anim1.png"), 0.1f);
        Frame f2=new Frame(loadImage("run_anim2.png"), 0.1f);
        Frame f3=new Frame(loadImage("run_anim3.png"), 0.1f);
        Frame f4=new Frame(loadImage("run_anim4.png"), 0.1f);
        Frame f5=new Frame(loadImage("run_anim5.png"), 0.1f);

        runAnim = new Animation(f1, f2, f3, f4, f5,f3,f2);

    }

    private static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
        } catch (Exception e) {
            System.out.printf("读取图片失败:" + filename);
        }
        return img;
    }

    private static AudioClip loadAudio(String filename) {
        URL fileURL = Resources.class.getResource("/resources/" + filename);
        return Applet.newAudioClip(fileURL);
    }
}
