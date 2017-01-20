package miscallenous;

import javax.swing.UIManager;

public class JFrameCommons {
    public static void setNimbusLookAndFeel(String className) {
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(className).log(java.util.logging.Level.SEVERE, null, ex);
        }       
    }
}
