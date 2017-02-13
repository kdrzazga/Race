package libs.test;

import libs.math2.LineSection;
import libs.math2.PointAG;
import static libs.Assert.assertion;
import libs.UnitTest;
import libs.math2.Numbers;

public class LineSectionTests extends UnitTest {

    public static void main(String args[]) {
        testComputeIntersection();
        testMoveP1MultiplyingBy();
        testXBelongsToLineSection();
        testYBelongsToLineSection();
        showTestPassedMessage(LineSectionTests.class.getName());
    }

    private static void testMoveP1MultiplyingBy() {
        LineSection lineSection[] = new LineSection[4];

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
            assertion(lineSection[0].p2.x, 20f, "testMoveP2MultiplyingBy");
            assertion(lineSection[0].p2.y, 20f, "testMoveP2MultiplyingBy");
            assertion(lineSection[1].p2.x, -10f, "testMoveP2MultiplyingBy");
            assertion(lineSection[1].p2.y, -10f, "testMoveP2MultiplyingBy");
            assertion(lineSection[2].p2.x, 0f, "testMoveP2MultiplyingBy");
            assertion(lineSection[2].p2.y, 80f, "testMoveP2MultiplyingBy");
            assertion(lineSection[3].p2.x, 10f, "testMoveP2MultiplyingBy");
            assertion(lineSection[3].p2.y, 27.326f, "testMoveP2MultiplyingBy");
        }

    }

    public static void testComputeIntersection() {
        LineSection lineSection1, lineSection2, lineSection3, verticalLineSection, pararellLineSection1, pararellLineSection2;

        PointAG intersection1_2, nullIntersection1_3, intersection2_3, nullIntersection1;

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
            assertion(intersection1_2.x, 5f, "testComputeIntersection");
            assertion(intersection1_2.y, 5f, "testComputeIntersection");
            assertion(nullIntersection1_3 == null, "testComputeIntersection");//intersection at point (-14, -14)- outside both sections
            assertion(intersection2_3.x, 6.266667f, "testComputeIntersection");
            assertion(intersection2_3.y, 3.7333336f, "testComputeIntersection");
            assertion(nullIntersection1 == null, "testComputeIntersection");//pararell lines - no intersection
        }
    }

    public static void testXBelongsToLineSection() {
        LineSection verticalLineSection;
        PointAG p1 = new PointAG(10.3f, 100);
        PointAG p2 = new PointAG(10.3f, 10.3f);
        PointAG p3 = new PointAG(-10.3f, 10.3f);
        
        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));
        
        THEN:
        assertion(verticalLineSection.xBelongsToLineSection(p1.x), "testXBelongsToLineSection");
        assertion(verticalLineSection.xBelongsToLineSection(p2.x), "testXBelongsToLineSection");
        assertion(!verticalLineSection.xBelongsToLineSection(p3.x), "testXBelongsToLineSection");
    }

    public static void testYBelongsToLineSection() {
        LineSection verticalLineSection;
        PointAG p1 = new PointAG(10.3f, 100);
        PointAG p2 = new PointAG(10.3f, 10.3f);
        PointAG p3 = new PointAG(-10.3f, 20.3f);
        
        WHEN:
        verticalLineSection = new LineSection(10.3f, 33.33f, 10.3f, Numbers.roundToFloat(Math.PI));
        
        THEN:
        assertion(!verticalLineSection.yBelongsToLineSection(p1.y), "testYBelongsToLineSection");
        assertion(verticalLineSection.yBelongsToLineSection(p2.y), "testYBelongsToLineSection");
        assertion(verticalLineSection.yBelongsToLineSection(p3.y), "testYBelongsToLineSection");
    }
}
