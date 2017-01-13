package presentation;

import logic.Game;

public class MainGameScreenAdapter extends java.awt.event.WindowAdapter {

    private final IntroFrame introFrame;
    private Game game;
    
    public MainGameScreenAdapter(IntroFrame introFrame)
    {
        this.introFrame = introFrame;
    }
    
    public void init(Game game)
    {
        this.game = game;
    }
    
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        System.out.println(this.introFrame);
        System.out.println(this.game);
        
        this.introFrame.setVisible(true);
        this.game.setGameRunning(false);
    }
}
 