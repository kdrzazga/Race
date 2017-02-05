package logic;

import java.util.ArrayList;

import libs.math2.Numbers;
import libs.math2.LineSection;
import libs.math2.PointAG;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    public Board() {
        init(new Track());
    }
 
    public Board(int numberOfVehicles, Track track) {
        init(track);

        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle veh = new Vehicle(i, 0, this.track.computeStartPosition(i, numberOfVehicles));
            veh.active = true;

            this.vehicles.add(veh);
        }
    }

    private void init(Track track) {
        this.track = track;
        this.vehicles = new ArrayList<>();
    }

    public void moveAllVehicles() {
        this.vehicles.forEach((vehicle) -> {
            this.moveVehicle(vehicle.getId());
        });
    }

    public void moveVehicle(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        vehicle.previousV = vehicle.v.clone();
        PointAG newPosition = computeNewPosition(vehicle);
        assignNewPositionIfVehicleOnTrack(newPosition, vehicle);
        checkVehicleCrashWithOthers(vehicle);
    }

    private void assignNewPositionIfVehicleOnTrack(PointAG newPosition, Vehicle vehicle) {
        if (track.isInsideTrack(newPosition)) {
            vehicle.v.position = newPosition;
            checkIfPassingCheckpoint(vehicle, track.computeCenter());
        } else {
            vehicle.stop(); 
        }
    }

    public PointAG getVehiclePosition(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);
        return vehicle.v.position;
    }

    private PointAG computeNewPosition(Vehicle vehicle) {
        float moveX, moveY, factorX, factorY;

        factorX = Numbers.roundToFloat(Math.cos(vehicle.v.angle));
        factorY = Numbers.roundToFloat(Math.sin(vehicle.v.angle));

        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        float newPositionX = vehicle.v.position.x + moveX;
        float newPositionY = vehicle.v.position.y + moveY;

        return new PointAG(newPositionX, newPositionY);
    }

    private void checkVehicleCrashWithOthers(Vehicle thisVehicle) {
        
        this.vehicles.forEach((otherVehicle) -> {
            if (!otherVehicle.equals(thisVehicle)) {
                PointAG thisVehicleLocation = thisVehicle.v.position; 
                PointAG otherVehicleLocation = otherVehicle.v.position;
                
                if (thisVehicleLocation.distanceToAntherPointAG(otherVehicleLocation) < Vehicle.SIZE) {
                    thisVehicle.stop();
                    otherVehicle.stop();
                }
            }
        });
    }

    private void checkIfPassingCheckpoint(Vehicle vehicle, PointAG trackCenter) {
        LineSection lsWithPrevLocation = new LineSection(trackCenter, vehicle.previousV.position);
        LineSection lsWithCurrLocation = new LineSection(trackCenter, vehicle.v.position);

        double angleWithPrevLocation = lsWithPrevLocation.computeInclinationAngle();
        double angleWithNewPos = lsWithCurrLocation.computeInclinationAngle();

        vehicle.travelledWayAngle = angleWithNewPos;
        System.out.println("checkIfPassingCheckpoint is invalid");
    }

}
