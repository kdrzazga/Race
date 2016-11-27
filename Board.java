package logic;

import java.awt.Point;
import java.util.ArrayList;
import libs.Math2;

public class Board {

    public Track track;
    public ArrayList<Vehicle> vehicles;

    public Board() {
        this.track = new Track();
        this.vehicles = new ArrayList<>();
    }

    public void moveVehicle(int vehicleId) {
        Vehicle vehicle = this.vehicles.get(vehicleId);

        Point newPosition = findNewPosition(vehicle);

        if (track.pointWithinTrack(newPosition)) {
            vehicle.v.position = newPosition;
        }
    }

    private Point findNewPosition(Vehicle vehicle) {
        double moveX, moveY, factorX, factorY;
        /*
        if (vehicle.v.angle == 0) {
            factorX = 1;
            factorY = 0;
        } else if (vehicle.v.angle == Math.PI / 2) {
            factorX = 0;
            factorY = -1;
        } else if (vehicle.v.angle == Math.PI) {
            factorX = -1;
            factorY = 0;
        } else if (vehicle.v.angle == 3 * Math.PI / 2) {
            factorX = 0;
            factorY = 1;
        } else */
        {
            double cos = Math.cos(vehicle.v.angle);
            double sin = Math.sin(vehicle.v.angle);
            factorX = Math2.round(cos, 4);
            factorY = Math2.round(sin, 4);
        }

        moveX = factorX * vehicle.v.value;
        moveY = factorY * vehicle.v.value;

        System.out.print(moveX + "," + moveY + " ");

        int newPositionX = (int)(vehicle.v.position.x + moveX);
        int newPositionY = (int) (vehicle.v.position.y + moveY);
        System.out.print(newPositionX + "," + newPositionY + " ");
        return new Point(newPositionX, newPositionY);
    }
}
