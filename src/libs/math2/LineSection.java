package libs.math2;

import java.awt.Point;

public class LineSection extends LineAG{

    public PointAG p1;
    public PointAG p2;

    public LineSection(float x1, float y1, float x2, float y2) {
        super(new PointAG(x1, y1), new PointAG(x2, y2));
        this.p1 = new PointAG(x1, y1);
        this.p2 = new PointAG(x2, y2);
    }

    public LineSection(Point p1, Point p2) {
        super(new PointAG(p1), new PointAG(p2));
        this.p1 = new PointAG(p1);
        this.p2 = new PointAG(p2);
    }

    public LineSection(PointAG p1, PointAG p2) {
        super(p1, p2);
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public LineSection(PointAG point, float angle, float length)
    {
        super(point, angle);      
        this.p1 = point;
        this.p2 = point.clone();
        p2.moveByVector(length, angle);
    }

    @Override
    public LineSection clone() {        
        return new LineSection(this.p1.clone(), this.p2.clone());
    }

    public double computeLength() {
        return Math.sqrt(Math.pow((p1.y - p2.y), 2) + Math.pow((p1.x - p2.x), 2));
    }

    public PointAG computeCenter() {
        float centerX = (p1.x + p2.x) / 2;
        float centerY = (p1.y + p2.y) / 2;
        return new PointAG(centerX, centerY);
    }
    
        /* positive angles:
                pi rad
                |
     3pi/2 rad--o---    pi/2 rad
                |\ pi/4 rad
                2pi rad = 0 rad     
     */
    
    public double computePositiveInclinationAngle()
    {
        return computeInclinationAngle() +Math.PI;
    }

    /* angles:
                0 deg
                |
        90 deg--o---    -90 deg
                |\ -135 deg
                180 deg       
     */
    public double computeInclinationAngle() {
        double ySpan = this.p1.y - this.p2.y;
        double xSpan = this.p1.x - this.p2.x;
        return Math.atan2(xSpan, ySpan);
    }

    public boolean xBelongsToLineSection(float x) {
        float xMin = Math.min(this.p1.x, this.p2.x);
        float xMax = Math.max(this.p1.x, this.p2.x);

        return (x >= xMin) && (x <= xMax);
    }

    public boolean yBelongsToLineSection(float y) {
        float yMin = Math.min(this.p1.y, this.p2.y);
        float yMax = Math.max(this.p1.y, this.p2.y);

        return (y >= yMin) && (y <= yMax);
    }

    @Override
    public float computeY(float x) {
        if (this.xBelongsToLineSection(x))
        {
            return super.computeY(x);
        }
        else
            throw new RuntimeException(x + " doesn't belong to line section");
    }
    
    public PointAG computeIntersection(LineSection lineSection2)
    {
        PointAG intersection = super.findIntersection(lineSection2);        
        return returnIntersectionIfInLineSection(intersection);
    }

    private PointAG returnIntersectionIfInLineSection(PointAG intersection) {
        if (intersection != null)
        {
            if (this.xBelongsToLineSection(intersection.x)
                    && this.yBelongsToLineSection(intersection.y))
                return intersection;
            else return null;
        }
        else
            return null;
    }
    
    public void moveP2MultiplyingBy(float scalar)
    {
        double length = this.computeLength();
        double inclination = this.computeInclinationAngle() + Math.PI;
        
        this.p2 = this.p1.clone();
        this.p2.moveByVector(length * scalar, inclination);
    }

    public LineSection createPararellSection(float distance)
    {
        LineAG perpLineP1 = this.computePerpendicularLine(this.p1);
        LineAG perpLineP2 = this.computePerpendicularLine(this.p2);
        
        PointAG parSectionP1 = perpLineP1.givenXMovePointAlongLine(this.p1.x, distance);
        PointAG parSectionP2 = perpLineP2.givenXMovePointAlongLine(this.p2.x, distance);
        
        return new LineSection(parSectionP1, parSectionP2);
    }
    
    @Override
    public String toString() {
        return "(" + this.p1.x + ", " + this.p1.y
                + "), (" + this.p2.x + ", " + this.p2.y + ")";
    }
}