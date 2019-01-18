package org.kd.libs.math2;

import java.awt.*;

public class PointAG implements Cloneable{

    private float x;
    private float y;

    public PointAG(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PointAG(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public PointAG clone() {
        return new PointAG(this.x, this.y);
    }

    public Point convertToPoint() {
        return new Point(Math.round(this.x), Math.round(this.y));
    }

    public double distanceToAntherPointAG(PointAG anotherPoint) {
        return Math.sqrt((this.x - anotherPoint.x) * (this.x - anotherPoint.x)
                + (this.y - anotherPoint.y) * (this.y - anotherPoint.y));
    }

    public PointAG createPointByMovingByVector(LineSection vector) {
        PointAG movedPoint = this.clone();
        movedPoint.moveByVector(vector);
        return movedPoint;
    }

    public PointAG createPointByMovingByVector(float length, float angle) {
        PointAG movedPoint = this.clone();
        movedPoint.moveByVector(length, angle);
        return movedPoint;
    }

    public void moveByVector(double length, double angle) {
        this.x += Numbers.roundToFloat(length * Math.sin(angle));
        this.y += Numbers.roundToFloat(length * Math.cos(angle));
    }

    public void moveByVector(LineSection vector) {
        this.moveByVector(LineSection.computeLength.apply(vector), vector.computeInclinationAngle());
    }

    public boolean equals(PointAG point) {
        return (this.x == point.x && this.y == this.y);
    }

    @Override
    public String toString() {
        return "(" + this.x + " ," + this.y + ")";
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
