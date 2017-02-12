package logic.drive_algorithms;

import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public class SimpleDrive extends DriveAlgorithm {
   
    private PolygonAG desiredRoute;

    public SimpleDrive(Track track) {
        this.track = track;
        this.desiredRoute = new PolygonAG();
        this.computeDesiredRoute();
    }

    @Override
    public void computeVelocityVector() {
        System.out.println("computeVelocityVector() - not implemented yet");
    }

    private void computeDesiredRoute() {
    }

    public PolygonAG getDesiredRoute() {
        return desiredRoute;
    }
    
    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public static String getName() {
        return SimpleDrive.class.getSimpleName();
    }
}
