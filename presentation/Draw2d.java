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
        int trackLineThickness = 5;
        int defaultLineThickness = 1;

        g.setColor(ColorSettings.TRACK_COLOR);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(trackLineThickness));

        g.drawPolygon(track.outerBound.convertToPolygon());
        g.drawPolygon(track.innerBound.convertToPolygon());

        LineSection startLine = track.computeVerticalStartLine();

        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();

        g2.setStroke(new BasicStroke(defaultLineThickness));
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

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
        Color currentforegroundColor = this.drawablePanel.getForeground();
        Color backgroundColor = this.drawablePanel.getBackground();

        board.vehicles.forEach((vehicle) -> {
            drawVehicle(vehicle.previousLocation.convertToPoint(), backgroundColor);
        });
        g.setColor(currentforegroundColor);
    }

    public void drawVehicle(Point vehiclePos, Color vehicleColor) {
        g.setColor(vehicleColor);
        g.fillOval(vehiclePos.x - 2, vehiclePos.y - 2, 4, 4);
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
        this.g = this.drawablePanel.getGraphics();
    }
}
