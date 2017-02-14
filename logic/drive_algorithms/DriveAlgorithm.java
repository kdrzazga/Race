package logic.drive_algorithms;

import libs.math2.PointAG;
import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public abstract class DriveAlgorithm {
    
    public boolean algorithmWithDesiredRoute;

    protected Track track;
    protected PolygonAG desiredRoute;
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
    public PolygonAG getDesiredTrack() {
        return this.desiredRoute;
    } 

    public String getName() {
        return name;
    }
}
