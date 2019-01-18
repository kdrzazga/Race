package org.kd.presentation;

import org.kd.logic.Board;
import org.kd.logic.IGraphicalOutput;
import org.kd.logic.creation.BoardBuilder;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Map;

import static org.kd.logic.creation.BoardBuilder.TrackType;

public final class IntroFrameInitializer {

    static IGraphicalOutput createGraphicalOutputBasedOnSelectedGraphics(JPanel drawablePanel, IntroFrame introFrame) {
        var selectedGraphics = introFrame.cbGraphics.getSelectedItem().toString();

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

        var selectedTrack = introFrame.cbTrack.getSelectedItem().toString();

        Map<String, TrackType> selectedTrackMap = Map.of(
                introFrame.rectTrackType.toString(), TrackType.RECTANGULAR,
                introFrame.circularTrackType.toString(), TrackType.DONUT,
                introFrame.kidneyTrackType.toString(), TrackType.KIDNEY,
                introFrame.triangleTrackType.toString(), TrackType.TRIANGLE,
                introFrame.pentagonTrackType.toString(), TrackType.PENTAGON,
                introFrame.sineTrackType.toString(), TrackType.SINE
        );

        return new BoardBuilder()
                .createBoard()
                .withTrack(selectedTrackMap.get(selectedTrack))
                .withVehicles(numberOfVehicles)
                .build();
    }

    private static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            var button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return "";
    }

    private static int readSelectedNumberOfVehicles(IntroFrame introFrame) throws NumberFormatException {
        var vehCount = introFrame.cbPlayers.getSelectedItem().toString();
        return Integer.parseInt(vehCount);
    }

}
