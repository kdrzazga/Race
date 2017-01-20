package presentation;

import java.awt.event.WindowAdapter;

import logic.Game;
import presentation.test.IntroFrame;

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
 