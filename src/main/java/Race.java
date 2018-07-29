import java.awt.EventQueue;

import static libs.JFrameDialogCommons.setNimbusLookAndFeel;
import presentation.IntroFrame;

public class Race {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        setNimbusLookAndFeel(IntroFrame.class.getName());
        EventQueue.invokeLater(() -> new IntroFrame().setVisible(true));
    }

}
