package presentation.test;

import javax.swing.JPanel;
import logic.Vehicle;
import miscallenous.Mocks;
import presentation.Draw3d;

public class Draw3dTest {

    private static void givenVehicle_ShouldDraw3dCreateProper3dModel()
    {
        JPanel stubPanel = new JPanel();
        Vehicle vehicle = Mocks.createVehicleAtPosition(2001, 100, 100);
        Draw3d draw3d = new Draw3d(stubPanel);
        
        throw new RuntimeException("Not implemented yet. Dont' actually know " 
                + "how to get into this. Too many private member to test like this.");
        //draw3d.draw(vehicle); this require OpenGLInit
    }
    
    public static void main(String[] arguments)
    {
        givenVehicle_ShouldDraw3dCreateProper3dModel();
        System.out.println("givenVehicle_ShouldDraw3dCreateProper3dModel passed");
    }
}
