package logic;

import java.awt.Point;
import java.util.ArrayList;
import libs.math2.General;
import libs.math2.LineSection;
import libs.math2.PointAG;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;
    public static final int ROUNDING_PRECISION = 4;

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
        PointAG oldPosition = vehicle.v.position;
        
        PointAG newPosition = findNewPosition(vehicle);

        if (track.pointWithinTrack(newPosition)) {
            vehicle.v.position = newPosition;
            updateVehicleTravelledWayAngle(vehicle, oldPosition, newPosition, track.getTrackCentre());
        }
    }
    
    private void updateVehicleTravelledWayAngle(Vehicle vehicle, PointAG oldPosition, PointAG newPosition, PointAG trackCenter)
    {
         LineSection lsWithOldPos;
         lsWithOldPos = new LineSection(trackCenter, oldPosition);
         LineSection lsWithNewPos = new LineSection(trackCenter, newPosition);
         
         double angleWithOldPos = General.inclinationAngle(lsWithOldPos);
         double angleWithNewPos = General.inclinationAngle(lsWithNewPos);
         
         vehicle.travelledWayAngle += angleWithNewPos - angleWithOldPos;
    }

    public PointAG getVehiclePosition(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        return vehicle.v.position;
    }

    private PointAG findNewPosition(Vehicle vehicle) {
        float moveX, moveY, factorX, factorY;
        
        double cos = Math.cos(vehicle.v.angle);
        double sin = Math.sin(vehicle.v.angle);
                
        factorX = General.roundToFloat(cos, ROUNDING_PRECISION);
        factorY = General.roundToFloat(sin, ROUNDING_PRECISION);
        
        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        float newPositionX = vehicle.v.position.x + moveX;
        float newPositionY = vehicle.v.position.y + moveY;

        return new PointAG(newPositionX, newPositionY);
    }
}
