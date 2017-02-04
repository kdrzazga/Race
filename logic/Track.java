package logic;

import java.util.ArrayList;
import libs.math2.*;
import logic.drive_algorithms.IDriveAlgorithm;

public class Track {

    public PolygonAG innerBound;
    public PolygonAG outerBound;
    
    public ArrayList<IDriveAlgorithm> acceptableAlgorithms;

    private LineSection intersectedInnerLine;
    private LineSection intersectedOuterLine;

    public Track() {
        this.innerBound = new PolygonAG();
        this.outerBound = new PolygonAG();
        this.acceptableAlgorithms = new ArrayList<>();
    }

    public boolean isInsideTrack(PointAG point) {
        return !this.innerBound.convertToPolygon().contains(point.convertToPoint())
                && this.outerBound.convertToPolygon().contains(point.convertToPoint());
    }

    public PointAG computeCenter() {
        PointAG outerBoundCenter = outerBound.computeCenter();
        PointAG innerBoundCenter = innerBound.computeCenter();

        LineSection sectionBetweenCenters;
        sectionBetweenCenters = new LineSection(innerBoundCenter, outerBoundCenter);

        return sectionBetweenCenters.computeCenter();
    }

    public LineSection computeVerticalStartLine() {
        findLinesIntersectedByVertStartLine();
        float X = computeCenter().x;
        float y1 = intersectedInnerLine.computeY(X);
        float y2 = intersectedOuterLine.computeY(X);

        return new LineSection(X, y1, X, y2);
    }

    public LineSection computeStartLine() {
        findLinesIntersectedByVertStartLine();
        return new LineSection(intersectedInnerLine.computeCenter(), intersectedOuterLine.computeCenter());
    }

    public PointAG computeStartPosition(int vehicleIndex, int numberOfVehicles) {
        LineSection startLine = this.computeVerticalStartLine();

        float x = computeCoordinate(numberOfVehicles, vehicleIndex, startLine.p1.x, startLine.p2.x);
        float y = computeCoordinate(numberOfVehicles, vehicleIndex, startLine.p1.y, startLine.p2.y);
        return new PointAG(x, y);
    }
    
    private void findLinesIntersectedByVertStartLine() {
        float X = computeCenter().x;
 
        LineAG lineContainingStartLineSection = new LineAG(X);

        intersectedInnerLine = this.innerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);
        intersectedOuterLine = this.outerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);
    }

    private float computeCoordinate(int numberOfVehicles, int vehicleIndex, float coordinateP1, float coordinateP2) {
        float y;
        if (coordinateP1 == coordinateP2) {
            y = coordinateP1;
        } else {
            float distanceBetweenVehsY = (coordinateP2 - coordinateP1) / (numberOfVehicles + 1);
            y = coordinateP1 + (vehicleIndex + 1) * distanceBetweenVehsY;
        }
        return y;
    }
  
}
