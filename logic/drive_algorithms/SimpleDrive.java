package logic.drive_algorithms;

import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public class SimpleDrive implements IDriveAlgorithm {

    public Track track;
    public Vehicle vehicle;
    private PolygonAG desiredRoute;

     public SimpleDrive()
     {
         
     }
    public SimpleDrive(Track track, Vehicle vehicle) {
        this.track = track;
        this.vehicle = vehicle;
        this.computeDesiredRoute();
    }

    public void computeVelocityVector() {

    }

    private void computeDesiredRoute() {
        this.desiredRoute = new PolygonAG(this.track.outerBound);

        this.desiredRoute.scale(0.79f);
    }

    public PolygonAG getDesiredRoute() {
        return desiredRoute;
    }
}
