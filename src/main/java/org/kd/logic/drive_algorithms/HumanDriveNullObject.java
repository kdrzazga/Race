package org.kd.logic.drive_algorithms;

public class HumanDriveNullObject extends DriveAlgorithm {

    public HumanDriveNullObject() {
        super(false, "Human");
    }

    @Override
    public void computeVelocityVector() {
        //no need to implement - KeyboardControlsAdapter took the responsibility
    }
}
