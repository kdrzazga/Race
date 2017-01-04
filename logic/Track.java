package logic;

import java.awt.Point;
import java.util.Arrays;
import libs.math2.*;

public class Track {

    public PolygonAG innerBound;
    public PolygonAG outerBound;

    public Track() {
        this.innerBound = new PolygonAG();
        this.outerBound = new PolygonAG();
    }

    public boolean pointWithinTrack(PointAG point) {
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

    public PointAG getStartPosition(int index) {
        switch (index) { /*TODO: Poilish code for case 1 and 2 */
            case 0:
                return this.getRaceStartLine().getCenter();
            case 1:
                LineSection halfStartLine1 = new LineSection(this.getRaceStartLine().getCenter(), this.getRaceStartLine().p1);
                return halfStartLine1.getCenter();
            case 2:
                LineSection halfStartLine2 = new LineSection(this.getRaceStartLine().getCenter(), this.getRaceStartLine().p2);
                return halfStartLine2.getCenter();
                
            default:
                throw new RuntimeException("Not implemented for more vehicles");
        }

        
    }

}
