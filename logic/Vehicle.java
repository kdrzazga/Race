package logic;

import libs.math2.PointAG;
import logic.drive_algorithms.DriveAlgorithm;

public class Vehicle {

    public VelocityVector v;
    public VelocityVector previousV;
    public double travelledWayAngle; //the path that Vehicle has travelled is expressed in angle, not in distance (2PI = 1 lap, PI = 0.5 lap etc.)
    public boolean active;
    public DriveAlgorithm driveAlgorithm;
    public static final int SIZE = 8;
    int id;

    public Vehicle(int id) {
        init(id, true);
    }
   
    public Vehicle(int id, int speed, PointAG position, DriveAlgorithm driveAlgorithm, boolean active)
    {
        init(id, active);
        this.v = new VelocityVector(speed, 0.0, position);
        this.driveAlgorithm = driveAlgorithm;
    }

    public Vehicle(int id, int speed, PointAG position) {
        init(id, true);
        this.v = new VelocityVector(speed, 0.0, position);
    }

    public Vehicle(int id, int speed, PointAG position, boolean active) {
        init(id, active);
        this.v = new VelocityVector(speed, 0.0, position);
        this.active = active;
    }

    private void init(int id, boolean active) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.previousV = new VelocityVector(0, 0, new PointAG(0, 0));
        this.active = active;
    }

    @Override
    public Vehicle clone() {
        //no need to call super.clone() or throw CloneNotSupportedException
        Vehicle clonedVehicle = new Vehicle(this.id, this.v.value, this.v.position, this.active);
        clonedVehicle.v.angle = this.v.angle;
        clonedVehicle.travelledWayAngle = this.travelledWayAngle;
        clonedVehicle.previousV = this.previousV.clone();

        return clonedVehicle;
    }

    public int getId() {
        return id;
    }

    public void turnLeft() {
        if (this.active) {
            this.v.turnLeft();
        }
    }

    public void turnRight() {
        if (this.active) {
            this.v.turnRight();
        }
    }

    public void accelerate() {
        if (this.active) {
            this.v.accelerate();
        }
    }

    public void slowDown() {
        if (this.active) {
            this.v.slowDown();
        }
    }

    public void stop() {
        if (this.active) {
            this.v.value = 0;
        }
    }

    @Override
    public String toString() {
        return "veh " + id + " at " + this.v.position + " speed = "
                + this.v.value + " travelled =" + this.travelledWayAngle;
    }
}
