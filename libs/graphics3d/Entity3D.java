package libs.graphics3d;

import libs.math2.Point3d;

public interface Entity3D {

    public float getX();

    public float getY();

    public float getZ();

    public void setX(float x);

    public void setY(float y);

    public void setZ(float z);

    public void setLocation(float x, float y, float z);
    
    public void setLocation(Point3d point);

    public void setUp();

    public void destroy();

    public void draw();
    
    public void move(Point3d vector);
}
