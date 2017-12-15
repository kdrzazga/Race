package presentation;

import logic.Board;
import logic.BoardBuilder;
import logic.IGraphicalOutput;

import javax.swing.*;
import java.util.Enumeration;

public final class IntroFrameInitializer {

    static IGraphicalOutput createGraphicalOutputBasedOnSelectedGraphics(JPanel drawablePanel, IntroFrame introFrame) {
        String selectedGraphics = (String) introFrame.cbGraphics.getSelectedItem();

        if (selectedGraphics.equals("2D")) {
            return new Draw2d(drawablePanel);
        } else {
            return new Draw3d(drawablePanel);
        }
    }

    static Board createBoardBasedOnSelectedUiItems(IntroFrame introFrame) {
        int numberOfVehicles = readSelectedNumberOfVehicles(introFrame);

        int lapsToWin = Integer.parseInt(getSelectedButtonText(introFrame.lapRadioButtonsGroup));

        introFrame.game.reset(lapsToWin);
        Board board;
        String selectedTrack = (String) introFrame.cbTrack.getSelectedItem();

        if (selectedTrack.equals(introFrame.rectTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.RECTANGULAR);
        } else if (selectedTrack.equals(introFrame.circularTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.DONUT);
        } else if (selectedTrack.equals(introFrame.kidneyTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.KIDNEY);
        } else if (selectedTrack.equals(introFrame.triangleTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.TRIANGLE);
        } else if (selectedTrack.equals(introFrame.pentagonTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.PENTAGON);
        } else// if (selectedTrack.equals(sineTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.SINE);
        }
        return board;
    }

    private static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return "";
    }

    private static int readSelectedNumberOfVehicles(IntroFrame introFrame) throws NumberFormatException {
        String vehCount = introFrame.cbPlayers.getSelectedItem().toString();
        return Integer.parseInt(vehCount);
    }

}
