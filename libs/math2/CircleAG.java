package libs.math2;

import logic.Board;

    public class CircleAG {

        private final int numberOfPoints;
        private final PolygonAG points;
        private final PointAG center;
        private float radius;

        public CircleAG(PointAG center, float radius, int numberOfPoints) {
            this.numberOfPoints = numberOfPoints;
            this.center = center;
            this.points = new PolygonAG();
            
            for (double angle = 0; angle < 2 * Math.PI; angle += 2 * Math.PI / numberOfPoints) {                
                float x  = center.x + radius * General.roundToFloat(Math.cos(angle), Board.ROUNDING_PRECISION);                        
                float y =  center.y + radius * General.roundToFloat(Math.sin(angle), Board.ROUNDING_PRECISION);
                
                points.addPointAG(x, y);
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