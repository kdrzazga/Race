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

    public boolean isPointWithinTrack(PointAG point) {
        return !this.innerBound.convertToPolygon().contains(point.convertToPoint())
                && this.outerBound.convertToPolygon().contains(point.convertToPoint());
    }

    public PointAG getTrackCentre() {
        Point outerBoundCenter = General.computeCenterOfPolygon(outerBound.convertToPolygon());
        Point innerBoundCenter = General.computeCenterOfPolygon(innerBound.convertToPolygon());

        LineSection sectionBetweenCenters;
        sectionBetweenCenters = new LineSection(innerBoundCenter, outerBoundCenter);

        return sectionBetweenCenters.getCenter();
    }

    public LineSection getRaceStartLine() {
        float X = getTrackCentre().x;

        LineAG lineContainingStartLineSection = new LineAG(X);

        LineSection upperLine = this.innerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);
        LineSection lowerLine = this.outerBound.getLineSectionCrossingVerticalLine(lineContainingStartLineSection);

        return new LineSection(upperLine.getCenter(), lowerLine.getCenter());
    }

    public PointAG getStartPosition(int index, int maxIndex) {

        LineSection startLine = this.getRaceStartLine();
        float x, y;
        
        float endPointX, endPointY;
        float mostLeftPointX = Math.min(startLine.p1.x, startLine.p2.x);
        float mostLeftPointY = Math.min(startLine.p1.y, startLine.p2.y);
        float mostRightPointX = Math.max(startLine.p1.x, startLine.p2.x);
        float mostRightPointY = Math.max(startLine.p1.y, startLine.p2.y);
        
        if (startLine.p1.x == startLine.p2.x) {
            x = startLine.p1.x;
        } else {
            x = startLine.p1.x + index * (startLine.p1.x - startLine.p2.x) / maxIndex;
        }

        if (startLine.p1.y == startLine.p2.y) {
            y = startLine.p1.y;
        } else {
            y = startLine.p1.y + index * (startLine.p1.y - startLine.p2.y) / maxIndex;
        }

        return new PointAG(x, y);
    }
}
