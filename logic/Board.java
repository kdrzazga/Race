package logic;

import java.util.ArrayList;

import libs.math2.General;
import libs.math2.LineSection;
import libs.math2.PointAG;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    public Board() {
        this.track = new Track();
        this.vehicles = new ArrayList<>();
    }

    public Board(int numberOfVehicles, Track track) {
        this.track = track;
        this.vehicles = new ArrayList<>();

        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle veh = new Vehicle(i, 0, this.track.getStartPosition(i, numberOfVehicles));
            veh.active = true;

            this.vehicles.add(veh);
        }
    }
    
    public void moveAllVehicles() {
        this.vehicles.forEach((vehicle) -> {
            this.moveVehicle(vehicle.getId());
        });
    }
    
    public void moveVehicle(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        vehicle.previousLocation = vehicle.v.position;

        PointAG newPosition = computeNewPosition(vehicle);

        if (track.isPointWithinTrack(newPosition)) {
            vehicle.v.position = newPosition;
            updateVehicleTravelledWayAngle(vehicle, vehicle.previousLocation, newPosition, track.getTrackCentre());
        }
    }

    public PointAG getVehiclePosition(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        return vehicle.v.position;
    }
    
    private PointAG computeNewPosition(Vehicle vehicle) {
        float moveX, moveY, factorX, factorY;

        double cos = Math.cos(vehicle.v.angle);
        double sin = Math.sin(vehicle.v.angle);

        factorX = General.roundToFloat(cos);
        factorY = General.roundToFloat(sin);

        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        float newPositionX = vehicle.v.position.x + moveX;
        float newPositionY = vehicle.v.position.y + moveY;

        return new PointAG(newPositionX, newPositionY);
    }

    private void updateVehicleTravelledWayAngle(Vehicle vehicle, PointAG oldPosition, PointAG newPosition, PointAG trackCenter) {
        LineSection lsWithOldPos;
        lsWithOldPos = new LineSection(trackCenter, oldPosition);
        LineSection lsWithNewPos = new LineSection(trackCenter, newPosition);

        double angleWithOldPos = General.inclinationAngle(lsWithOldPos);
        double angleWithNewPos = General.inclinationAngle(lsWithNewPos);

        vehicle.travelledWayAngle += angleWithNewPos - angleWithOldPos;
    }

}
