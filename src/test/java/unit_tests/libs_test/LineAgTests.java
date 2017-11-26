package unit_tests.libs_test;

import java.util.ArrayList;
import java.util.Random;
import libs.math2.LineAG;
import libs.math2.Numbers;
import libs.math2.PointAG;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class LineAgTests{

    @Test
    public static void testComputePerpendicularLine() {
        LineAG line, perpLine;
        final String methodName = "testComputePerpendicularLine";
        final float x = 100;
        final float y = 200;
        final PointAG intersection;
        final PointAG expectedIntersection;

        GIVEN:
        line = TestLinesLineSectionsBuilder.getDiagonalLine();
        expectedIntersection = new PointAG(150f, 150f);

        WHEN:
        perpLine = line.computePerpendicularLine(new PointAG(x, y));
        intersection = perpLine.findIntersection(line);

        THEN:
        assertEquals(perpLine.A, -1 / line.A);
        assertEquals(perpLine.computeY(x), y);
        assertEquals(intersection.x, expectedIntersection.x);
        assertEquals(intersection.y, expectedIntersection.y);
    }

    @Test
    public static void testComputeX() {
        LineAG diagonalLine;
        float x, y;

        GIVEN:
        diagonalLine = TestLinesLineSectionsBuilder.getDiagonalLine();
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
        ArrayList<LineAG> lines;
        LineAG line, lineToCompare;
        int lineIndex;

        GIVEN:
        commonPoint = new PointAG(10.2012f, 20.1891f);
        lines = new ArrayList<>();
        lines.add(new LineAG(new PointAG(0, 0), commonPoint));
        
        for (float angle = -3.141f; angle < 3.141f; angle += 0.05) {
            WHEN:
            A = Numbers.roundToFloat(Math.tan(angle));
            B = commonPoint.y - A * commonPoint.x;
            line = new LineAG(A, B);

            Random rand = new Random();
            lineIndex = rand.nextInt(lines.size());
            lineToCompare = lines.get(lineIndex);

            intersectionPoint = line.findIntersection(lineToCompare);
            lines.add(line);
            
            THEN:
            assertTrue(Math.abs(commonPoint.x - intersectionPoint.x) < 0.001f, "testFindIntersection");
            assertTrue(Math.abs(commonPoint.y - intersectionPoint.y) < 0.001f, "testFindIntersection");
        }
    }
}
