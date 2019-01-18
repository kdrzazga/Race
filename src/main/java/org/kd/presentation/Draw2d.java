package org.kd.presentation;

import org.kd.libs.math2.LineSection;
import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PolygonAG;
import org.kd.logic.*;

import javax.swing.*;
import java.awt.*;

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
        var g2 = (Graphics2D) g;
        g2.setStroke(THICK_STROKE);
        g.setColor(Color.WHITE);
        
        var outerBorder = track.outerBound.clone();
        outerBorder.scale(1.015f);
         g.drawPolygon(outerBorder.convertToPolygon());
        
        var innerBorder = track.innerBound.clone();
        innerBorder.scale(0.985f);
        g.drawPolygon(innerBorder.convertToPolygon());
    }

    @Override
    public void drawAllVehicles(Board board) {
        board.vehicles.forEach(this::draw);
    }

    @Override
    public void draw(Vehicle vehicle) {
        var vehicleColor = ColorSettings.getVehicleColorById(vehicle.getId());
        drawVehicle(vehicle.v, vehicleColor);
    }

    @Override
    public void clearOutput() {
        Color currentForegrndColor = this.drawablePanel.getForeground();
        Color backgrndColor = this.drawablePanel.getBackground();

        g.setColor(backgrndColor);
        int width = this.drawablePanel.getWidth();
        int height = this.drawablePanel.getHeight();

        g.fillRect(0, 0, width, height);
        g.setColor(currentForegrndColor);
    }

    @Override
    public void eraseVehicles(Board board) {
        g.setColor(ColorSettings.BOARD_COLOR);

        board.vehicles.forEach(vehicle -> {
            VelocityVector previousV = vehicle.previousV;
            float eraseSize = Vehicle.SIZE + VEHICLE_LENGTH;
            var upperLeftCornerX = (int) (previousV.position.getX() - eraseSize / 2);
            var upperLeftCornerY = (int) (previousV.position.getY() - eraseSize / 2);
            float prevVehicleCoverFactor = 1.181f;
            int radius = (int) (prevVehicleCoverFactor * eraseSize);
            g.fillOval(upperLeftCornerX, upperLeftCornerY, radius, radius);
        });
        g.setColor(ColorSettings.TRACK_COLOR);
    }

    public void drawVehicle(VelocityVector vehicleV, Color vehicleColor) {
        var position = vehicleV.position.convertToPoint();

        g.setColor(vehicleColor);
        int centerX = position.x - (Vehicle.SIZE / 2);
        int centerY = position.y - (Vehicle.SIZE / 2);

        g.fillOval(centerX, centerY, Vehicle.SIZE, Vehicle.SIZE);
        float factorX;
        float factorY;

        factorX = Numbers.roundToFloat(Math.cos(vehicleV.angle));
        factorY = Numbers.roundToFloat(Math.sin(vehicleV.angle));

        int deltaX;
        int deltaY;

        deltaX = (int) Math.ceil(factorX * VEHICLE_LENGTH);
        deltaY = (int) Math.ceil(factorY * VEHICLE_LENGTH);

        g.drawLine(position.x, position.y, position.x + deltaX, position.y + deltaY);
    }

    @Override
    public void drawStartLine(Track track) {
        LineSection startLine = track.computeVerticalStartLine();

        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();

        var g2 = (Graphics2D) g;
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
