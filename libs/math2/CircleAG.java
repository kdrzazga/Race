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
            
            for (double angle = Math.PI; angle > -Math.PI; angle -= 2 * Math.PI / numberOfPoints) {                
                float x  = center.x + radius * General.roundToFloat(Math.cos(angle));                        
                float y =  center.y + radius * General.roundToFloat(Math.sin(angle));
                
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