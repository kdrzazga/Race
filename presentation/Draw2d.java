package presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import libs.math2.LineSection;
import logic.Board;
import logic.Game;
import logic.Track;
import logic.Vehicle;

/*
uses JFrame
 */
public class Draw2d implements IGraphicalOutput {

    private final Graphics g;
    private static final Color[] VEHICLE_COLORS = new Color[7];
    private static final Color TRACK_COLOR = Color.BLACK;
    private static Color BOARD_COLOR = Color.LIGHT_GRAY;

    public Draw2d(Graphics g) {
        this.g = g;
        initVehicleColors();
    }

    private static void initVehicleColors() {
        VEHICLE_COLORS[0] = Color.RED;
        VEHICLE_COLORS[1] = Color.BLUE;
        VEHICLE_COLORS[2] = Color.GREEN;
        VEHICLE_COLORS[3] = Color.MAGENTA;
        VEHICLE_COLORS[4] = Color.ORANGE;
        VEHICLE_COLORS[5] = new Color(162, 42, 42); //brown
        VEHICLE_COLORS[6] = Color.CYAN;
    }

    @Override
    public void drawBoard(Board board) {
        drawTrack(board.track);

        board.vehicles.forEach((vehicle) -> {
            this.drawVehicle(vehicle);
        });
    }

    @Override
    public void drawTrack(Track track) {
        g.setColor(TRACK_COLOR);
        g.drawPolygon(track.outerBound.convertToPolygon());
        g.drawPolygon(track.innerBound.convertToPolygon());
        
         LineSection startLine = track.getRaceStartLine();
        
        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();
        
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
    
    public void drawAllVehicles(Game game)
    {
        game.board.vehicles.forEach((vehicle) -> {
            drawVehicle(vehicle);
        });
    }

    @Override
    public void drawVehicle(Vehicle vehicle) {
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

}
