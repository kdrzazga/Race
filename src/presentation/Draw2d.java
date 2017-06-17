package presentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import libs.math2.Numbers;

import libs.math2.LineSection;
import libs.math2.PolygonAG;
import logic.Board;
import logic.IGraphicalOutput;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;

/*
uses JFrame
 */
public class Draw2d implements IGraphicalOutput {

    protected JPanel drawablePanel;
    protected Graphics g;
    private static final int TRACK_LINE_THICKNESS = 3;
    private static final int DEFAULT_LINE_THICKNESS = 2;
    private static final float VEHICLE_LENGTH = 0.6f * Vehicle.SIZE;

    private final BasicStroke THICK_STROKE = new BasicStroke(TRACK_LINE_THICKNESS, BasicStroke.CAP_SQUARE, // End cap
            BasicStroke.JOIN_MITER, 10.0f, /* Miter limit*/ new float[]{16.0f, 16.0f}, // Dash pattern
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

        drawStartLine(track);
    }

    @Override
    public void drawTrackBorder(Track track) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(THICK_STROKE);
        g.setColor(Color.WHITE);
        
        PolygonAG outerBorder = track.outerBound.clone();
        outerBorder.scale(1.015f);
         g.drawPolygon(outerBorder.convertToPolygon());
        
        PolygonAG innerBorder = track.innerBound.clone();
        innerBorder.scale(0.985f);
        g.drawPolygon(innerBorder.convertToPolygon());
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
        drawVehicle(vehicle.v, vehicleColor);
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
        g.setColor(backgroundColor);

        board.vehicles.forEach((vehicle) -> {
            VelocityVector previousV = vehicle.previousV;
            float eraseSize = Vehicle.SIZE + VEHICLE_LENGTH;
            int upperLeftCornerX = (int) (previousV.position.x - eraseSize / 2);
            int upperLeftCornerY = (int) (previousV.position.y - eraseSize / 2);
            float prevVehicleCoverFactor = 1.181f;
            int radius = (int) (prevVehicleCoverFactor * eraseSize);
            g.fillOval(upperLeftCornerX, upperLeftCornerY, radius, radius);
        });
        g.setColor(ColorSettings.BOARD_COLOR);
    }

    public void drawVehicle(VelocityVector vehicleV, Color vehicleColor) {
        Point position = vehicleV.position.convertToPoint();

        g.setColor(vehicleColor);
        int centerX = position.x - (int) (Vehicle.SIZE / 2);
        int centerY = position.y - (int) (Vehicle.SIZE / 2);

        g.fillOval(centerX, centerY, Vehicle.SIZE, Vehicle.SIZE);
        float factorX, factorY;

        factorX = Numbers.roundToFloat(Math.cos(vehicleV.angle));
        factorY = Numbers.roundToFloat(Math.sin(vehicleV.angle));

        int deltaX, deltaY;

        deltaX = (int) Math.ceil(factorX * VEHICLE_LENGTH);
        deltaY = (int) Math.ceil(factorY * VEHICLE_LENGTH);

        g.drawLine(position.x, position.y, position.x + deltaX, position.y + deltaY);
    }

    @Override
    public void drawStartLine(Track track) {
        LineSection startLine = track.computeVerticalStartLine();

        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(DEFAULT_LINE_THICKNESS));
        g.setColor(ColorSettings.START_LINE_COLOR);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
        this.g = this.drawablePanel.getGraphics();
    }
}
