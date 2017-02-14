package logic.drive_algorithms;

import libs.math2.PointAG;
import logic.Track;

public class TurnLeftAlgorithm extends DriveAlgorithm {

    private int counter = 0;
    private static final int MAX_COUNTER = 12;

    public TurnLeftAlgorithm(Track track) {
        super(false, "Turn Left");
        this.previousLocation = new PointAG(0, 0);
        this.track = track;
        this.algorithmWithDesiredRoute = false;
    }

    @Override
    public void computeVelocityVector() {
        if (this.vehicle.v.position.equals(previousLocation)) {
            this.vehicle.turnLeft();
            this.vehicle.turnLeft();
        }
        this.vehicle.accelerate();
        everySeveralGameFramesTurnRight();
    }

    private void everySeveralGameFramesTurnRight() {
        this.previousLocation = vehicle.v.position;
        this.counter = (++this.counter % MAX_COUNTER);
        if (this.counter > 10) {
            this.vehicle.turnRight();
        }
    }

}
