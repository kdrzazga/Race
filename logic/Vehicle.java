package logic;

import libs.math2.PointAG;

public class Vehicle {

    public VelocityVector v;
    public double travelledWayAngle; //the path that Vehicle has moved is is expressed in angle, not in length (2PI = 1 lap, PI = 0.5 lap etc.)
    public boolean active;
    private final int id;

    public Vehicle(int id) {
        this.travelledWayAngle = 0.0;
        this.id = id;
    }

    public Vehicle(int id, int speed) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.v = new VelocityVector(speed, 0);
    }

    public Vehicle(int id, int speed, PointAG position) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.v = new VelocityVector(speed, 0);
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
}
