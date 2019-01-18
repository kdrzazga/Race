package org.kd.unit_tests.libs_test;

import org.kd.libs.math2.LineSection;
import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PointAG;
import org.testng.annotations.Test;

import static org.kd.libs.math2.LineSection.computeLength;
import static org.testng.Assert.*;

public class LineSectionTests{
 
    private final static LineSection[] LINE_SECTION = new LineSection[4];

    @Test
    public static void testMoveP1MultiplyingBy() {
        final var methodName = "testMoveP2MultiplyingBy";

        GIVEN:
        {
            LINE_SECTION[0] = new LineSection(0, 0, 10, 10); //length 10*sqrt(2)
            LINE_SECTION[1] = new LineSection(10, 10, 0, 0);
            LINE_SECTION[2] = new LineSection(0, 5, 0, 15);
            LINE_SECTION[3] = new LineSection(10, 20.333f, 10, 41.333f);
        }

        WHEN:
        {
            LINE_SECTION[0].moveP2MultiplyingBy(2);
            LINE_SECTION[1].moveP2MultiplyingBy(2);
            LINE_SECTION[2].moveP2MultiplyingBy(7.5f);
            LINE_SECTION[3].moveP2MultiplyingBy(0.333f);
        }

        THEN:
        {
            assertEquals(LINE_SECTION[0].p2.getX(), 20f, methodName);
            assertEquals(LINE_SECTION[0].p2.getY(), 20f, methodName);
            assertEquals(LINE_SECTION[1].p2.getX(), -10f, methodName);
            assertEquals(LINE_SECTION[1].p2.getY(), -10f, methodName);
            assertEquals(LINE_SECTION[2].p2.getX(), 0f, methodName);
            assertEquals(LINE_SECTION[2].p2.getY(), 80f, methodName);
            assertEquals(LINE_SECTION[3].p2.getX(), 10f, methodName);
            assertEquals(LINE_SECTION[3].p2.getY(), 27.326f, methodName);
        }

    }

    @Test
    public static void testComputeIntersection() {
        final var methodName = "testComputeIntersection";

        LineSection lineSection2;
        LineSection lineSection3;
        LineSection pararellLineSection1;
        LineSection pararellLineSection2;
        GIVEN:
        {
            lineSection1 = new LineSection(0, 0, 10, 10);
            lineSection2 = new LineSection(0, 10, 10, 0);
            lineSection3 = new LineSection(2, 0, 10, 7);
            verticalLineSection = new LineSection(5, 0, 10, 5);
            pararellLineSection1 = new LineSection(0, 2, 1, 0);
            pararellLineSection2 = new LineSection(0, 3, 6, 0);
        }

        PointAG intersection1_2;
        PointAG nullIntersection1_3;
        PointAG nullIntersection1;
        PointAG intersection2_3;
        WHEN:
        {
            intersection1_2 = lineSection1.computeIntersection.apply(lineSection2);
            nullIntersection1_3 = lineSection1.computeIntersection.apply(lineSection3);
            intersection2_3 = lineSection3.computeIntersection.apply(lineSection2);
            nullIntersection1 = pararellLineSection1.computeIntersection.apply(pararellLineSection2);
        }

        THEN:
        {
            assertEquals(intersection1_2.getX(), 5f, methodName);
            assertEquals(intersection1_2.getY(), 5f, methodName);
            assertTrue(nullIntersection1_3 == null, methodName);//intersection at point (-14, -14)- outside both sections
            assertEquals(intersection2_3.getX(), 6.266667f, methodName);
            assertEquals(intersection2_3.getY(), 3.7333336f, methodName);
            assertTrue(nullIntersection1 == null, methodName);//pararell lines - no intersection
        }
    }

    private static LineSection verticalLineSection;
    private static PointAG p1, p2, p3;

    @Test
    public static void testXBelongsToLineSection() {
        final var methodName = "testXBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 10.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertTrue(verticalLineSection.xBelongsToLineSection.apply(p1.getX()), methodName);
        assertTrue(verticalLineSection.xBelongsToLineSection.apply(p2.getX()), methodName);
        assertFalse(verticalLineSection.xBelongsToLineSection.apply(p3.getX()), methodName);
    }

    @Test
    public static void testYBelongsToLineSection() {
        final var methodName = "testYBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 20.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertFalse(verticalLineSection.yBelongsToLineSection.apply(p1.getY()), methodName);
        assertTrue(verticalLineSection.yBelongsToLineSection.apply(p2.getY()), methodName);
        assertTrue(verticalLineSection.yBelongsToLineSection.apply(p3.getY()), methodName);
    }

    private static LineSection lineSection1;

    //@Test
    public static void testCreatePararellSection() {
        float ls1Length;
        GIVEN:

        lineSection1 = new LineSection(new PointAG(20, 20), new PointAG(40, 40));
        ls1Length = Numbers.roundToFloat(computeLength.apply(lineSection1));
        LineSection sameLineSection;
        WHEN:

        sameLineSection = lineSection1.createPararellSection.apply(0f);
        LineSection lineSectionAbove = lineSection1.createPararellSection.apply(ls1Length + 1);
        LineSection lineSectionBelow = lineSection1.createPararellSection.apply(-ls1Length - 1);
        THEN:

        ;
    }
}
