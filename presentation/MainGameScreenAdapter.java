package presentation;

import logic.Game;
import presentation.test.GameTest;

class MainGameScreenAdapter extends java.awt.event.WindowAdapter {

    private final GameTest introFrame;
    private final Game game;
    
    public MainGameScreenAdapter(GameTest introFrame, Game game)
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
 