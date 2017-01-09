package presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import libs.math2.LineSection;
import logic.Board;
import logic.IGraphicalOutput;
import logic.Track;
import logic.Vehicle;

/*
uses JFrame
 */
public final class Draw2d implements IGraphicalOutput {

    private JPanel drawablePanel;
    private Graphics g;
    private static final Color[] VEHICLE_COLORS = {Color.RED, Color.BLUE, Color.GREEN,
        Color.MAGENTA, Color.ORANGE, Color.CYAN,
        new Color(162, 42, 42)};//brown
    private static final Color TRACK_COLOR = Color.BLACK;
    private static Color BOARD_COLOR = Color.LIGHT_GRAY;

    public Draw2d(JPanel drawablePanel) {
        this.setPanelToDrawOn(drawablePanel);
    }

    @Override
    public void draw(Board board) {
        draw(board.track);
        drawAllVehicles(board);
    }

    @Override
    public void draw(Track track) {
        g.setColor(TRACK_COLOR);
        g.drawPolygon(track.outerBound.convertToPolygon());
        g.drawPolygon(track.innerBound.convertToPolygon());

        LineSection startLine = track.getRaceStartLine();

        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();

        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public void drawAllVehicles(Board board) {
        board.vehicles.forEach((vehicle) -> {
            draw(vehicle);
        });
    }

    @Override
    public void draw(Vehicle vehicle) {
        eraseSurrounding(vehicle);

        int maxColorIndex = VEHICLE_COLORS.length - 1;
        Color drawingColor = VEHICLE_COLORS[vehicle.getId() % maxColorIndex];
        g.setColor(drawingColor);

        Point vehiclePos = vehicle.v.position.convertToPoint();

        g.drawOval(vehiclePos.x - 2, vehiclePos.y - 2, 4, 4);
    }

    private void eraseSurrounding(Vehicle vehicle) {
        g.setColor(BOARD_COLOR);

        Point vehiclePos = vehicle.v.position.convertToPoint();
        g.drawOval(vehiclePos.x - 3, vehiclePos.y - 3, 6, 6);
    }

    public static void setBOARD_COLOR(Color aBOARD_COLOR) {
        BOARD_COLOR = aBOARD_COLOR;
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
                this.drawablePanel = drawablePanel;
        this.g = this.drawablePanel.getGraphics();
    }

}
