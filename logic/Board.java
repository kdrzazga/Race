package logic;

import java.awt.Point;
import java.util.ArrayList;
import libs.Math2;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    private static Board instance = null;

    public Board() {
        this.track = new Track();
        this.vehicles = new ArrayList<>();
    }

    public void moveVehicle(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        Point oldPosition = vehicle.v.position;
        
        Point newPosition = findNewPosition(vehicle);

        if (track.pointWithinTrack(newPosition)) {
            vehicle.v.position = newPosition;
            updateVehicleTravelledWayAngle(vehicle, oldPosition, newPosition, track.getTrackCentre());
        }
    }
    
    private void updateVehicleTravelledWayAngle(Vehicle vehicle, Point oldPosition, Point newPosition, Point trackCenter)
    {
         Math2.LineSection lsWithOldPos = new Math2.LineSection(trackCenter, oldPosition);
         Math2.LineSection lsWithNewPos = new Math2.LineSection(trackCenter, newPosition);
         
         double angleWithOldPos = Math2.inclinationAngle(lsWithOldPos);
         double angleWithNewPos = Math2.inclinationAngle(lsWithNewPos);
         
         vehicle.travelledWayAngle += angleWithNewPos - angleWithOldPos;
    }

    public Point getVehiclePosition(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        return vehicle.v.position;
    }

    private Point findNewPosition(Vehicle vehicle) {
        double moveX, moveY, factorX, factorY;
        double cos = Math.cos(vehicle.v.angle);
        double sin = Math.sin(vehicle.v.angle);
        factorX = Math2.round(cos, 4);
        factorY = Math2.round(sin, 4);
        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        int newPositionX = (int) (vehicle.v.position.x + moveX);
        int newPositionY = (int) (vehicle.v.position.y + moveY);

        return new Point(newPositionX, newPositionY);
    }
}
