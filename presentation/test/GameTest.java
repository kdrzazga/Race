package presentation.test;

import static libs.JFrameDialogCommons.setNimbusLookAndFeel;
import presentation.IntroFrame;

public class GameTest {
    public static void main(String args[]) {
        setNimbusLookAndFeel(IntroFrame.class.getName());

        java.awt.EventQueue.invokeLater(() -> {
            new IntroFrame().setVisible(true);
        });
    }
}
