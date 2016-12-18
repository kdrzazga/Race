package logic;

import java.awt.Point;
import java.util.ArrayList;
import libs.math2.General;
import libs.math2.LineSection;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    public Board() {
        this.track = new Track();
        this.vehicles = new ArrayList<>();
    }

    public Board(int numberOfVehicles) {
        this.track = new Track();
        this.vehicles = new ArrayList<>();
        
        for(int i = 0; i < numberOfVehicles; i++)
        {
            vehicles.add(new Vehicle(i));
        }
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
         LineSection lsWithOldPos;
         lsWithOldPos = new LineSection(trackCenter, oldPosition);
         LineSection lsWithNewPos = new LineSection(trackCenter, newPosition);
         
         double angleWithOldPos = General.inclinationAngle(lsWithOldPos);
         double angleWithNewPos = General.inclinationAngle(lsWithNewPos);
         
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
        factorX = General.roundToDouble(cos, 4);
        factorY = General.roundToDouble(sin, 4);
        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        int newPositionX = (int) (vehicle.v.position.x + moveX);
        int newPositionY = (int) (vehicle.v.position.y + moveY);

        return new Point(newPositionX, newPositionY);
    }
}
