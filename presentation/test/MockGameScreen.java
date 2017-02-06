package presentation.test;

import logic.Game;
import presentation.GameScreen;
import presentation.IntroFrame;

public class MockGameScreen extends GameScreen{

    public MockGameScreen(IntroFrame introFrame, Game game) {
        super(introFrame, game);
        this.drawOutput = new MockDraw2d(this.pnlBoard);
    }

}
