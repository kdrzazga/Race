package libs.test;

import java.awt.Point;
import java.awt.Polygon;
import static libs.Assert.assertion;
import libs.math2.General;
import libs.math2.LineAG;
import libs.math2.LineSection;
import libs.math2.PolygonAG;

public class Math2Test {

    public static void main(String[] args) {
        testPolygonAG();
        testInclinationAngle();
        testLine();
        testRoundingToFloat();
        testRoundingToDouble();

        System.out.println("No assertionion returned exception - all tests in " + Math2Test.class.getName() + " passed");
    }
    
    private static void testPolygonAG()
    {
        PolygonAG triangle2D = new PolygonAG();
        triangle2D.addPoint2D(-10.2f, -10.2f);
        triangle2D.addPoint2D(10.2f, -10.2f);
        triangle2D.addPoint2D(0f, 24.3f);
        
        Point expectedPts[] = new Point[3];
        
        expectedPts[0] = new Point(-10, -10);
        expectedPts[1] = new Point(10, -10);
        expectedPts[2] = new Point(0, 24);
        
        Polygon triangle = triangle2D.convertToPolygon();
        
        assertion(triangle.npoints == 3, "testPolygonAG");
        
        for (int i = 0; i < expectedPts.length - 1; i++)
        {
            assertion(triangle.xpoints[i], expectedPts[i].x, "testPolygonAG iteration=" + i);
            assertion(triangle.ypoints[i], expectedPts[i].y, "testPolygonAG iteration=" + i);
            
        }
    }

    private static void testInclinationAngle(LineSection section, double expectedAngle) {
        double angle = General.inclinationAngle(section);
        double rounded = General.roundToDouble(angle, 2);
        assertion(rounded == expectedAngle, "testInclinationAngle");
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
        testInclinationAngle(new LineSection(20, 20, 10, 10), 0.79);// 45 deg
        testInclinationAngle(new LineSection(0, 0, 20, -20), -0.79); //-45 deg
        testInclinationAngle(new LineSection(0, 0, 1, 2), -2.68); //-153.5 deg
        testInclinationAngle(new LineSection(0, 0, 0, 2), 3.14); //180 deg
        testInclinationAngle(new LineSection(0, 0, 0, -2), 0); //0 deg
        testInclinationAngle(new LineSection(0, 0, 2, 0), -1.57); //-90 deg
        testInclinationAngle(new LineSection(0, 0, -2, 0), 1.57); //90 deg

    }

    private static void testRoundingToFloat() {
        double x = 1.123956789;

        float expected[] = new float[5];
        expected[0] = 1.0f;
        expected[1] = 1.1f;
        expected[2] = 1.12f;
        expected[3] = 1.124f;
        expected[4] = 1.1240f;

        for (int i = 0; i < expected.length; i++) {
            assertion(General.roundToFloat(x, i), expected[i], " testRoundingToFloat");
        }
    }

    private static void testRoundingToDouble() {
        double x = 3.192837465;

        double expected[] = new double[5];
        expected[0] = 3.0;
        expected[1] = 3.2;
        expected[2] = 3.19;
        expected[3] = 3.193;
        expected[4] = 3.1928;

        for (int i = 0; i < expected.length; i++) {
            assertion(General.roundToDouble(x, i), expected[i], " testRoundingToFloat");
        }
    }

    private static void testLine() {
        LineAG line1 = new LineAG(1, 0);
        LineAG line2 = new LineAG(-1, 0);
        LineAG line3 = new LineAG(-1, 1);
        LineAG line4 = new LineAG(-1, 2);

        LineAG line5 = new LineAG(10, -2);
        LineAG line6 = new LineAG(2, 2);

        assertion(line1.findIntersection(line2).x, 0f, "testLine");
        assertion(line1.findIntersection(line2).y, 0f, "testLine");

        assertion(line1.findIntersection(line3).x, 0.5f, "testLine");

        assertion(line1.findIntersection(line3).y, 0.5f, "testLine");

        assertion(line1.findIntersection(line4).x, 1f, "testLine");
        assertion(line1.findIntersection(line4).y, 1f, "testLine");

        assertion(line5.findIntersection(line6).x, 0.5f, "testLine");
        assertion(line5.findIntersection(line6).y, 3.0f, "testLine");
    }

}
