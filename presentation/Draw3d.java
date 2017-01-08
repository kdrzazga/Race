package presentation;

import javax.swing.JPanel;
import logic.Board;
import logic.IGraphicalOutput;
import logic.Track;
import logic.Vehicle;

/*
uses OpenGL
*/
public final class Draw3d implements IGraphicalOutput{

    private JPanel drawablePanel;
    
    public Draw3d(JPanel drawablaPanel)
    {
        this.setPanelToDrawOn(drawablePanel);
    }

    @Override
    public void drawBoard(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawTrack(Track track) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
       this.drawablePanel = drawablePanel;
    }

}
