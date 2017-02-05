package logic.drive_algorithms;

import libs.math2.PointAG;
import logic.Track;
import logic.Vehicle;

public abstract class DriveAlgorithm {

    protected Track track;
    protected Vehicle vehicle;
    protected PointAG previousLocation;
    
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    public abstract void computeVelocityVector();

}
