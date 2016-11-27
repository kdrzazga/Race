package logic;

public class Vehicle {
    
    public VelocityVector v;
    public double travelledWayAngle; //the path that Vehicle has moved is is expressed in angle, not in length (2PI = 1 lap, PI = 0.5 lap etc.)
    public boolean active;

    public Vehicle()
    {
        this.travelledWayAngle = 0.0;
    }
}
