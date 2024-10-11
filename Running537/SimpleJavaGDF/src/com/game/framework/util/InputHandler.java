package com.game.framework.util;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.state.State;

public class InputHandler implements MouseListener,KeyListener {

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        currentState.onkeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentState.onkeyRelease(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentState.onclick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
