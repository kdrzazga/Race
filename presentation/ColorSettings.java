package presentation;

import java.awt.Color;
//import static org.lwjgl.opengl.GL11.glColor3f;

public class ColorSettings {

    private final static Color DARK_GREEN = new Color(0, 120, 0);
    private final static Color VIOLET = new Color(180, 0, 180);
    private final static Color YELLOW_GREEN = new Color(135, 155, 21);
    private final static Color BROWN = new Color(142, 25, 25);
    private final static Color LIGHT_BROWN = new Color(232, 102, 102);
    private final static Color LIGHT_BLUE = new Color(115, 115, 255);
    private final static Color DARK_CYAN = new Color(1, 129, 128);
    private final static Color DARK_ORANGE = new Color(207, 132, 0);

    public static final Color[] AWT_VEHICLE_COLORS = { Color.CYAN, Color.WHITE,Color.RED, Color.YELLOW,
         LIGHT_BLUE, Color.PINK, Color.MAGENTA, LIGHT_BROWN, Color.GREEN};

    public static final float[][] OPEN_GL_VEHICLE_COLORS = {{1f, 0f, 0f}, {0f, 0f, 1f}, {0f, 1f, 0f},
    {1f, 0f, 1f}, {1f, 0.9f, 0.2f}, {0f, 1f, 1f}, {1f, 0.37f, 0f}
    };

    public static Color BOARD_COLOR = Color.LIGHT_GRAY;
    public static Color TRACK_COLOR = Color.GRAY;
    public static Color START_LINE_COLOR = Color.LIGHT_GRAY;
    public static Color INFO_PANEL_COLOR = BROWN;

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
