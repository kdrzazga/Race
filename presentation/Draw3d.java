package presentation;

import javax.swing.JPanel;
import libs.math2.Point3d;
import logic.Board;
import logic.IGraphicalOutput;
import logic.Track;
import logic.Vehicle;

import static org.lwjgl.opengl.GL11.*;
import static presentation.ColorSettings.glColor;

/*
uses OpenGL
 */
public final class Draw3d implements IGraphicalOutput {

    private JPanel drawablePanel;

    private final static float VEHICLE_WIDTH = 40f;
    private final static float VEHICLE_LENGTH = 50f;
    private final static float VEHICLE_HEIGHT = 20f;

    private final Point3d vehicleSide1[] = {
        new Point3d(0, 0, 20), new Point3d((float) (0.4 * VEHICLE_LENGTH), 0, VEHICLE_HEIGHT / 2),
        new Point3d((float) (0.6 * VEHICLE_LENGTH), 0, VEHICLE_HEIGHT / 2), new Point3d(35, 0, 0),
        new Point3d((float) (0.9 * VEHICLE_LENGTH), 0, 0), new Point3d(VEHICLE_LENGTH, 0, VEHICLE_HEIGHT / 2),
        new Point3d(VEHICLE_LENGTH, 0, VEHICLE_HEIGHT)
    };

    private final Point3d vehicleSide2[] = new Point3d[vehicleSide1.length];//initialized in initVehicleOutline()

    public Draw3d(JPanel drawablaPanel) {
        this.setPanelToDrawOn(drawablePanel);
        initVehicleOutline();
    }

    @Override
    public void draw(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Track track) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Vehicle vehicle) {
        setVehicleCenter(new Point3d(vehicle.v.position, VEHICLE_HEIGHT));
        glColor(ColorSettings.getVehicleColorById(vehicle.getId()));
        drawSide(vehicleSide1);
        drawSide(vehicleSide2);
        //throw new RuntimeException("Implementation not finished.");
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
    }

    private void drawSide(Point3d points[]) {
        
        glBegin(GL_POLYGON);
        for (Point3d vehiclePoint : points) {
            glVertex3f(vehiclePoint.x, vehiclePoint.y, vehiclePoint.z);
        }
        glEnd();
    }

    private void setVehicleCenter(Point3d point) {
        for (Point3d side1Point : this.vehicleSide1) {
            side1Point.move(point);
        }

        for (Point3d side2Point : this.vehicleSide2) {
            side2Point.move(point);
        }
    }

    private void initVehicleOutline() {
        int i = 0;
        for (Point3d vehicleSide1Point : this.vehicleSide1) {
            float x = vehicleSide1Point.x;
            float z = vehicleSide1Point.z;
            this.vehicleSide2[i] = new Point3d(x, VEHICLE_WIDTH, z);
            i++;
        }
    }
}
