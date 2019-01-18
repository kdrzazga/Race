package org.kd.libs.math2;

public class CircleAG {

        private final int numberOfPoints;
        private final PolygonAG points;
        private final PointAG center;
        private float radius;

        public CircleAG(PointAG center, float radius, int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
            this.radius = radius;
            this.center = center;
            this.points = new PolygonAG();
            
            for (double angle = Math.PI; angle > -Math.PI; angle -= 2 * Math.PI / numberOfPoints) {
                float x  = center.getX() + radius * Numbers.roundToFloat(Math.cos(angle));
                float y =  center.getY() + radius * Numbers.roundToFloat(Math.sin(angle));
                
                points.addPointAG(x, y);
            }
        }

        public PolygonAG getPoints() {
            return points;
        }

        public PointAG getCenter() {
            return center;
        }

        public double getRadius() {
            return radius;
        }
    }