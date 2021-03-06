package org.kd.logic;

import org.kd.libs.BooleanArray;
import org.kd.libs.math2.PointAG;
import org.kd.logic.drive_algorithms.DriveAlgorithm;

public class Vehicle implements Cloneable{

    public VelocityVector v;
    public VelocityVector previousV;

    public int finalPlace;
    public DriveAlgorithm driveAlgorithm;
    public static final int SIZE = 8;    
    public BooleanArray checkpointsVisited;
    int id;

    private int laps;
    private boolean active;

    public Vehicle(int id) {
        init(id, true);
    }

    public Vehicle(int id, int speed, PointAG position, DriveAlgorithm driveAlgorithm, boolean active) {
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
        this.checkpointsVisited = new BooleanArray(Track.NUMBER_OF_CHECKPOINTS);
        this.checkpointsVisited.setAllItems(false);
        this.laps = 1;
        this.finalPlace = -1;
        this.id = id;
        this.previousV = new VelocityVector(0, 0, new PointAG(0, 0));
        this.active = active;
    }

    @Override
    public Vehicle clone() {
        //no need to call super.clone() or throw CloneNotSupportedException
        var clonedVehicle = new Vehicle(this.id, this.v.value, this.v.position, this.active);
        clonedVehicle.v.angle = this.v.angle;
        clonedVehicle.laps = this.laps;
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
                + this.v.value + " travelled =" + this.laps;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getLaps() {
        return laps;
    }

    public void incLaps() {
        this.laps++;
    }
}
