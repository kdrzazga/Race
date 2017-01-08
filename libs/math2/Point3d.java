package libs.math2;

public class Point3d{

    public float x;
    public float y;
    public float z;
    
    public Point3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void move(float move_x, float move_y, float move_z)
    {
        this.x += move_x;
        this.y += move_y;
        this.z += move_z;
    }

}
