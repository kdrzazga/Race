package org.kd.libs.math2;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PolygonAG implements Cloneable {
    //AG stands for Analitical Geometry

    public List<PointAG> points;

    public PolygonAG() {
        this.points = new ArrayList<>();
    }

    public PolygonAG(PointAG[] points) {
        this.points = new ArrayList<>();
        this.points.addAll(Arrays.asList(points));
    }

    @Override
    public PolygonAG clone() {
        var clonedPolygon = new PolygonAG();
        clonedPolygon.points = List.copyOf(this.points);
        return clonedPolygon;
    }

    public void addPoint(int x, int y) {
        this.points.add(new PointAG(x, y));
    }

    public void addPointAG(float x, float y) {
        this.points.add(new PointAG(x, y));
    }

    public LineSection getLineSectionCrossedBy(LineSection lineSection) {
        if (lineSection.vertical) {
            return getLineSectionCrossingVerticalSection(lineSection);
        } else {

            for (int i = 0; i < this.points.size() - 2; i++) {
                LineSection lineSectionToCheck = new LineSection(this.points.get(i), this.points.get(i + 1));
                PointAG intersection = lineSectionToCheck.computeIntersection.apply(lineSection);
                if (intersection != null) {
                    return lineSectionToCheck;
                }
            }
            return null;
        }
    }

    public LineSection getLineSectionCrossingVerticalSection(LineSection section) {
        if (!section.vertical) {
            throw new RuntimeException("Wrong argument - line not vertical. " + PolygonAG.class.getName());
        }

        List<LineSection> sections = IntStream.range(1, points.size())
                .mapToObj(j -> new LineSection(this.points.get(j - 1), this.points.get(j)))
                .collect(Collectors.toList());

        for (var result : sections) {
            PointAG p1 = result.p1;
            PointAG p2 = result.p2;

            float minX = Math.min(p1.getX(), p2.getX());
            float maxX = Math.max(p1.getX(), p2.getX());

            if (minX < section.verticalX && maxX > section.verticalX) {

                PointAG intersection = section.computeIntersection.apply(result);
                if (intersection != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public Polygon convertToPolygon() {
        var result = new Polygon();

        for (var point : this.points) {
            var p = point.convertToPoint();
            result.addPoint(p.x, p.y);
        }

        return result;
    }

    public PointAG computeCenter() {
        int sumXCoordinates = 0;
        int sumYCoordinates = 0;

        for (PointAG point : this.points) {
            sumXCoordinates += point.getX();
            sumYCoordinates += point.getY();
        }

        float centerX = Numbers.roundToFloat(sumXCoordinates / (float) this.points.size());
        float centerY = Numbers.roundToFloat(sumYCoordinates / (float) this.points.size());

        return new PointAG(centerX, centerY);
    }

    public void scale(float scaleFactor) {
        scaleConvexPolygon(scaleFactor);
    }

    public PointAG computeCentroid() {
        double A = this.computeSignedArea();
        if (A == 0)
            return this.points.get(0);
        else {
            double CxSum = 0;
            double CySum = 0;
            for (int i = 0; i < this.points.size() - 1; i++) {
                PointAG p = this.points.get(i);
                PointAG pPlus1 = this.points.get(i + 1);

                double factor = (p.getX() * pPlus1.getY() - pPlus1.getX() * p.getY());
                CxSum += (p.getX() + pPlus1.getX()) * factor;
                CySum += (p.getY() + pPlus1.getY()) * factor;
            }
            double Cx = CxSum / (6 * A);
            double Cy = CySum / (6 * A);

            return new PointAG(Numbers.roundToFloat(Cx), Numbers.roundToFloat(Cy));
        }
    }

    private double computeSignedArea() {
        double factor = 0;
        for (int i = 0; i < this.points.size() - 1; i++) {
            PointAG p = this.points.get(i);
            PointAG pPlus1 = this.points.get(i + 1);

            factor += (p.getX() * pPlus1.getY() - pPlus1.getX() * p.getY());
        }
        return factor / 2;
    }

    private void scaleConvexPolygon(float scaleFactor) {
        PointAG center = this.computeCentroid();

        List<PointAG> scaledPoints = new ArrayList<>(this.points.size());

        this.points.stream().map(point -> new LineSection(center, point)).map(ray -> {
            ray.moveP2MultiplyingBy(scaleFactor);
            return ray;
        }).forEachOrdered(ray -> scaledPoints.add(ray.p2));

        this.points = scaledPoints;
    }

    public LineSection findSideClosestToPoint(PointAG point) {
        double minDistance = Double.MAX_VALUE;
        int vertexIndex = 0;
        LineSection side;

        for (int i = 0; i < this.points.size() - 2; i++) {
            side = new LineSection(points.get(i), points.get(i + 1));
            PointAG sideCenter = side.computeCenter();

            double distance = sideCenter.distanceToAntherPointAG(point);
            if (distance < minDistance) {
                vertexIndex = i;
                minDistance = distance;
            }
        }

        return new LineSection(points.get(vertexIndex), points.get(vertexIndex + 1));
    }

    public boolean isConvex() {
        checkIfPolygonBigEnough();

        final int pointsCount = this.points.size();
        final var truncatedPolygon = this.convertToPolygon();

        for (int i = 0; i < pointsCount; i++) {
            PointAG pointI = this.points.get(i);
            for (int j = i + 1; j < pointsCount; j++) {
                PointAG pointJ = this.points.get(j);
                var side = new LineSection(pointI, pointJ);
                PointAG sectionCenter = side.computeCenter();

                if (truncatedPolygon.contains(sectionCenter.getX(), sectionCenter.getY())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkIfPolygonBigEnough() {
        for (int i = 0; i < this.points.size() - 1; i++) {
            LineSection section = new LineSection(this.points.get(i), this.points.get(i + 1));
            if (LineSection.computeLength.apply(section) < 1) {
                throw new RuntimeException("Polygon too small to check if it's convex"
                        + " with this algorithm, pls google a different algorithm.");
            }
        }
    }

    private Float[] getXPoints() {
        return this.points.stream().map(PointAG::getX).toArray(Float[]::new);
    }

    private Float[] getYPoints() {
        return this.points.stream().map(PointAG::getY).toArray(Float[]::new);
    }

    public float computeMinX() {
        return List.of(getXPoints()).stream().min(Float::compareTo).orElse(Float.MAX_VALUE);
    }

    public float computeMaxX() {
        return List.of(getYPoints()).stream().max(Float::compareTo).orElse(Float.MIN_VALUE);
    }

    public float computeMinY() {
        return List.of(getYPoints()).stream().min(Float::compareTo).orElse(Float.MAX_VALUE);
    }

    public float computeMaxY() {
        return List.of(getYPoints()).stream().max(Float::compareTo).orElse(Float.MIN_VALUE);
    }
}
