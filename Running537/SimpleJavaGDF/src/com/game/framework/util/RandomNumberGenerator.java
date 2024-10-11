package com.game.framework.util;

import java.util.Random;
public class RandomNumberGenerator {
    private static Random rand = new Random();

    public static int getRandIntBetween(int lowBound, int upperBound) {
        return rand.nextInt(upperBound - lowBound ) + lowBound;
}

    public static int getRandomInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
