package libs;

import java.awt.Point;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Math2 {

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
}
