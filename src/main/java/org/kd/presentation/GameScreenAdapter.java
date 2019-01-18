package org.kd.presentation;

import org.kd.logic.Game;

import java.awt.event.WindowAdapter;

class GameScreenAdapter extends WindowAdapter {

    private final IntroFrame introFrame;
    private final Game game;
    
    public GameScreenAdapter(IntroFrame introFrame, Game game)
    {
        this.introFrame = introFrame;
        this.game = game;
    }
    
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        this.introFrame.setVisible(true);
        this.game.setGameRunning(false);
    }
}
 