package org.kd;

import org.kd.libs.JFrameDialogCommons;
import org.kd.presentation.IntroFrame;

import java.awt.*;

import static org.kd.libs.JFrameDialogCommons.setNimbusLookAndFeel;

public class Race {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        JFrameDialogCommons.setNimbusLookAndFeel(IntroFrame.class.getName());
        EventQueue.invokeLater(() -> new IntroFrame().setVisible(true));
    }

}
