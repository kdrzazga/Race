package libs.math2;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;

public class PolygonAG {
    //AG stands for Analitycal Geometry

    public ArrayList<PointAG> points;

    public PolygonAG() {
        this.points = new ArrayList<>();
    }

    public PolygonAG(PointAG points[]) {
        this.points = new ArrayList<>();
        this.points.addAll(Arrays.asList(points));
    }

    public PolygonAG(PolygonAG polygonToBeCloned) {
        this.points = new ArrayList<>();
        this.points.addAll(polygonToBeCloned.points);
    }

    public void addPoint(int x, int y) {
        this.points.add(new PointAG(x, y));
    }

    public void addPointAG(float x, float y) {
        this.points.add(new PointAG(x, y));
    }

    public LineSection getLineSectionCrossedBy(LineSection lineSection) {
        if (lineSection.vertical) {
            return getLineSectionCrossingVerticalLine(lineSection);
        } else {
            for (int i = 0; i < this.points.size() - 2; i++) {
                LineSection lineSectionToCheck = new LineSection(this.points.get(i), this.points.get(i + 1));
                PointAG intersection = lineSectionToCheck.computeIntersection(lineSection);
                if (intersection != null) {
                    return lineSectionToCheck;
                }
            }
            return null;
        }
    }

    public LineSection getLineSectionCrossingVerticalLine(LineAG line) {
        if (!line.vertical) {
            throw new RuntimeException("Wrong argument - line not vertical. " + libs.math2.PolygonAG.class.getName());
        }

        int i;

        for (i = 0; i < this.points.size() - 2; i++) {
            PointAG p1 = this.points.get(i);
            PointAG p2 = this.points.get(i + 1);
            if (p1.x < line.verticalX && p2.x > line.verticalX) {
                break;
            }
        }

        return new LineSection(this.points.get(i), this.points.get(i + 1));
    }

    public Polygon convertToPolygon() {
        Polygon result = new Polygon();

        for (int i = 0; i < this.points.size(); i++) {
            Point p = this.points.get(i).convertToPoint();
            result.addPoint(p.x, p.y);
        }

        return result;
    }

    public PointAG computeCenter() {

        /*Polygon polygon = this.convertToPolygon();

        if (polygon.xpoints.length != polygon.ypoints.length) {
            throw new RuntimeException(" Polygon error - number of X coornates differs from number of Ys");
        }*/
        int sumXCoordinates = 0;
        int sumYCoordinates = 0;

        for (PointAG point : this.points) {
            sumXCoordinates += point.x;
            sumYCoordinates += point.y;
        }

        float centerX = Numbers.roundToFloat(sumXCoordinates / this.points.size());
        float centerY = Numbers.roundToFloat(sumYCoordinates / this.points.size());

        return new PointAG(centerX, centerY);
    }

    public void scale(float scaleFactor) {
        PointAG center = this.computeCenter();

        ArrayList<PointAG> scaledPoints = new ArrayList<>(this.points.size());

        this.points.stream().map((point) -> new LineSection(center, point)).map((ray) -> {
            ray.moveP2MultiplyingBy(scaleFactor);
            return ray;
        }).forEachOrdered((ray) -> {
            scaledPoints.add(ray.p2);
        });

        this.points = scaledPoints;
    }

    public float[] getXPoints() {
        float[] xpoints = new float[this.points.size()];

        for (int i = 0; i < this.points.size(); i++) {
            xpoints[i] = this.points.get(i).x;
        }
        return xpoints;
    }

    public float[] getYPoints() {
        float[] ypoints = new float[this.points.size()];

        for (int i = 0; i < this.points.size(); i++) {
            ypoints[i] = this.points.get(i).y;
        }
        return ypoints;
    }
}
