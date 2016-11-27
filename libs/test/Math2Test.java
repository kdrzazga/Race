package libs.test;

import libs.Math2;
import libs.Math2.LineSection;

public class Math2Test {

    public static void testInclinationAngle(LineSection section, double expectedAngle) {
        double angle = Math2.inclinationAngle(section);
        assert Math2.round(angle, 2) == expectedAngle;
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
    }

}
