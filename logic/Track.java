package logic;

import java.awt.Point;

import libs.math2.*;

public class Track {

    public PolygonAG innerBound;
    public PolygonAG outerBound;

    public Track() {
        this.innerBound = new PolygonAG();
        this.outerBound = new PolygonAG();
    }

    public boolean isInsideTrack(PointAG point) {
        return !this.innerBound.convertToPolygon().contains(point.convertToPoint())
                && this.outerBound.convertToPolygon().contains(point.convertToPoint());
    }

    public PointAG computeCenter() {
        Point outerBoundCenter = General.computeCenterOfPolygon(outerBound.convertToPolygon());
        Point innerBoundCenter = General.computeCenterOfPolygon(innerBound.convertToPolygon());

        LineSection sectionBetweenCenters;
        sectionBetweenCenters = new LineSection(innerBoundCenter, outerBoundCenter);

        return sectionBetweenCenters.computeCenter();
    }

    public LineSection getRaceStartLine() {
        float X = computeCenter().x;

        LineAG lineContainingStartLineSection = new LineAG(X);

        LineSection intersectedInnerLine = this.innerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);
        LineSection intersectedOuterLine = this.outerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);

        return new LineSection(intersectedInnerLine.computeCenter(), intersectedOuterLine.computeCenter());
    }

    public PointAG computeStartPosition(int vehicleIndex, int numberOfVehicles) {

        LineSection startLine = this.getRaceStartLine();
        
        float x, y;

        if (startLine.p1.x == startLine.p2.x) {
            x = startLine.p1.x;
        } else {
            float distanceBetweenVehsX = (startLine.p2.x - startLine.p1.x) / (numberOfVehicles + 1);
            x = startLine.p1.x + (vehicleIndex + 1) * distanceBetweenVehsX;
        }

        if (startLine.p1.y == startLine.p2.y) {
            y = startLine.p1.y;
        } else {
            float distanceBetweenVehsY = (startLine.p2.y - startLine.p1.y) / (numberOfVehicles + 1);
            y = startLine.p1.y + (vehicleIndex + 1) * distanceBetweenVehsY;
        }

        return new PointAG(x, y);
    }
}
