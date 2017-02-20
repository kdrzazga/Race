package libs.test;

import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.Test;
import libs.math2.Numbers;

public class LineSectionTests extends Test {

    public static void main(String args[]) {
        testComputeIntersection();
        testMoveP1MultiplyingBy();
        testXBelongsToLineSection();
        testYBelongsToLineSection();
        testCreatePararellSection();
        showTestPassedMessage(LineSectionTests.class.getName());
    }

    public static LineSection lineSection[] = new LineSection[4];

    private static void testMoveP1MultiplyingBy() {
        final String methodName = "testMoveP2MultiplyingBy";

        GIVEN:
        {
            lineSection[0] = new LineSection(0, 0, 10, 10); //length 10*sqrt(2)
            lineSection[1] = new LineSection(10, 10, 0, 0);
            lineSection[2] = new LineSection(0, 5, 0, 15);
            lineSection[3] = new LineSection(10, 20.333f, 10, 41.333f);
        }

        WHEN:
        {
            lineSection[0].moveP2MultiplyingBy(2);
            lineSection[1].moveP2MultiplyingBy(2);
            lineSection[2].moveP2MultiplyingBy(7.5f);
            lineSection[3].moveP2MultiplyingBy(0.333f);
        }

        THEN:
        {

            assertion(lineSection[0].p2.x, 20f, methodName);
            assertion(lineSection[0].p2.y, 20f, methodName);
            assertion(lineSection[1].p2.x, -10f, methodName);
            assertion(lineSection[1].p2.y, -10f, methodName);
            assertion(lineSection[2].p2.x, 0f, methodName);
            assertion(lineSection[2].p2.y, 80f, methodName);
            assertion(lineSection[3].p2.x, 10f, methodName);
            assertion(lineSection[3].p2.y, 27.326f, methodName);
        }

    }
    private static LineSection lineSection2, lineSection3, pararellLineSection1, pararellLineSection2;
    private static PointAG intersection1_2, nullIntersection1_3, intersection2_3, nullIntersection1;

    public static void testComputeIntersection() {
        final String methodName = "testComputeIntersection";

        GIVEN:
        {
            lineSection1 = new LineSection(0, 0, 10, 10);
            lineSection2 = new LineSection(0, 10, 10, 0);
            lineSection3 = new LineSection(2, 0, 10, 7);
            verticalLineSection = new LineSection(5, 0, 10, 5);
            pararellLineSection1 = new LineSection(0, 2, 1, 0);
            pararellLineSection2 = new LineSection(0, 3, 6, 0);
        }

        WHEN:
        {
            intersection1_2 = lineSection1.computeIntersection(lineSection2);
            nullIntersection1_3 = lineSection1.computeIntersection(lineSection3);
            intersection2_3 = lineSection3.computeIntersection(lineSection2);
            nullIntersection1 = pararellLineSection1.computeIntersection(pararellLineSection2);
        }

        THEN:
        {
            assertion(intersection1_2.x, 5f, methodName);
            assertion(intersection1_2.y, 5f, methodName);
            assertion(nullIntersection1_3 == null, methodName);//intersection at point (-14, -14)- outside both sections
            assertion(intersection2_3.x, 6.266667f, methodName);
            assertion(intersection2_3.y, 3.7333336f, methodName);
            assertion(nullIntersection1 == null, methodName);//pararell lines - no intersection
        }
    }

    private static LineSection verticalLineSection;
    private static PointAG p1, p2, p3;

    public static void testXBelongsToLineSection() {
        final String methodName = "testXBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 10.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertion(verticalLineSection.xBelongsToLineSection(p1.x), methodName);
        assertion(verticalLineSection.xBelongsToLineSection(p2.x), methodName);
        assertion(!verticalLineSection.xBelongsToLineSection(p3.x), methodName);
    }

    public static void testYBelongsToLineSection() {
        final String methodName = "testYBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 20.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertion(!verticalLineSection.yBelongsToLineSection(p1.y), methodName);
        assertion(verticalLineSection.yBelongsToLineSection(p2.y), methodName);
        assertion(verticalLineSection.yBelongsToLineSection(p3.y), methodName);
    }

    private static LineSection lineSection1, lineSectionAbove, lineSectionBelow, sameLineSection;
    private static float ls1Length;

    public static void testCreatePararellSection() {
        GIVEN:

        lineSection1 = new LineSection(new PointAG(20, 20), new PointAG(40, 40));
        ls1Length = Numbers.roundToFloat(lineSection1.computeLength());
        WHEN:

        sameLineSection = lineSection1.createPararellSection(0);
        lineSectionAbove = lineSection1.createPararellSection(ls1Length + 1);
        lineSectionBelow = lineSection1.createPararellSection(-ls1Length - 1);
        THEN:

        ;
    }
}
