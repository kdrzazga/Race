package logic;

import libs.math2.*;

public class Track {

    public PolygonAG innerBound;
    public PolygonAG outerBound;

    public PolygonAG checkpoints[];

    private LineSection intersectedInnerLine;
    private LineSection intersectedOuterLine;

    public Track() {
        this.innerBound = new PolygonAG();
        this.outerBound = new PolygonAG();
        this.checkpoints = new PolygonAG[5];
    }

    public boolean isInsideTrack(PointAG point) {
        return !this.innerBound.convertToPolygon().contains(point.convertToPoint())
                && this.outerBound.convertToPolygon().contains(point.convertToPoint());
    }

    public void computeCheckpoints() {
        PointAG innerBoundCenter = innerBound.computeCenter();

        int height = Numbers.roundToInt(VelocityVector.V_MAX * 1.5);

        PointAG centralPt1 = new PointAG(innerBoundCenter.x, innerBoundCenter.y - height / 2);
        PointAG centralPt2 = new PointAG(innerBoundCenter.x, innerBoundCenter.y + height / 2);
        PointAG topLeftPt1 = new PointAG(this.computeMinX(), this.computeMinY() - height / 2);
        PointAG topLeftPt2 = new PointAG(this.computeMinX(), this.computeMinY() + height / 2);
        PointAG topRightPt1 = new PointAG(this.computeMaxX(), this.computeMinY() - height / 2);
        PointAG topRightPt2 = new PointAG(this.computeMaxX(), this.computeMinY() + height / 2);
        PointAG bottomRightPt1 = new PointAG(this.computeMaxX(), this.computeMaxY() - height / 2);
        PointAG bottomRightPt2 = new PointAG(this.computeMaxX(), this.computeMaxY() + height / 2);
        PointAG bottomLeftPt1 = new PointAG(this.computeMinX(), this.computeMaxY() - height / 2);
        PointAG bottomLeftPt2 = new PointAG(this.computeMinX(), this.computeMaxY() + height / 2);

        for (int i = 0; i < this.checkpoints.length - 1; i++) {
            this.checkpoints[i] = new PolygonAG();
            this.checkpoints[i].points.add(centralPt2);
            this.checkpoints[i].points.add(centralPt1);
        }

        checkpoints[0].points.add(topLeftPt1);
        checkpoints[0].points.add(topLeftPt2);

        checkpoints[1].points.add(topRightPt1);
        checkpoints[1].points.add(topRightPt2);

        checkpoints[2].points.add(bottomLeftPt1);
        checkpoints[2].points.add(bottomLeftPt2);

        checkpoints[3].points.add(bottomRightPt1);
        checkpoints[3].points.add(bottomRightPt2);

        float x1 = this.computeVerticalStartLine().p1.x;
        float x2 = this.computeVerticalStartLine().p2.x;
        float y1 = this.computeVerticalStartLine().p1.y;
        float y2 = this.computeVerticalStartLine().p2.y;

        checkpoints[4] = new PolygonAG();
        checkpoints[4].addPointAG(x1, y1);
        checkpoints[4].addPointAG(x2, y2);
        checkpoints[4].addPointAG(x2 + height, y2);
        checkpoints[4].addPointAG(x1 + height, y1);
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

    public float computeMinX() {
        return Numbers.getMin(this.outerBound.getXPoints());
    }

    public float computeMaxX() {
        return Numbers.getMax(this.outerBound.getXPoints());
    }

    public float computeMinY() {
        return Numbers.getMin(this.outerBound.getYPoints());
    }

    public float computeMaxY() {
        return Numbers.getMax(this.outerBound.getYPoints());
    }
}
