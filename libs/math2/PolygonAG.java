package libs.math2;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class PolygonAG{
    //AG stands for Analitycal Geometry
    
    public ArrayList<PointAG> points;
    
    public PolygonAG(){
        this.points = new ArrayList<>();
    }
    
    public void addPoint(int x, int y)
    {
        this.points.add(new PointAG(x, y));
    }
        
    public void addPoint2D(float x, float y)
    {
        this.points.add(new PointAG(x, y));
    }
    
    public LineSection getLineSectionCrossingVerticalLine(LineAG line)
    {
        if (!line.vertical)
            throw new RuntimeException("Wrong argument - line not vertical. " + libs.math2.PolygonAG.class.getName());
        
        int i;
        
        for(i = 0; i < this.points.size() - 1; i++)
        {
            if (this.points.get(i).x < line.verticalX && this.points.get(i+1).x > line.verticalX)
                break;
        }
        
        return new LineSection(this.points.get(i), this.points.get(i + 1));
          
    }
    
    public Polygon convertToPolygon()
    {
        Polygon result = new Polygon();
        
        for (int i = 0; i < this.points.size(); i++)
        {
            Point p = this.points.get(i).convertToPoint();
            result.addPoint(p.x, p.y);
        }
        
        return result;
    }
}
