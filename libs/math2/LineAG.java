package libs.math2;

import java.awt.geom.Line2D;

public class LineAG {

    //y = Ax + B
    public float A;
    public float B;
    public float verticalX;
    public boolean vertical;

    public LineAG(float A, float B) {
        this.A = A;
        this.B = B;
        vertical = false;
    }

    public LineAG(float verticalX) {
        this.verticalX = verticalX;
        this.vertical = true;
    }
    
    public LineAG(PointAG p1, PointAG p2)
    {
        if (p1.x == p2.x) {
            this.vertical = true;
            this.verticalX = p1.x;

        } else {
            this.vertical = false;
            this.A = (p2.y - p1.y) / (p2.x - p1.x);
            this.B = -this.A * p1.x + p1.y;
        }
    }
   
    public PointAG findIntersection(LineAG line) {
        float x, y;
        if (!this.vertical && !line.vertical) {
            if (this.A == line.A) {
                return null;
            }

            x = (line.B - this.B) / (this.A - line.A);
            y = this.A * x + this.B;

            
        } else if (this.vertical) {
            x = this.verticalX;
            y = line.A * this.verticalX + line.B;
        }
        else { //line.vertical = true
            x = line.verticalX;
            y = line.A * this.verticalX + line.B;
        }
        
        return new PointAG(x, y);
    }    
    
}
