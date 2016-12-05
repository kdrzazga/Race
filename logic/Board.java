package logic;

import java.awt.Point;
import java.util.ArrayList;
import libs.Math2;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    private static Board instance = null;

    private Board() {
        this.track = Track.getInstance();
        this.vehicles = new ArrayList<>();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void moveVehicle(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);

        Point newPosition = findNewPosition(vehicle);

        if (track.pointWithinTrack(newPosition)) {
            vehicle.v.position = newPosition;
        }
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
