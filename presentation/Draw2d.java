package presentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Draw2d implements IGraphicalOutput {

    private JPanel drawablePanel;
    private Graphics g;
    private static final int TRACK_LINE_THICKNESS = 3;
    private static final int DEFAULT_LINE_THICKNESS = 2;

    private final BasicStroke THICK_STROKE = new BasicStroke(TRACK_LINE_THICKNESS, BasicStroke.CAP_SQUARE, // End cap
            BasicStroke.JOIN_MITER, 10.0f, // Miter limit
            new float[]{16.0f, 16.0f}, // Dash pattern
            0.0f);

    public Draw2d(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
        this.g = drawablePanel.getGraphics();
    }

    @Override
    public void draw(Board board) {
        draw(board.track);
        drawAllVehicles(board);
    }

    @Override
    public void draw(Track track) {
        g.setColor(ColorSettings.TRACK_COLOR);

        g.fillPolygon(track.outerBound.convertToPolygon());

        drawTrackBorder(track);

        g.setColor(ColorSettings.BOARD_COLOR);
        g.fillPolygon(track.innerBound.convertToPolygon());

        LineSection startLine = track.computeVerticalStartLine();

        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(DEFAULT_LINE_THICKNESS));
        g.setColor(ColorSettings.START_LINE_COLOR);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);

        /*Point trackCenter = track.computeCenter().convertToPoint();
        g.drawOval(trackCenter.x - 2, trackCenter.y - 2, 4, 4);*/
    }

    @Override
    public void drawTrackBorder(Track track) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(THICK_STROKE);
        g.setColor(Color.WHITE);
        g.drawPolygon(track.outerBound.convertToPolygon());
        g.drawPolygon(track.innerBound.convertToPolygon());
    }

    @Override
    public void drawAllVehicles(Board board) {
        board.vehicles.forEach((vehicle) -> {
            draw(vehicle);
        });
    }

    @Override
    public void draw(Vehicle vehicle) {
        Color vehicleColor = ColorSettings.getVehicleColorById(vehicle.getId());
        drawVehicle(vehicle.v.position.convertToPoint(), vehicleColor);
    }

    @Override
    public void clearOutput() {
        Color currentforegroundColor = this.drawablePanel.getForeground();
        Color backgroundColor = this.drawablePanel.getBackground();

        g.setColor(backgroundColor);
        int width = this.drawablePanel.getWidth();
        int height = this.drawablePanel.getHeight();

        g.fillRect(0, 0, width, height);
        g.setColor(currentforegroundColor);
    }

    @Override
    public void eraseVehicles(Board board) {
        Color backgroundColor = ColorSettings.TRACK_COLOR;

        board.vehicles.forEach((vehicle) -> {
            drawVehicle(vehicle.previousLocation.convertToPoint(), backgroundColor);
        });
        g.setColor(ColorSettings.BOARD_COLOR);
    }

    public void drawVehicle(Point vehiclePos, Color vehicleColor) {
        final int  VEHICLE_SIZE = 8;
        g.setColor(vehicleColor);
        g.fillOval(vehiclePos.x - (int)(VEHICLE_SIZE / 2), vehiclePos.y - (int)(VEHICLE_SIZE / 2)
                , VEHICLE_SIZE, VEHICLE_SIZE);
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
        this.g = this.drawablePanel.getGraphics();
    }
}
