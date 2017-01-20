
import java.awt.EventQueue;

import static miscallenous.JFrameCommons.setNimbusLookAndFeel;
import presentation.test.IntroFrame;

public class Race {

    public static void main(String args) {
        startGame();
    }

    private static void startGame() {
        setNimbusLookAndFeel(IntroFrame.class.getName());

        EventQueue.invokeLater(() -> {
            new IntroFrame().setVisible(true);
        });
    }

}
