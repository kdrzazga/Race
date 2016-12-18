package libs.math2;

    public class CircleAG {

        private final int numberOfPoints;
        private final PolygonAG points;
        private final PointAG center;
        private float radius;

        public CircleAG(PointAG center, float radius, int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
            this.center = center;
            this.points = new PolygonAG();
            int roundingPrecision = 6;

            for (double angle = 0; angle < 2 * Math.PI; angle += 2 * Math.PI / numberOfPoints) {                
                float x  = center.x + radius * General.roundToFloat(Math.cos(angle), roundingPrecision);                        
                float y =  center.y + radius * General.roundToFloat(Math.sin(angle), roundingPrecision);
                
                points.addPoint2D(x, y);
            }
        }
    
        public int getNumberOfPoints()
        {
            return this.numberOfPoints;
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