package logic.drive_algorithms;

import libs.math2.PolygonAG;

public interface IDriveAlgorithm {

    public void computeVelocityVector();

    public PolygonAG getDesiredRoute();
}
