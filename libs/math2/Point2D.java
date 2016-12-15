package libs.math2;

import java.awt.Point;

public class Point2D {

    public float x;    
    public float y;
    
    public Point2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point truncToPoint()
    {
        int x = Math.round(this.x);
        int y = Math.round(this.y);
        
        return new Point(x, y);
    }
}
