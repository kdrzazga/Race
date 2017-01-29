package presentation;

import java.awt.Color;
//import static org.lwjgl.opengl.GL11.glColor3f;

public class ColorSettings {

    private final static Color DARK_GREEN = new Color(0, 120, 0);
    private final static Color VIOLET = new Color(180, 0, 180);
    private final static Color YELLOW_GREEN = new Color(135, 155, 21);
    private final static Color BROWN = new Color(152, 22, 22);
    private final static Color DARK_CYAN = new Color(1, 129, 128);
    private final static Color DARK_ORANGE = new Color(207, 132, 0);

    public static final Color[] AWT_VEHICLE_COLORS = {Color.RED, Color.BLUE, DARK_GREEN,
        VIOLET, Color.DARK_GRAY, YELLOW_GREEN, BROWN, DARK_CYAN, DARK_ORANGE};

    public static final float[][] OPEN_GL_VEHICLE_COLORS = {{1f, 0f, 0f}, {0f, 0f, 1f}, {0f, 1f, 0f},
    {1f, 0f, 1f}, {1f, 0.9f, 0.2f}, {0f, 1f, 1f}, {1f, 0.37f, 0f}
    };

    public static Color TRACK_COLOR = Color.BLACK;

    public static Color BOARD_COLOR = new Color(204, 255, 204);

    /*
    public static void glColor(Color awtColor) {

        for (int i = 0; i < AWT_VEHICLE_COLORS.length; i++) {

            if (awtColor.equals(AWT_VEHICLE_COLORS[i])) {

                float r = OPEN_GL_VEHICLE_COLORS[i][0];
                float g = OPEN_GL_VEHICLE_COLORS[i][1];
                float b = OPEN_GL_VEHICLE_COLORS[i][2];
                glColor3f(r, g, b);
            }
        }
    }*/
    public static Color getVehicleColorById(int id) {
        int maxColorIndex = AWT_VEHICLE_COLORS.length;
        return AWT_VEHICLE_COLORS[id % maxColorIndex];
    }
}
