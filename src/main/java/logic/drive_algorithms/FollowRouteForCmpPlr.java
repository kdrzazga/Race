package logic.drive_algorithms;

import libs.math2.LineSection;
import static libs.math2.LineSection.computeLength;
import libs.math2.PointAG;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;


public class FollowRouteForCmpPlr extends DriveAlgorithm {

    private int indexOfNextRoutePointToVisit;
    
    public FollowRouteForCmpPlr(Track track) {
        super(true, "Follow Optimal Route");
        this.track = track;
        indexOfNextRoutePointToVisit = 0;
    }

    @Override
    public void computeVelocityVector() {
        
        PointAG currentPos = this.vehicle.v.position;
        PointAG destPos = this.track.routeForCompPlyr.points.get(indexOfNextRoutePointToVisit);
        
        LineSection pathToDest = new LineSection(currentPos, destPos);
              
        this.vehicle.v.angle = pathToDest.computeInclinationAngle();        
        this.vehicle.v.value = (int) Math.min(computeLength.apply(pathToDest), (double)VelocityVector.V_MAX);
        
        this.computeNextPointToVisit();
    }
    
    private void computeNextPointToVisit()
    {
        this.indexOfNextRoutePointToVisit 
                = (this.indexOfNextRoutePointToVisit + 1) % this.track.routeForCompPlyr.points.size();
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
}
