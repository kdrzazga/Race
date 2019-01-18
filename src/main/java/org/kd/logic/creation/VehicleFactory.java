package org.kd.logic.creation;

import org.kd.libs.math2.PointAG;
import org.kd.logic.Board;
import org.kd.logic.Game;
import org.kd.logic.Vehicle;
import org.kd.logic.VelocityVector;
import org.kd.logic.drive_algorithms.DriveAlgorithm;
import org.kd.logic.drive_algorithms.HumanDriveNullObject;
import org.kd.logic.drive_algorithms.TurnLeftAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VehicleFactory {


    List<Vehicle> createVehicles(int numberOfVehicles, Board board) {
        var vehicles = new ArrayList<Vehicle>();
        int initialSpeed = VelocityVector.V_MIN;

        IntStream.range(0, numberOfVehicles).forEach(i -> {
            PointAG vehiclePosition = board.track.computeStartPosition(i, numberOfVehicles);
            DriveAlgorithm driveAlgorithm;

            if (i >= Game.NUMBER_OF_HUMAN_CONTROLLED_VEHICLES) {
                driveAlgorithm = new TurnLeftAlgorithm(board.track);
            } else {
                driveAlgorithm = new HumanDriveNullObject();
            }
            var vehicle = new Vehicle(i, initialSpeed, vehiclePosition, driveAlgorithm, true);
            driveAlgorithm.setVehicle(vehicle);
            vehicles.add(vehicle);
        });

        return vehicles;
    }


    Vehicle createVehicleAtPosition(int id, float x, float y) {
        var vehicle = new Vehicle(id, 1, new PointAG(x, y));
        vehicle.setActive(true);

        return vehicle;
    }
}
