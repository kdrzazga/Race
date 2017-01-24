package libs.math2;

import java.awt.Point;

public class PointAG {

    public float x;    
    public float y;
    
    public PointAG(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public PointAG(Point p)
    {
        this.x = p.x;
        this.y = p.y;
    }
    
    public Point convertToPoint()
    {   
        return new Point(Math.round(this.x), Math.round(this.y));
    }

    public double distanceToAntherPointAG(PointAG anotherPoint)
    {
        return Math.sqrt((this.x - anotherPoint.x) * (this.x - anotherPoint.x) 
            + (this.y - anotherPoint.y) * (this.y - anotherPoint.y));
    }
    
    @Override
    public String toString()
    {
        return "(" + this.x + " ," + this.y + ")";
    }
}
