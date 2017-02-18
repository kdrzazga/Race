package libs.test;

import libs.math2.PointAG;
import libs.math2.PolygonAG;

public class TestPolygonsBuilder {

    public enum PolygonType {
        TRIANGLE, SMALL_TRIANGLE_2, BIG_TRIANGLE, CONVEX_TETRAGON_BIG, CONVEX_TETRAGON_SMALL
        , CONCAVE_TETRAGON_BIG, BASE_RECTANGLE;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Point arrays for shapes">
    private static final PointAG[] TRIANGLE_PTS = {
        new PointAG(-50, 50), new PointAG(50, 50), new PointAG(0, -36.603f)};
    
    private static final PointAG[] SMALL_TRIANGLE_2_PTS = {new PointAG(130, 98), new PointAG(170, 180), new PointAG(60, 190)};
    
    private static final PointAG[] BIG_TRIANGLE_PTS = {new PointAG(110, 50), new PointAG(200, 200), new PointAG(30, 240)};

    private static final PointAG[] TETRAGON_BIG_PTS = {
        new PointAG(-50, 50), new PointAG(650, 50), new PointAG(650, -50), new PointAG(-50, -50)};

    private static final PointAG[] TETRAGON_SMALL_PTS = {
        new PointAG(-50, 50), new PointAG(50, 50), new PointAG(50, -50), new PointAG(-50, -50)};
    
    private static final PointAG[] BASE_RECTANGLE_PTS = {new PointAG(350, 250), new PointAG(500, 250),
            new PointAG(500, 350), new PointAG(350, 350)};
    //</editor-fold>

    public static PolygonAG createPolygon(PolygonType polygonType) {
        switch (polygonType) {
            case TRIANGLE:
                return new PolygonAG(TRIANGLE_PTS);
            case SMALL_TRIANGLE_2:
                return new PolygonAG(SMALL_TRIANGLE_2_PTS);
            case BIG_TRIANGLE:
                return new PolygonAG(BIG_TRIANGLE_PTS);
            case CONVEX_TETRAGON_BIG:
                return new PolygonAG(TETRAGON_BIG_PTS);
            case BASE_RECTANGLE:
                return new PolygonAG(BASE_RECTANGLE_PTS);
            case CONCAVE_TETRAGON_BIG:
                throw new RuntimeException("Not implemented yet");
            default://case CONVEX_TETRAGON_SMALL:
                return new PolygonAG(TETRAGON_SMALL_PTS);
        }
    }

    public static PolygonAG createPolygon(PolygonType polygonType, PointAG move) {
        PolygonAG newPolygon;

        newPolygon = createPolygon(polygonType);

        for (PointAG point : newPolygon.points) {
            point.x += move.x;
            point.y += move.y;
        }

        return newPolygon;
    }
    
    public static PolygonAG createPolygonMovedBy500_500(PolygonType polygonType)
    {
        return createPolygon(polygonType, new PointAG(500, 500));
    }
    
    public static PointAG computePointOutsidePolygon(PolygonAG polygon)
    {
        float outsideX = polygon.computeMaxX() + 1;
        float outsideY = polygon.computeMaxY() + 1;
        return new PointAG(outsideX, outsideY);
    }
    public static PointAG computePointInsidePolygon(PolygonAG polygon)
    {
        float outsideX = polygon.computeMaxX() - 1;
        float outsideY = polygon.computeMaxY() - 1;
        return new PointAG(outsideX, outsideY);
    }
}
