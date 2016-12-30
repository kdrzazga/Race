package logic;

import libs.math2.PointAG;

public class VelocityVector {

    public final static int V_MAX = 5;
    public final static int V_MIN = 0;
    public final static float ROTATION_UNIT = (float) (Math.PI / 10);
    public final static int ACCELERATE_UNIT = 1;

    public VelocityVector(int value, double angle) {

        this.value = value;
        this.angle = angle;
    }

    public int value;
    public double angle;
    public PointAG position;

    public void turnLeft()
    {
        this.turn(-ROTATION_UNIT);
    }
    
    public void turnRight()
    {
        this.turn(ROTATION_UNIT);
    }
        
    public void accelerate()
    {
        this.accelerate(ACCELERATE_UNIT);
    }
    
    public void slowDown()
    {
        this.accelerate(-ACCELERATE_UNIT);
    }
    
    private void turn(double turnAngle) {
        this.angle = (this.angle + turnAngle) % (2 * Math.PI);
    }

    private void accelerate(int accelerateValue) {
        if (this.value + accelerateValue > V_MAX) {
            this.value = V_MAX; 
        } else if (this.value + accelerateValue < V_MIN) {
            this.value = V_MIN;
        } else {
            this.value += accelerateValue;
        }
    }
    
}
