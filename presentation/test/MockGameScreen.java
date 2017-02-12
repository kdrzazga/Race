package presentation.test;

import logic.Game;
import presentation.GameScreen;

public class MockGameScreen extends GameScreen {

    public MockGameScreen(MockIntroFrame introFrame, Game game) {
        super(introFrame, game);
        this.drawOutput = new MockDraw2d(this.pnlBoard);
    }
}
