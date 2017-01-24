package libs.math2;

import java.awt.Point;

public class PointAG {

    public float x;
    public float y;

    public PointAG(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PointAG(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public PointAG(PointAG p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point convertToPoint() {
        return new Point(Math.round(this.x), Math.round(this.y));
    }

    public double distanceToAntherPointAG(PointAG anotherPoint) {
        return Math.sqrt((this.x - anotherPoint.x) * (this.x - anotherPoint.x)
                + (this.y - anotherPoint.y) * (this.y - anotherPoint.y));
    }

    public PointAG createPointByMovingdByVector(LineSection vector) {
        PointAG movedPoint = new PointAG(this);
        movedPoint.moveByVector(vector);
        return movedPoint;
    }

    public PointAG createPointByMovingdByVector(float length, float angle) {
        PointAG movedPoint = new PointAG(this);
        movedPoint.moveByVector(length, angle);
        return movedPoint;
    }

    public void moveByVector(double length, double angle) {
        this.x += General.roundToFloat(length * Math.sin(angle));
        this.y += General.roundToFloat(length * Math.cos(angle));
    }

    public void moveByVector(LineSection vector) {
        this.moveByVector(vector.computeLength(), vector.computeInclinationAngle());
    }

    @Override
    public String toString() {
        return "(" + this.x + " ," + this.y + ")";
    }
}
