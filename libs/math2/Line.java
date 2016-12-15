package libs.math2;

import java.awt.Point;

    public class Line {

        //y = Ax + B
        public float A;
        public float B;
        public int verticalX;
        public boolean isVertical;

        public Line(float A, float B) {
            this.A = A;
            this.B = B;
            isVertical = false;
        }

        public Line(int verticalX) {
            this.verticalX = verticalX;
            isVertical = true;
        }

        public Line(Point p1, Point p2) {
            if (p1.x == p2.x) {
                isVertical = true;
                this.verticalX = p1.x;

            } else {
                isVertical = false;
                this.A = (p2.y - p1.y) / (p2.x - p1.x);
                this.B = -this.A * p1.x + p1.y;
            }
        }

        public Point2D findIntersection(Line line)
        {
            if (this.A == line.A)
                return null;
            
            float x = (line.B - this.B) / (this.A - line.A);
            float y = this.A * x + this.B;
            
            return new Point2D(x, y);
        }
    }