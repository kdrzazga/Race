package presentation;

import java.awt.Color;
import static org.lwjgl.opengl.GL11.glColor3f;

public class ColorSettings {

    public static final Color[] VEHICLE_COLORS = {Color.RED, Color.BLUE, Color.GREEN,
        Color.MAGENTA, Color.ORANGE, Color.CYAN,
        new Color(162, 42, 42)};//brown

    public static final float OPEN_GL_COLORS[][] = {{1f, 0f, 0f}, {0f, 0f, 1f}, {0f, 1f, 0f},
    {1f, 0f, 1f}, {1f, 0.9f, 0.2f}, {0f, 1f, 1f},
     {1f, 0.37f, 0f}
    };

    public static final Color TRACK_COLOR = Color.BLACK;

    public static Color BOARD_COLOR = Color.LIGHT_GRAY;

    public static void glColor(Color awtColor) {

        for (int i = 0; i < VEHICLE_COLORS.length; i++) {

            if (awtColor.equals(VEHICLE_COLORS[i])) {

                float r = OPEN_GL_COLORS[i][0];
                float g = OPEN_GL_COLORS[i][1];
                float b = OPEN_GL_COLORS[i][2];
                glColor3f(r, g, b);
            }
        }
    }

    public static Color getVehicleColorById(int id) {
        int maxColorIndex = VEHICLE_COLORS.length - 1;
        return VEHICLE_COLORS[id % maxColorIndex];
    }

    public static void setBOARD_COLOR(Color aBOARD_COLOR) {
        BOARD_COLOR = aBOARD_COLOR;
    }
}
