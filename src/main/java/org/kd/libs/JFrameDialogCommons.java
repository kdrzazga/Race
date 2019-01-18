package org.kd.libs;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFrameDialogCommons {

    private JFrameDialogCommons() {
    }

    public static void setNimbusLookAndFeel(String className) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(className).log(Level.SEVERE, null, ex);
        }
    }
}
