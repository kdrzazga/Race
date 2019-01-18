package org.kd.logic;

import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PointAG;
import org.kd.libs.math2.PolygonAG;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    public Track track;
    public List<Vehicle> vehicles;

    public Board() {
        init(new Track());
    }

    public Board(int numberOfVehicles, Track track) {
        init(track);
        initVehicles(numberOfVehicles);
    }

    private void initVehicles(int numberOfVehicles) {
        IntStream.range(0, numberOfVehicles)
                .forEach(index -> {
                    var veh = new Vehicle(index, 0, track.computeStartPosition(index, numberOfVehicles));
                    veh.setActive(true);
                    vehicles.add(veh);
                });
    }

    private void init(Track track) {
        this.track = track;
        this.vehicles = new ArrayList<>();
    }

    void moveAllVehicles() {
        vehicles.forEach(vehicle -> this.moveVehicle(vehicle.getId()));
    }

    public void moveVehicle(int vehicleId) {
        var vehicle = this.vehicles.get(vehicleId);
        vehicle.previousV = vehicle.v.clone();
        PointAG newPosition = computeNewPosition(vehicle);
        assignNewPositionIfVehicleOnTrack(newPosition, vehicle);
        checkVehicleCrashWithOthers(vehicle);
    }

    private void assignNewPositionIfVehicleOnTrack(PointAG newPosition, Vehicle vehicle) {
        if (track.isInsideTrack(newPosition)) {
            vehicle.v.position = newPosition;
            checkIfPassingCheckpoint(vehicle);
        } else {
            vehicle.stop();
        }
    }

    public PointAG getVehiclePosition(int vehicleId) {
        var vehicle = this.vehicles.get(vehicleId);
        return vehicle.v.position;
    }

    private PointAG computeNewPosition(Vehicle vehicle) {
        float moveX;
        float moveY;
        float factorX;
        float factorY;

        factorX = Numbers.roundToFloat(Math.cos(vehicle.v.angle));
        factorY = Numbers.roundToFloat(Math.sin(vehicle.v.angle));

        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        float newPositionX = vehicle.v.position.getX() + moveX;
        float newPositionY = vehicle.v.position.getY() + moveY;

        return new PointAG(newPositionX, newPositionY);
    }

    private void checkVehicleCrashWithOthers(Vehicle thisVehicle) {

        this.vehicles.forEach(otherVehicle -> {
            if (!otherVehicle.equals(thisVehicle)) {
                var thisVehicleLocation = thisVehicle.v.position;
                var otherVehicleLocation = otherVehicle.v.position;

                if (thisVehicleLocation.distanceToAntherPointAG(otherVehicleLocation) < Vehicle.SIZE) {
                    thisVehicle.stop();
                    otherVehicle.stop();
                }
            }
        });
    }

    private void checkIfPassingCheckpoint(Vehicle vehicle) {
        IntStream.range(0, this.track.checkpoints.length).forEach(i ->
        {
            var checkpointPolygon = this.track.checkpoints[i].convertToPolygon();
            var vehicleLocation = vehicle.v.position;

            if (checkpointPolygon.contains(vehicleLocation.getX(), vehicleLocation.getY())) {
                vehicle.checkpointsVisited.values[i] = true;
                PolygonAG checkpointWithFinishLine = this.track.checkpoints[Track.CHECKPOINT_WITH_START_LINE_INDEX];
                boolean vehicleCrossedFinishLine = checkpointWithFinishLine.convertToPolygon().contains(vehicleLocation.getX(), vehicleLocation.getY());

                if (vehicle.checkpointsVisited.allAreTrue() && vehicleCrossedFinishLine) {
                    vehicle.incLaps();
                    vehicle.checkpointsVisited.setAllItems(false);
                }
            }
        });
    }
}
