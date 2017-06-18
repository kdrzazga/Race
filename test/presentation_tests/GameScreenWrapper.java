package presentation_tests;

import logic.Game;
import presentation.GameScreen;

public class GameScreenWrapper extends GameScreen {

    public GameScreenWrapper(IntroFrameWrapper introFrame, Game game) {
        super(introFrame, game);
        this.drawOutput = new Draw2dWrapper(this.pnlBoard);
    }
}
