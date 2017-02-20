package libs.test;

import libs.Test;
import libs.math2.LineAG;
import libs.math2.PointAG;

public class LineAgTests extends Test {

    public static void main(String[] args) {
        testComputePerpendicularLine();
        testComputeX();
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

}
