package libs.test;

import static libs.Assert.assertion;
import libs.math2.General;
import libs.math2.Line;
import libs.math2.LineSection;
import libs.math2.Point2D;

public class Math2Test {

    public static void testInclinationAngle(LineSection section, double expectedAngle) {
        double angle = General.inclinationAngle(section);
        assertion(General.round(angle, 2) == expectedAngle, "testInclinationAngle");
    }

    public static void testInclinationAngle2() {

        int radius = 10;

        for (double angle = -Math.PI; angle < Math.PI; angle += 0.05) {
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            System.out.println(x + ", " + y);
        }
    }
        
    public static void main(String[] args) {
        //testInclinationAngle(new LineSection(10, 10, 20, 20), -2.36);//135 deg
        testInclinationAngle(new LineSection(20, 20, 10, 10), 0.79);// 45 deg
        testInclinationAngle(new LineSection(0, 0, 20, -20), -0.79); //-45 deg
        testInclinationAngle(new LineSection(0, 0, 1, 2), -2.68); //-153.5 deg
        testInclinationAngle(new LineSection(0, 0, 0, 2), 3.14); //180 deg
        testInclinationAngle(new LineSection(0, 0, 0, -2), 0); //0 deg
        testInclinationAngle(new LineSection(0, 0, 2, 0), -1.57); //-90 deg
        testInclinationAngle(new LineSection(0, 0, -2, 0), 1.57); //90 deg
        
        Line line1 = new Line(1, 0);
        Line line2 = new Line(-1, 0);
        Line line3 = new Line(-1, 1);
        Line line4 = new Line(-1, 2);
        
        Line line5 = new Line(10, -2);
        Line line6 = new Line(2, 2);
        
        assertion(line1.findIntersection(line2).x, 0f , "main");
        assertion(line1.findIntersection(line2).y, 0f , "main");
        
        assertion(line1.findIntersection(line3).x, 0.5f , "main");
        assertion(line1.findIntersection(line3).y, 0.5f , "main");
        
        System.out.print("ssssssssa");
        assertion(line1.findIntersection(line4).x, 1f , "main");
        assertion(line1.findIntersection(line4).y, 1f , "main");
        
        assertion(line5.findIntersection(line6).x, 0.5f , "main");
        assertion(line5.findIntersection(line6).y, 3.0f , "main");
        
        System.out.println("No assertionion returned exception - all tests in " + Math2Test.class.getName() + " passed");
    }

}
