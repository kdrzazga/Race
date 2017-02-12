package libs.test;

import static libs.Assert.assertion;
import libs.UnitTest;
import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.PolygonAG;
import logic.Board;
import logic.BoardBuilder;

public class PolygonAgTests extends UnitTest{

    public static void main(String[] args) {
        testGetLineSectionCrossedBy();
        //testGetLineSectionCrossingVerticalSection();
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

    private static void testGetLineSectionCrossingVerticalSection() {

        PolygonAG rectangle;
        PointAG[] rectangleVertices = {new PointAG(10, 10), new PointAG(500, 15), new PointAG(490, 900), new PointAG(10, 890)};
        LineSection verticalLineSection, crossingSection;
        byte i = 0;
        GIVEN:

        rectangle = new PolygonAG(rectangleVertices);
        verticalLineSection = computeVerticalSectionDownFromCenter(rectangle);

        WHEN:
        crossingSection = rectangle.getLineSectionCrossingVerticalSection(verticalLineSection);
        
        THEN:
        
        assertion(crossingSection.p1.equals(new PointAG(500, 15)), "testGetLineSectionCrossingVericalSection");
        assertion(crossingSection.p2.equals(new PointAG(490, 900)), "testGetLineSectionCrossingVericalSection");
    }

    private static PolygonAG getOuterRectangleFromWeronikaTrack() {
        Board weronikaBoard = BoardBuilder.createBoardWithTrack(0, BoardBuilder.TrackType.WERONIKA);
        return weronikaBoard.track.outerBound;
    }

    private static LineSection computeVerticalSectionDownFromCenter(PolygonAG rectangle) {
        PointAG verticalSectionP1 = rectangle.computeCenter();
        PointAG verticalSectionP2 = new PointAG(verticalSectionP1.x, Float.MAX_VALUE);
        return new LineSection(verticalSectionP1, verticalSectionP2);

    }
}
