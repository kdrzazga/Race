package libs;

import libs.math2.LineSection;

public class Math2Assert {

    public static void assertLineSectionsEqualNoMatterPointsOrder(LineSection expectedLineSection,
             LineSection actualLineSection) {

        float x1act = actualLineSection.p1.x;
        float y1act = actualLineSection.p1.y;

        float x2act = actualLineSection.p2.x;
        float y2act = actualLineSection.p2.y;

        float x1exp = expectedLineSection.p1.x;
        float y1exp = expectedLineSection.p1.y;

        float x2exp = expectedLineSection.p2.x;
        float y2exp = expectedLineSection.p2.y;

        if (x1act != x1exp || y1act != y1exp || x2act != x2exp || y2act != y2exp) {
            if (x1act != x2exp || y1act != y2exp || x2act != x1exp || y2act != y1exp) {

                throw new RuntimeException("Assertion failed. Actual = " + actualLineSection
                        + ". Expected = " + expectedLineSection);
            }
        }
    }
    
}
