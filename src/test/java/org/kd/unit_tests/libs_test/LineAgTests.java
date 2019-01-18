package org.kd.unit_tests.libs_test;

import org.kd.libs.math2.LineAG;
import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PointAG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LineAgTests{

    @Test
    public static void testComputePerpendicularLine() {
        LineAG line, perpLine;
        final float x = 100;
        final float y = 200;
        final PointAG intersection;
        final PointAG expectedIntersection;

        GIVEN:
        line = TestLinesLineSectionsFactory.getDiagonalLine();
        expectedIntersection = new PointAG(150f, 150f);

        WHEN:
        perpLine = line.computePerpendicularLine(new PointAG(x, y));
        intersection = perpLine.findIntersection(line);

        THEN:
        assertEquals(perpLine.A, -1 / line.A);
        assertEquals(perpLine.computeY(x), y);
        assertEquals(intersection.getX(), expectedIntersection.getX());
        assertEquals(intersection.getY(), expectedIntersection.getY());
    }

    @Test
    public static void testComputeX() {
        LineAG diagonalLine;
        float x, y;

        GIVEN:
        diagonalLine = TestLinesLineSectionsFactory.getDiagonalLine();
        for (y = -150; y < 20 * Math.pow(10, 12); y += Math.pow(10, 7) * Math.random()) {
            WHEN:
            x = diagonalLine.computeX(y);

            THEN:
            assertEquals(x, y);
        }
    }

    @Test
    public static void testFindIntersection() {
        PointAG commonPoint, intersectionPoint;
        float A, B;
        List<LineAG> lines;
        LineAG line, lineToCompare;
        int lineIndex;

        GIVEN:
        commonPoint = new PointAG(10.2012f, 20.1891f);
        lines = new ArrayList<>();
        lines.add(new LineAG(new PointAG(0, 0), commonPoint));
        
        for (float angle = -3.141f; angle < 3.141f; angle += 0.05) {
            WHEN:
            A = Numbers.roundToFloat(Math.tan(angle));
            B = commonPoint.getY() - A * commonPoint.getX();
            line = new LineAG(A, B);

            var rand = new Random();
            lineIndex = rand.nextInt(lines.size());
            lineToCompare = lines.get(lineIndex);

            intersectionPoint = line.findIntersection(lineToCompare);
            lines.add(line);
            
            THEN:
            assertTrue(Math.abs(commonPoint.getX() - intersectionPoint.getX()) < 0.001f, "testFindIntersection");
            assertTrue(Math.abs(commonPoint.getY() - intersectionPoint.getY()) < 0.001f, "testFindIntersection");
        }
    }
}
