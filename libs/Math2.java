package libs;

import java.awt.Point;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Math2 {

    public static class LineSection {

        Point p1;
        Point p2;

        public LineSection(int x1, int y1, int x2, int y2) {
            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);
        }

        public LineSection(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double getLength() {
            return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p1.x - p2.x), 2));
        }

        public Point getCenter() {
            int centerX = Math.round((p1.x + p2.x) / 2);
            int centerY = Math.round((p1.y + p2.y) / 2);
            return new Point(centerX, centerY);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Point computeCenterOfPolygon(Polygon polygon) {

        if (polygon.xpoints.length != polygon.ypoints.length) {
            new RuntimeException(" Polygon error - number of X coornates differs from number of Ys");
        }

        int sumXCoordinates = 0;
        int sumYCoordinates = 0;

        for (int i = 0; i < polygon.xpoints.length; i++) {
            sumXCoordinates += polygon.xpoints[i];
            sumYCoordinates += polygon.ypoints[i];
        }

        int centerX = sumXCoordinates / polygon.npoints;
        int centerY = sumYCoordinates / polygon.npoints;

        return new Point(centerX, centerY);
    }
    public static double inclinationAngle(LineSection section) {
	/* angles:
                0 deg
                |
        90 deg--o---    -90 deg
                |\ -135 deg
                180 deg       
        */
        double ySpan = section.p1.y - section.p2.y;
        double xSpan = section.p1.x - section.p2.x;
        return Math.atan2(xSpan, ySpan);
    }

}
