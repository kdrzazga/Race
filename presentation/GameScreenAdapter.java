package presentation;

import java.awt.event.WindowAdapter;

import logic.Game;
import presentation.test.GameTest;

class GameScreenAdapter extends WindowAdapter {

    private final GameTest introFrame;
    private final Game game;
    
    public GameScreenAdapter(GameTest introFrame, Game game)
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
 