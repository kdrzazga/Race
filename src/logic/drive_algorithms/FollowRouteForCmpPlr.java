package logic.drive_algorithms;

import logic.Track;
import logic.Vehicle;

public class FollowRouteForCmpPlr extends DriveAlgorithm {

    public FollowRouteForCmpPlr(Track track) {
        super(true, "Follow Optimal Route");
        this.track = track;
    }

    @Override
    public void computeVelocityVector() {
        throw new RuntimeException("computeVelocityVector() - not implemented yet");
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
