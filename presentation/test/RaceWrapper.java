package presentation.test;

import static libs.JFrameDialogCommons.setNimbusLookAndFeel;

public class RaceWrapper {
    public static void main(String args[]) {
        setNimbusLookAndFeel(IntroFrameWrapper.class.getName());

        java.awt.EventQueue.invokeLater(() -> {
            new IntroFrameWrapper().setVisible(true);
        });
    }
}
