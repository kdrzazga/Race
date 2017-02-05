package logic.drive_algorithms;

import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public class SimpleDrive extends DriveAlgorithm {

    public Track track;
    public Vehicle vehicle;
    private PolygonAG desiredRoute;

    public SimpleDrive(Track track) {
        this.track = track;
        this.computeDesiredRoute();
    }

    public void computeVelocityVector() {
        System.out.println("computeVelocityVector() - not implemented yet");
    }

    private void computeDesiredRoute() {
        this.desiredRoute = new PolygonAG(this.track.outerBound);
        this.desiredRoute.scale(0.79f);
    }

    public PolygonAG getDesiredRoute() {
        return desiredRoute;
    }
    
    public void setTrack(Track track) {
        this.track = track;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public static String getName() {
        return SimpleDrive.class.getSimpleName();
    }
}
