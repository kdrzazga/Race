package libs.test;

import libs.UnitTest;
import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.PolygonAG;

public class PolygonAgTests extends UnitTest {

    public static void main(String[] args) {
        testGetLineSectionCrossedBy();
        testGetLineSectionCrossingVerticalSection();
        //testIsConvex();
        showTestPassedMessage(PolygonAgTests.class.getSimpleName());
    }

    private static void testGetLineSectionCrossedBy() {
        PolygonAG triangle;
        LineSection crossingLineSection = new LineSection(1200, 1200, 1300, 1000);
        PointAG[] trianglePts = {new PointAG(1100, 1100), new PointAG(2100, 1100), new PointAG(1100, 2100)};

        GIVEN:
        {
            triangle = new PolygonAG(trianglePts);
        }
        WHEN_CROSSING_LINE_SECTION_COMPUTER:
        {
            crossingLineSection = triangle.getLineSectionCrossedBy(crossingLineSection);
        }
        THEN_CROSSING_LINE_SECTION_HAS_EXPECTED_POINTS:
        {
            assertion(crossingLineSection.p1.x, 1100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p1.y, 1100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p2.x, 2100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p2.y, 1100, "testGetLineSectionCrossedBy");
        }
    }

    private static void testGetLineSectionCrossingVerticalSection() {

        PolygonAG rectangle;
        PointAG[] rectangleVertices = {new PointAG(10, 10), new PointAG(500, 15), new PointAG(490, 900), new PointAG(10, 890)};
        LineSection verticalLineSection, crossingSection;

        GIVEN:
        rectangle = new PolygonAG(rectangleVertices);
        verticalLineSection = computeVerticalSectionDownFromCenter(rectangle);

        WHEN:
        crossingSection = rectangle.getLineSectionCrossingVerticalSection(verticalLineSection);

        THEN:
        assertion(crossingSection.p2.equals(new PointAG(10, 890)), "testGetLineSectionCrossingVericalSection");
        assertion(crossingSection.p1.equals(new PointAG(490, 900)), "testGetLineSectionCrossingVericalSection");
    }

    private static void testIsConvex() {
        PointAG[] concave1PolygonVertices = {new PointAG(0,900), new PointAG(123, 500), new PointAG(2500, 900),
            new PointAG(124, 900), new PointAG(123, 899), new PointAG(122, 900)};
        PointAG[] concave2PolygonVertices = {new PointAG(10.53f, 10), new PointAG(12.33f, 30), new PointAG(10, 50),
            new PointAG(40, 50), new PointAG(40, 10)};
        PointAG[] convexPolygon1Vertices = {new PointAG(1000.3f, 1000.12f), new PointAG(3000.3f, 2000.12f),
            new PointAG(5000.3f, 3000.12f), new PointAG(1000.3f, 3000.12f)};
        PolygonAG concavePolygon1UnderTest, convexPolygon1UnderTest, concavePolygon2UnderTest;
        
        GIVEN:
        concavePolygon1UnderTest = new PolygonAG(concave1PolygonVertices);
        concavePolygon2UnderTest = new PolygonAG(concave2PolygonVertices);
        convexPolygon1UnderTest = new PolygonAG(convexPolygon1Vertices);
        
        THEN:
        assertion(!concavePolygon1UnderTest.isConvex(), "testIsConvex on concavePolygon 1");
        assertion(!concavePolygon2UnderTest.isConvex(), "testIsConvex on concavePolygon 2");
        assertion(convexPolygon1UnderTest.isConvex(), "testIsConvex on convexPolygon");
    }

    private static LineSection computeVerticalSectionDownFromCenter(PolygonAG rectangle) {
        PointAG verticalSectionP1 = rectangle.computeCenter();
        PointAG verticalSectionP2 = new PointAG(verticalSectionP1.x, 50000);
        return new LineSection(verticalSectionP1, verticalSectionP2);

    }
}
