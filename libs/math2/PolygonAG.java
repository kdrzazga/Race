package libs.math2;

import java.awt.Point;
import java.awt.Polygon;

public class PolygonAG extends Polygon{
    //AG stands for Analitycal Geometry
    
    public Point getPoint(int index)
    {
        return new Point(this.xpoints[index], this.ypoints[index]);
    }
}
