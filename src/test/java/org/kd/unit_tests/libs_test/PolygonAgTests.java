package org.kd.unit_tests.libs_test;

import org.kd.libs.math2.LineSection;
import org.kd.libs.math2.PointAG;
import org.kd.libs.math2.PolygonAG;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PolygonAgTests {

    private static PolygonAG triangle;
    private static LineSection crossingLineSection = new LineSection(1200, 1200, 1300, 1000);
    private static final PointAG[] TRIANGLE_PTS = {new PointAG(1100, 1100), new PointAG(2100, 1100), new PointAG(1100, 2100)};

    @Test
    public static void testGetLineSectionCrossedBy() {

        final var testName = "testGetLineSectionCrossedBy";

        GIVEN:
        {
            triangle = new PolygonAG(TRIANGLE_PTS);
        }
        WHEN_CROSSING_LINE_SECTION_COMPUTER:
        {
            crossingLineSection = triangle.getLineSectionCrossedBy(crossingLineSection);
        }
        THEN_CROSSING_LINE_SECTION_HAS_EXPECTED_POINTS:
        {

            assertTrue(crossingLineSection.p1.getX() == 1100.0, testName);
            assertTrue(crossingLineSection.p1.getY() == 1100.0, testName);
            assertTrue(crossingLineSection.p2.getX() == 2100, testName);
            assertTrue(crossingLineSection.p2.getY() == 1100, testName);
        }
    }

    private static final PointAG[] RECTNGLE_VERTICES = {new PointAG(10, 10), new PointAG(500, 15), new PointAG(490, 900), new PointAG(10, 890)};
    private static LineSection verticalLineSection, crossingSection;

    
    @Test
    public static void testGetLineSectionCrossingVerticalSection() {
        PolygonAG rectangle;
        final var testName = "testGetLineSectionCrossingVericalSection";

        GIVEN:
        rectangle = new PolygonAG(RECTNGLE_VERTICES);
        verticalLineSection = computeVerticalSectionDownFromCenter(rectangle);

        WHEN:
        crossingSection = rectangle.getLineSectionCrossingVerticalSection(verticalLineSection);

        THEN:
        assertTrue(crossingSection.p2.getX() == 10, testName);
        assertTrue(crossingSection.p2.getY() == 890, testName);
        assertTrue(crossingSection.p1.getX() == 490, testName);
        assertTrue(crossingSection.p1.getY() == 900, testName);
    }

    private static final PointAG[] CONCAVE_POLYGON1_VERTICES = {new PointAG(0, 900), new PointAG(123, 500), new PointAG(2500, 900),
        new PointAG(124, 900), new PointAG(123, 899), new PointAG(122, 900)};
    private static final PointAG[] CONCAVE_POLYGON2_VERTICES = {new PointAG(10.53f, 10), new PointAG(12.33f, 30), new PointAG(10, 50),
        new PointAG(40, 50), new PointAG(40, 10)};
    private static final PointAG[] CONVEX_POLYGON1_VERTICES = {new PointAG(1000.3f, 1000.12f), new PointAG(3000.3f, 2000.12f),
        new PointAG(5000.3f, 3000.12f), new PointAG(1000.3f, 3000.12f)};
    private static PolygonAG concavePolygon1UnderTest, convexPolygon1UnderTest, concavePolygon2UnderTest;

    @Test
    public static void testIsConvex() {
        final var test1Name = "testIsConvex on concavePolygon";

        GIVEN:
        concavePolygon1UnderTest = new PolygonAG(CONCAVE_POLYGON1_VERTICES);
        concavePolygon2UnderTest = new PolygonAG(CONCAVE_POLYGON2_VERTICES);
        convexPolygon1UnderTest = new PolygonAG(CONVEX_POLYGON1_VERTICES);

        THEN:
        assertFalse(concavePolygon1UnderTest.isConvex(), test1Name);
        assertFalse(concavePolygon2UnderTest.isConvex(), test1Name);
        //assertTrue(convexPolygon1UnderTest.isConvex(), "testIsConvex on convexPolygon");
    }

    private static LineSection computeVerticalSectionDownFromCenter(PolygonAG rectangle) {
        PointAG verticalSectionP1 = rectangle.computeCenter();
        PointAG verticalSectionP2 = new PointAG(verticalSectionP1.getX(), 50000);
        return new LineSection(verticalSectionP1, verticalSectionP2);

    }
}
