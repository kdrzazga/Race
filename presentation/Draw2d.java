package presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import logic.Board;
import logic.Track;
import logic.Vehicle;

/*
uses JFrame
 */
public class Draw2d implements IGraphicalOutput {

    private final Graphics g;
    private static final Color[] VEHICLE_COLORS = new Color[7];
    private static final Color TRACK_COLOR = Color.BLACK;
        
    public Draw2d(Graphics g) {
        this.g = g;
        initVehicleColors();
    }
    
    private static void initVehicleColors()
    {
        VEHICLE_COLORS[0] = Color.RED;
        VEHICLE_COLORS[1] = Color.BLUE;
        VEHICLE_COLORS[2] = Color.GREEN;
        VEHICLE_COLORS[3] = Color.MAGENTA;
        VEHICLE_COLORS[4] = Color.ORANGE;
        VEHICLE_COLORS[5] = new Color(162,42,42); //brown
        VEHICLE_COLORS[6] = Color.CYAN;
    }

    @Override
    public void drawBoard(Board board) {
        drawTrack(board.track);
        
        for(Vehicle vehicle: board.vehicles)
        {
            this.drawVehicle(vehicle);
        }
    }

    @Override
    public void drawTrack(Track track) {
        g.setColor(TRACK_COLOR);
        g.drawPolygon(track.outerBound);
        g.drawPolygon(track.innerBound);        
    }

    @Override
    public void drawVehicle(Vehicle vehicle) {        
        int maxColorIndex = VEHICLE_COLORS.length - 1;
        Color drawingColor = VEHICLE_COLORS[vehicle.getId() % maxColorIndex];
        g.setColor(drawingColor);
        
        Point vehiclePos = vehicle.v.position;
        
        g.drawOval(vehiclePos.x - 2, vehiclePos.y - 2, 4, 4);
    }
}
