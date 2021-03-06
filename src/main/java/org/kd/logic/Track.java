package org.kd.logic;

import org.kd.libs.math2.*;

import java.util.stream.IntStream;

public class Track implements Cloneable{

    public PolygonAG innerBound;
    public PolygonAG outerBound;
    public PolygonAG routeForCompPlyr ;

    public PolygonAG[] checkpoints;

    public static final int NUMBER_OF_CHECKPOINTS = 3;
    public static final int CHECKPOINT_WITH_START_LINE_INDEX = 0;
    private static final int TRACK_HEIGHT_LIMIT = Numbers.roundToInt(Math.pow(2, 20));//more than a million

    protected LineSection intersectedInnerLine;
    protected LineSection intersectedOuterLine;
    protected LineSection downFromCenterLineSection;
    
    public Track() {
        this.innerBound = new PolygonAG();
        this.outerBound = new PolygonAG();
        this.checkpoints = new PolygonAG[NUMBER_OF_CHECKPOINTS];
    }

    public boolean isInsideTrack(PointAG point) {
        return !this.innerBound.convertToPolygon().contains(point.getX(), point.getY())
                && this.outerBound.convertToPolygon().contains(point.getX(), point.getY());
    }

    public void computeCheckpoints() {
        PointAG innerBoundCenter = innerBound.computeCenter();

        int height = Numbers.roundToInt(VelocityVector.V_MAX * 2.1);

        var centralPt1 = new PointAG(innerBoundCenter.getX(), (float)(innerBoundCenter.getY() - height / 2.0));
        var centralPt2 = new PointAG(innerBoundCenter.getX(), (float)(innerBoundCenter.getY() + height / 2.0));
        var topLeftPt1 = new PointAG(this.computeMinX(), (float)(this.computeMinY() - height / 2.0));
        var topLeftPt2 = new PointAG(this.computeMinX(), (float)(this.computeMinY() + height / 2.0));
        var topRightPt1 = new PointAG(this.computeMaxX(), (float)(this.computeMinY() - height / 2.0));
        var topRightPt2 = new PointAG(this.computeMaxX(), (float)(this.computeMinY() + height / 2.0));

        IntStream.range(CHECKPOINT_WITH_START_LINE_INDEX + 1, this.checkpoints.length).forEach(i -> {
            this.checkpoints[i] = new PolygonAG();
            this.checkpoints[i].points.add(centralPt2);
            this.checkpoints[i].points.add(centralPt1);
        });

        LineSection startLine = this.computeVerticalStartLine();

        float x1 = startLine.p1.getX();
        float x2 = startLine.p2.getX();
        float y1 = startLine.p1.getY();
        float y2 = startLine.p2.getY();

        checkpoints[CHECKPOINT_WITH_START_LINE_INDEX] = new PolygonAG();
        checkpoints[CHECKPOINT_WITH_START_LINE_INDEX].addPointAG(x1, y1);
        checkpoints[CHECKPOINT_WITH_START_LINE_INDEX].addPointAG(x2, y2);
        checkpoints[CHECKPOINT_WITH_START_LINE_INDEX].addPointAG(x2 + height, y2);
        checkpoints[CHECKPOINT_WITH_START_LINE_INDEX].addPointAG(x1 + height, y1);

        checkpoints[1].points.add(topLeftPt1);
        checkpoints[1].points.add(topLeftPt2);
        
        checkpoints[2].points.add(topRightPt1);
        checkpoints[2].points.add(topRightPt2);  
    }

    public PointAG computeCenter() {
        PointAG outerBoundCenter = outerBound.computeCenter();
        PointAG innerBoundCenter = innerBound.computeCenter();

        var sectionBetweenCenters = new LineSection(innerBoundCenter, outerBoundCenter);

        return sectionBetweenCenters.computeCenter();
    }

    public LineSection computeVerticalStartLine() {
        findLinesIntersectedByVertStartLine();
        float centerX = innerBound.computeCenter().getX();
        float y1 = intersectedInnerLine.computeY(centerX);
        float y2 = intersectedOuterLine.computeY(centerX);

        return new LineSection(centerX, y1, centerX, y2);
    }

    public PointAG computeStartPosition(int vehicleIndex, int numberOfVehicles) {
        LineSection startLine = this.computeVerticalStartLine();

        float x = computeCoordinate(numberOfVehicles, vehicleIndex, startLine.p1.getX(), startLine.p2.getX());
        float y = computeCoordinate(numberOfVehicles, vehicleIndex, startLine.p1.getY(), startLine.p2.getY());
        return new PointAG(x, y);
    }

    private void findLinesIntersectedByVertStartLine() {
        var center = innerBound.computeCenter();

        downFromCenterLineSection = new LineSection(center, new PointAG(center.getX(), TRACK_HEIGHT_LIMIT));

        intersectedInnerLine = this.innerBound.getLineSectionCrossedBy(downFromCenterLineSection);
        intersectedOuterLine = this.outerBound.getLineSectionCrossedBy(downFromCenterLineSection);

        if (intersectedInnerLine == null || intersectedOuterLine == null) {
            throw new RuntimeException("findLinesIntersectedByVertStartLine() exception"
                    + " - WRONG line sections have been used for computing. Please debug");
        }
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

    @Override
    public Track clone() {
        var clonedTrack = new Track();
        clonedTrack.checkpoints = this.checkpoints.clone();
        clonedTrack.innerBound = this.innerBound.clone();
        clonedTrack.outerBound = this.outerBound.clone();
        clonedTrack.routeForCompPlyr = this.routeForCompPlyr.clone();
        
        return clonedTrack;
    }

    public float computeMinX() {
        return this.outerBound.computeMinX();
    }

    public float computeMaxX() {
        return this.outerBound.computeMaxX();
    }

    public float computeMinY() {
        return this.outerBound.computeMinY();
    }

    public float computeMaxY() {
        return this.outerBound.computeMaxY();
    }
}
