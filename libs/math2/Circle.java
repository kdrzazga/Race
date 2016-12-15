package libs.math2;

import java.awt.Point;

    public class Circle {

        private final int numberOfPoints;
        private final PolygonAG points;
        private final Point center;
        private double radius;

        public Circle(Point center, double radius, int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
            this.center = center;
            this.points = new PolygonAG();
            int roundingPrecision = 2;

            for (double angle = 0; angle < 2 * Math.PI; angle += 2 * Math.PI / numberOfPoints) {
                int x = center.x + (int) General.round(radius * Math.cos(angle), roundingPrecision);
                int y = center.y + (int) General.round(radius * Math.sin(angle), roundingPrecision);
                points.addPoint(x, y);
            }
        }
    
        public int getNumberOfPoints()
        {
            return this.numberOfPoints;
        }

        public PolygonAG getPoints() {
            return points;
        }

        public Point getCenter() {
            return center;
        }

        public double getRadius() {
            return radius;
        }
    }