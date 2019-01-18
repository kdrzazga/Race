package org.kd.presentation;

import java.awt.*;

import static org.kd.libs.Colors.BROWN;

public class ColorSettings {

    public static final Color[] AWT_VEHICLE_COLORS = org.kd.libs.Colors.LIGHT_COLORS;
    public static final float[][] OPEN_GL_VEHICLE_COLORS = org.kd.libs.Colors.OPEN_GL_COLORS;
    
    public static final Color BOARD_COLOR = Color.LIGHT_GRAY;
    public static final Color TRACK_COLOR = Color.GRAY;
    public static final Color START_LINE_COLOR = Color.RED;
    public static final Color INFO_PANEL_COLOR = BROWN;

    public static Color getVehicleColorById(int id) {
        int maxColorIndex = AWT_VEHICLE_COLORS.length;
        return AWT_VEHICLE_COLORS[id % maxColorIndex];
    }
}
