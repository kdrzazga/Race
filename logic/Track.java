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

    public static Track create_50_50__350_250_RectangularTrack() {
        Track rectangularTrack = new Track();

        PointAG outerBoundPts[] = { new PointAG(50, 50), new PointAG(350, 50), new PointAG(350, 250), new PointAG(50, 250)};
        PointAG innerBoundPts[] = { new PointAG(100, 100), new PointAG(250, 100), new PointAG(250, 150), new PointAG(100, 150)};
        
        rectangularTrack.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        rectangularTrack.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        return rectangularTrack;
    }

    /*
    creates a donut track bounded by rectangle(50,50, 550,550)
     */
    public static Track create_50_50__550_550_DonutTrack() {

        Track donutTrack = new Track();
        PointAG donutCenter = new PointAG(300, 300);
        int outerBoundRadius = 250;
        int innerBoundRadius = 100;
        int numberOfPoints = 360;

        CircleAG innerBound = new CircleAG(donutCenter, innerBoundRadius, numberOfPoints);
        CircleAG outerBound = new CircleAG(donutCenter, outerBoundRadius, numberOfPoints);

        donutTrack.innerBound = innerBound.getPoints();
        donutTrack.outerBound = outerBound.getPoints();

        return donutTrack;
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
        switch (index) {
            case 0:
                return this.getRaceStartLine().getCenter();

        }

        throw new RuntimeException("Not implemented yet");
    }

}
