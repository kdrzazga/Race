package libs.math2;

import java.awt.Point;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class General {

    public static final int ROUNDING_PRECISION = 4;

    public static double roundToDouble(double value) {
        return roundToDouble(value, ROUNDING_PRECISION);
    }

    public static double roundToDouble(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = rounding(bd, places);
        return bd.doubleValue();
    }

    public static float roundToFloat(double value) {
        return roundToFloat(value, ROUNDING_PRECISION);
    }

    public static float roundToFloat(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = rounding(bd, places);

        float result = bd.floatValue();
        return result;
    }

    public static Point computeCenterOfPolygon(Polygon polygon) {

        if (polygon.xpoints.length != polygon.ypoints.length) {
            throw new RuntimeException(" Polygon error - number of X coornates differs from number of Ys");
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



    private static BigDecimal rounding(BigDecimal decimalValue, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal scaledValue = decimalValue.setScale(places, RoundingMode.HALF_UP);

        return scaledValue;
    }
}
