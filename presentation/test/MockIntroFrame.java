package presentation.test;

import logic.Board;
import logic.BoardBuilder;
import logic.BoardBuilder.TrackType;
import presentation.IntroFrame;

public class MockIntroFrame extends IntroFrame {

    private TrackType weraTrackType;
    private TrackType testRectangularTrackType;

    @Override
    protected void initComponents2() {
        super.initComponents2();
        this.weraTrackType = BoardBuilder.TrackType.WERONIKA;
        this.testRectangularTrackType = BoardBuilder.TrackType.TEST_RECTANGULAR;
        cbTrack.addItem(weraTrackType.toString());
        cbTrack.addItem(testRectangularTrackType.toString());
    }

    @Override
    protected Board createBoardBasedOnSelectedUiItems() {
        int numberOfVehicles = readSelectedNumberOfVehicles();        
        Board board;
        String selectedTrack = (String) this.cbTrack.getSelectedItem();

        if (selectedTrack.equals(weraTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.WERONIKA);
        } else if (selectedTrack.equals(testRectangularTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.TEST_RECTANGULAR);
        } else {
            board = super.createBoardBasedOnSelectedUiItems();
        }

        return board;
    }

    @Override
    protected void btnStartActionPerformed(java.awt.event.ActionEvent evt
    ) {
        game.board = createBoardBasedOnSelectedUiItems();
        gameScreen = new MockGameScreen(this, game);
        startGame(gameScreen);
    }
}
