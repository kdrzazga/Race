package logic.drive_algorithms;

import libs.math2.PointAG;
import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public abstract class DriveAlgorithm {
    
    public boolean algorithmWithDesiredRoute;

    protected Track track;
    protected String name;
    
    protected Vehicle vehicle;
    protected PointAG previousLocation;
    
    public DriveAlgorithm(boolean algorithmWithDesiredRoute, String name)
    {
        this.algorithmWithDesiredRoute = algorithmWithDesiredRoute;
        this.name = name;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract void computeVelocityVector();

    public Track getTrack() {
        return this.track;
    }
    
    public String getName() {
        return name;
    }
}
