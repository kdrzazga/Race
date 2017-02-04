package libs.test;

import static libs.Assert.assertion;
import static libs.UnitTest.showTestPassedMessage;
import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.PolygonAG;

public class PolygonAgTests {

    private static void testGetLineSectionCrossedBy() {
        PolygonAG triangle;
        LineSection crossingLineSection = new LineSection(1200, 1200, 1300, 1000);
        PointAG[] trianglePts = {new PointAG(1100, 1100), new PointAG(2100, 1100), new PointAG(1100, 2100)};

        GIVEN:
        {
            triangle = new PolygonAG(trianglePts);
        }
        WHEN:
        {
            crossingLineSection = triangle.getLineSectionCrossedBy(crossingLineSection);
        }
        THEN:
        {
            assertion(crossingLineSection.p1.x, 1100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p1.y, 1100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p2.x, 2100, "testGetLineSectionCrossedBy");
            assertion(crossingLineSection.p2.y, 1100, "testGetLineSectionCrossedBy");
        }
    }

    public static void main(String[] args) {
        testGetLineSectionCrossedBy();
        showTestPassedMessage(PolygonAgTests.class.getSimpleName());
    }

}
