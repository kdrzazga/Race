package libs.graphics3d;

import libs.math2.Point3d;

public abstract class BaseEntity3d {

    public float height;
    protected Point3d center;

    public BaseEntity3d() {
    }

    public abstract void setCenter(float x, float y, float z);

    public abstract void setCenter(Point3d point);

    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public abstract void draw();

    public abstract Point3d getCenter();

}
