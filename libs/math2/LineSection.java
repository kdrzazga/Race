package libs.math2;

import java.awt.Point;

   public class LineSection {

        public PointAG p1;
        public PointAG p2;

        public LineSection(float x1, float y1, float x2, float y2) {
            this.p1 = new PointAG(x1, y1);
            this.p2 = new PointAG(x2, y2);
        }
        
        public LineSection(Point p1, Point p2)
        {
            this.p1 = new PointAG(p1);
            this.p2 = new PointAG(p2);
        }

        public LineSection(PointAG p1, PointAG p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double getLength() {
            return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p1.x - p2.x), 2));
        }

        public PointAG getCenter() {
            float centerX = (p1.x + p2.x) / 2;
            float centerY = (p1.y + p2.y) / 2;
            return new PointAG(centerX, centerY);
        }
        
        @Override
        public String toString(){
            return "(" + this.p1.x + ", " + this.p1.y 
                    + "), (" + this.p2.x +", " + this.p2.y +")";
        }
    }