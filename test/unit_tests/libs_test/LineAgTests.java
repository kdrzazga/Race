package libs.test;

import java.util.ArrayList;
import java.util.Random;
import libs.UnitTest;
import libs.math2.LineAG;
import libs.math2.Numbers;
import libs.math2.PointAG;

public class LineAgTests extends UnitTest {

    public static void main(String[] args) {
        testComputePerpendicularLine();
        testComputeX();
        testFindIntersection();
        showTestPassedMessage("LineAgTests");
    }

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
        assertion(perpLine.A, -1 / line.A, methodName);
        assertion(perpLine.computeY(x), y, methodName);
        assertion(intersection.x, expectedIntersection.x, methodName);
        assertion(intersection.y, expectedIntersection.y, methodName);
    }

    public static void testComputeX() {
        LineAG diagonalLine;
        float x = 0, y;

        GIVEN:
        diagonalLine = TestLinesLineSectionsBuilder.getDiagonalLine();
        for (y = -150; y < 20 * Math.pow(10, 12); y += Math.pow(10, 7) * Math.random()) {
            WHEN:
            x = diagonalLine.computeX(y);

            THEN:
            assertion(x, y, "testComputeX");
        }
    }

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
            assertion(Math.abs(commonPoint.x - intersectionPoint.x) < 0.0001f, "testFindIntersection");
            assertion(Math.abs(commonPoint.y - intersectionPoint.y) < 0.0001f, "testFindIntersection");
        }
    }
}
