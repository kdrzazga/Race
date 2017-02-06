package presentation.test;

import static libs.JFrameDialogCommons.setNimbusLookAndFeel;

public class GameTest {
    public static void main(String args[]) {
        setNimbusLookAndFeel(MockIntroFrame.class.getName());

        java.awt.EventQueue.invokeLater(() -> {
            new MockIntroFrame().setVisible(true);
        });
    }
}
