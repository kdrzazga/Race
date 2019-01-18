package org.kd.libs.math2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    public static final int ROUNDING_PRECISION = 4;

    public static double roundToDouble(double value) {
        return roundToDouble(value, ROUNDING_PRECISION);
    }

    public static double roundToDouble(double value, int places) {
        BigDecimal bd = roundUsingBigDecimal(value, places);
        return bd.doubleValue();
    }

    public static float roundToFloat(double value) {
        return roundToFloat(value, ROUNDING_PRECISION);
    }

    public static float roundToFloat(double value, int places) {
        BigDecimal bd = roundUsingBigDecimal(value, places);
        return bd.floatValue();
    }

    public static int roundToInt(double value) {
        BigDecimal bd = roundUsingBigDecimal(value, 0);
        return bd.intValue();
    }

    private static BigDecimal roundUsingBigDecimal(double value, int places) {
        var bd = BigDecimal.valueOf(value);
        bd = rounding(bd, places);
        return bd;
    }

    private static BigDecimal rounding(BigDecimal decimalValue, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        return decimalValue.setScale(places, RoundingMode.HALF_UP);
    }
}
