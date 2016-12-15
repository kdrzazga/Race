package libs.math2;

import java.awt.Point;

   public class LineSection {

        Point p1;
        Point p2;

        public LineSection(int x1, int y1, int x2, int y2) {
            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);
        }

        public LineSection(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double getLength() {
            return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p1.x - p2.x), 2));
        }

        public Point getCenter() {
            int centerX = Math.round((p1.x + p2.x) / 2);
            int centerY = Math.round((p1.y + p2.y) / 2);
            return new Point(centerX, centerY);
        }
    }