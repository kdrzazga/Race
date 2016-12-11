package logic;

import java.awt.Point;
import java.awt.Polygon;
import libs.Math2.Circle;
import libs.Math2;

public class Track {

    public Polygon innerBound;
    public Polygon outerBound;

    public Track() {
        this.innerBound = new Polygon();
        this.outerBound = new Polygon();
    }

    public boolean pointWithinTrack(Point point) {
        return !this.innerBound.contains(point)
                && this.outerBound.contains(point);
    }

    public static Track create_50_50__350_250_RectangularTrack() {
        Track rectangularTrack = new Track();

        rectangularTrack.outerBound.addPoint(50, 50);
        rectangularTrack.outerBound.addPoint(350, 50);
        rectangularTrack.outerBound.addPoint(350, 250);
        rectangularTrack.outerBound.addPoint(50, 250);

        rectangularTrack.innerBound.addPoint(100, 100);
        rectangularTrack.innerBound.addPoint(250, 100);
        rectangularTrack.innerBound.addPoint(250, 150);
        rectangularTrack.innerBound.addPoint(100, 150);

        return rectangularTrack;
    }

    /*
    creates a donut track bounded by rectangle(50,50, 550,550)
     */
    public static Track create_50_50__550_550_DonutTrack() {

        Track donutTrack = new Track();
        Point donutCenter = new Point(300, 300);
        int outerBoundRadius = 250;
        int innerBoundRadius = 100;
        int numberOfPoints = 360;

        Circle innerBound = new Circle(donutCenter, innerBoundRadius, numberOfPoints);
        Circle outerBound = new Circle(donutCenter, outerBoundRadius, numberOfPoints);

        donutTrack.innerBound = innerBound.getPoints();
        donutTrack.outerBound = outerBound.getPoints();

        return donutTrack;
    }

    public Point getTrackCentre() {
        Point outerBoundCenter = Math2.computeCenterOfPolygon(outerBound);
        Point innerBoundCenter = Math2.computeCenterOfPolygon(innerBound);

        Math2.LineSection section;
        section = new Math2.LineSection(innerBoundCenter, outerBoundCenter);

        return section.getCenter();
    }

}
