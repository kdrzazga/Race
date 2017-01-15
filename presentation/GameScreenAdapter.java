package presentation;

import logic.Game;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GameScreenAdapter extends WindowAdapter {

    private final IntroFrame introFrame;
    //private final GameScreen gameScreen;
    private Game game;
    
    public GameScreenAdapter(IntroFrame introFrame, GameScreen gameScreen)
    {
        this.introFrame = introFrame;
        //this.gameScreen = gameScreen;
    }
    
    public void init(Game game)
    {
        this.game = game;
    }
     
    @Override
    public void windowClosing(WindowEvent evt) {
        this.introFrame.setVisible(true);
        //this.gameScreen.setVisible(false);
        this.game.setGameRunning(false);
    }
}
 