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

 private static BigDecimal rounding(BigDecimal decimalValue, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal scaledValue = decimalValue.setScale(places, RoundingMode.HALF_UP);

        return scaledValue;
    }
}
