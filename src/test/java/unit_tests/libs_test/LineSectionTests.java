package unit_tests.libs_test;

import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.Numbers;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static libs.math2.LineSection.computeLength;
import org.testng.annotations.Test;

public class LineSectionTests{
 
    private final static LineSection[] LINE_SECTION = new LineSection[4];

    @Test
    public static void testMoveP1MultiplyingBy() {
        final String methodName = "testMoveP2MultiplyingBy";

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
            assertEquals(LINE_SECTION[0].p2.x, 20f, methodName);
            assertEquals(LINE_SECTION[0].p2.y, 20f, methodName);
            assertEquals(LINE_SECTION[1].p2.x, -10f, methodName);
            assertEquals(LINE_SECTION[1].p2.y, -10f, methodName);
            assertEquals(LINE_SECTION[2].p2.x, 0f, methodName);
            assertEquals(LINE_SECTION[2].p2.y, 80f, methodName);
            assertEquals(LINE_SECTION[3].p2.x, 10f, methodName);
            assertEquals(LINE_SECTION[3].p2.y, 27.326f, methodName);
        }

    }

    @Test
    public static void testComputeIntersection() {
        final String methodName = "testComputeIntersection";

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
            intersection1_2 = lineSection1.computeIntersection(lineSection2);
            nullIntersection1_3 = lineSection1.computeIntersection(lineSection3);
            intersection2_3 = lineSection3.computeIntersection(lineSection2);
            nullIntersection1 = pararellLineSection1.computeIntersection(pararellLineSection2);
        }

        THEN:
        {
            assertEquals(intersection1_2.x, 5f, methodName);
            assertEquals(intersection1_2.y, 5f, methodName);
            assertTrue(nullIntersection1_3 == null, methodName);//intersection at point (-14, -14)- outside both sections
            assertEquals(intersection2_3.x, 6.266667f, methodName);
            assertEquals(intersection2_3.y, 3.7333336f, methodName);
            assertTrue(nullIntersection1 == null, methodName);//pararell lines - no intersection
        }
    }

    private static LineSection verticalLineSection;
    private static PointAG p1, p2, p3;

    @Test
    public static void testXBelongsToLineSection() {
        final String methodName = "testXBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 10.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertTrue(verticalLineSection.xBelongsToLineSection.apply(p1.x), methodName);
        assertTrue(verticalLineSection.xBelongsToLineSection.apply(p2.x), methodName);
        assertFalse(verticalLineSection.xBelongsToLineSection.apply(p3.x), methodName);
    }

    @Test
    public static void testYBelongsToLineSection() {
        final String methodName = "testYBelongsToLineSection";

        GIVEN:
        p1 = new PointAG(10.3f, 100);
        p2 = new PointAG(10.3f, 10.3f);
        p3 = new PointAG(-10.3f, 20.3f);

        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));

        THEN:
        assertFalse(verticalLineSection.yBelongsToLineSection.apply(p1.y), methodName);
        assertTrue(verticalLineSection.yBelongsToLineSection.apply(p2.y), methodName);
        assertTrue(verticalLineSection.yBelongsToLineSection.apply(p3.y), methodName);
    }

    private static LineSection lineSection1;
    private static float ls1Length;

    //@Test
    public static void testCreatePararellSection() {
        GIVEN:

        lineSection1 = new LineSection(new PointAG(20, 20), new PointAG(40, 40));
        ls1Length = Numbers.roundToFloat(computeLength.apply(lineSection1));
        LineSection sameLineSection;
        WHEN:

        sameLineSection = lineSection1.createPararellSection(0);
        LineSection lineSectionAbove = lineSection1.createPararellSection(ls1Length + 1);
        LineSection lineSectionBelow = lineSection1.createPararellSection(-ls1Length - 1);
        THEN:

        ;
    }
}
