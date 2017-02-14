package logic.drive_algorithms;

import libs.math2.PolygonAG;
import logic.Track;
import logic.Vehicle;

public class KeepCloseInnerBound extends DriveAlgorithm {

    public KeepCloseInnerBound(Track track) {
        super(true, "Keep Close Inner Bound");
        this.track = track;
        this.desiredRoute = new PolygonAG();
        this.computeDesiredRoute();
    }

    @Override
    public void computeVelocityVector() {
        throw new RuntimeException("computeVelocityVector() - not implemented yet");
    }

    private void computeDesiredRoute() {
        this.desiredRoute = getTrack().innerBound.clone();
        this.desiredRoute.scale(1.2f);
    }

    public PolygonAG getDesiredRoute() {
        if (this.desiredRoute == null)
            this.computeDesiredRoute();
        
        return desiredRoute;
    }
    
    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
