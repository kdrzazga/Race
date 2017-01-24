package logic;

import libs.math2.PointAG;

public class Vehicle {

    public VelocityVector v;
    public PointAG previousLocation;
    public double travelledWayAngle; //the path that Vehicle has travelled is expressed in angle, not in distance (2PI = 1 lap, PI = 0.5 lap etc.)
    public boolean active;
    private final int id;

    public Vehicle(int id) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.previousLocation = new PointAG(0, 0);
    }

    public Vehicle(int id, int speed, PointAG position) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.v = new VelocityVector(speed, 0.0, position);
        this.previousLocation = new PointAG(0, 0);
    }

    public Vehicle(int id, int speed, PointAG position, boolean active) {
        this.travelledWayAngle = 0.0;
        this.id = id;
        this.v = new VelocityVector(speed, 0.0, position);
        this.active = active;
        this.previousLocation = new PointAG(0, 0);
    }
    
    @Override
    public Vehicle clone()
    {
        //no need to call super.clone() or throw CloneNotSupportedException
        Vehicle clonedVehicle = new Vehicle(this.id, this.v.value, this.v.position, this.active);
        clonedVehicle.v.angle = this.v.angle;
        clonedVehicle.travelledWayAngle = this.travelledWayAngle;
        clonedVehicle.previousLocation.x = this.previousLocation.x;
        clonedVehicle.previousLocation.y = this.previousLocation.y;
        
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
        if (this.active)
            this.v.value = 0;
    }

    @Override
    public String toString() {
        return "veh " + id + " at " + this.v.position + " speed = " 
                + this.v.value + " travelled =" + this.travelledWayAngle;
    }
}
