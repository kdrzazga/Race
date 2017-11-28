package libs;

import java.awt.*;
import java.util.function.Function;
//import static org.lwjgl.opengl.GL11.glColor3f;

public class Colors {

    private static final Color DARK_GREEN = new Color(0, 120, 0);
    private static final Color VIOLET = new Color(180, 0, 180);
    private static final Color YELLOW_GREEN = new Color(135, 155, 21);
    public static final Color BROWN = new Color(142, 25, 25);
    private static final Color LIGHT_BROWN = new Color(232, 102, 102);
    private static final Color LIGHT_BLUE = new Color(115, 115, 255);
    private static final Color DARK_CYAN = new Color(1, 129, 128);
    private static final Color DARK_ORANGE = new Color(207, 132, 0);

    public static final Color[] LIGHT_COLORS = {Color.CYAN, Color.WHITE, Color.RED,
            Color.YELLOW, LIGHT_BLUE, Color.PINK, Color.MAGENTA, LIGHT_BROWN,
            YELLOW_GREEN, Color.GREEN, BROWN, Color.LIGHT_GRAY, Color.ORANGE};

    public static final Color[] DARK_COLORS = {Color.BLACK, Color.BLUE, Color.DARK_GRAY, DARK_CYAN,
            VIOLET, DARK_ORANGE, Color.GRAY, DARK_GREEN};

    public static final float[][] OPEN_GL_COLORS = {{0f, 1f, 1f}, {1f, 1f, 1f}, {1f, 0f, 0f},
            {1f, 1f, 0f}, {45f, 0.45f, 1f}, {0.6f, 0f, 0f}, {1f, 0f, 1f}, {0.91f, 0.4f, 0.4f},
            {0.53f, 0.608f, 0.824f}, {0f, 1f, 0f}, {0.557f, 0.98f, 0.98f}
    };

    public final static Function<Integer, Color> getDarkColor =  index
            -> DARK_COLORS[index % (DARK_COLORS.length - 1)];

    public final static Function<Integer, Color> getLightColor = index -> LIGHT_COLORS[index % (LIGHT_COLORS.length - 1)];

    public final static Function<Integer, Color> getColor = index-> {
        index %= (LIGHT_COLORS.length + DARK_COLORS.length - 2);

        if (index < LIGHT_COLORS.length) {
            return getLightColor.apply(index);
        } else {
            return getDarkColor.apply(index - LIGHT_COLORS.length);
        }

    };
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

}
