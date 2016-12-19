package libs.test;

import java.awt.Point;
import java.awt.Polygon;
import static libs.Assert.assertion;
import libs.math2.General;
import libs.math2.LineAG;
import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.PolygonAG;

public class Math2Test {

    public static void main(String[] args) {

        boolean allPassed = true;

        try {
            
            testPolygonAG();
            testInclinationAngle();
            testLine();
            testRoundingToFloat();
            testRoundingToDouble();
            
        } catch (RuntimeException rex) {
            allPassed = false;
            System.err.println(rex);
        }

        if (allPassed) {
            System.out.println("No assertionion returned exception - all tests in " + Math2Test.class.getName() + " passed");
        }
    }

    private static void testPolygonAG() {
        PolygonAG triangle2D = new PolygonAG();
        triangle2D.addPointAG(-10.2f, -10.2f);
        triangle2D.addPointAG(10.2f, -10.2f);
        triangle2D.addPointAG(0.0f, 24.3f);//0,24.3

        Point[] expectedConvertedPts = {new Point(-10, -10),
             new Point(10, -10),
             new Point(0, 24)
        };

        Polygon triangle = triangle2D.convertToPolygon();

        assertion(triangle.npoints == 3, "testPolygonAG");

        for (int i = 0; i < expectedConvertedPts.length - 1; i++) {
            assertion(triangle.xpoints[i], expectedConvertedPts[i].x, "testPolygonAG iteration=" + i);
            assertion(triangle.ypoints[i], expectedConvertedPts[i].y, "testPolygonAG iteration=" + i);
        }
    }

    public static void testInclinationAngle2() {

        int radius = 10;

        for (double angle = -Math.PI; angle < Math.PI; angle += 0.05) {
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            System.out.println(x + ", " + y);
        }
    }

    private static void testInclinationAngle() {
        LineSection sections[] = {
            new LineSection(20, 20, 10, 10),
            new LineSection(0, 0, 20, -20),
            new LineSection(0, 0, 1, 2),
            new LineSection(0, 0, 0, 2),
            new LineSection(0, 0, 0, -2),
            new LineSection(0, 0, 2, 0),
            new LineSection(0, 0, -2, 0)
        };

        double expectedAngles[] = {0.79, -0.79, -2.68, 3.14, 0, -1.57, 1.57}; // 45 deg, -45 deg, -153.5 deg, 180 deg, 0 deg, -90 deg, 90 deg

        for (int i = 0; i < sections.length; i++) {
            int precision = 2;
            double actual = General.roundToDouble(General.inclinationAngle(sections[i]), precision);
            assertion(actual, expectedAngles[i], "testInclinationAngle()");
        }

    }

    private static void testRoundingToFloat() {
        double x = 1.123956789;

        float expected[] = {1.0f, 1.1f, 1.12f, 1.124f, 1.1240f};

        for (int i = 0; i < expected.length; i++) {
            assertion(General.roundToFloat(x, i), expected[i], " testRoundingToFloat");
        }
    }

    private static void testRoundingToDouble() {
        double x = 3.192837465;

        double expected[] = {3.0, 3.2, 3.19, 3.193, 3.1928};

        for (int i = 0; i < expected.length; i++) {
            assertion(General.roundToDouble(x, i), expected[i], " testRoundingToFloat");
        }
    }

    private static void testLine() {
        LineAG line1 = new LineAG(1, 0);
        LineAG lines[] = {new LineAG(-1, 0), new LineAG(-1, 1), new LineAG(-1, 2), new LineAG(10, -2), new LineAG(2, 2)};

        PointAG expIntersectWithLine1[] = {new PointAG(0f, 0f), new PointAG(0.5f, 0.5f), new PointAG(1f, 1f),
             new PointAG(0.22222222f, 0.22222222f), new PointAG(-2f, -2f)};

        for (int i = 0; i < lines.length - 1; i++) {
            assertion(lines[i].findIntersection(line1).x, expIntersectWithLine1[i].x, "testLine iteration=" + i);
            assertion(line1.findIntersection(lines[i]).y, expIntersectWithLine1[i].y, "testLine iteration=" + i);
        }

    }

}
