package presentation;

import javax.swing.JPanel;

import logic.Board;
import logic.IGraphicalOutput;
import logic.Track;
import logic.Vehicle;

/*
uses OpenGL
 */
public class Draw3d implements IGraphicalOutput {

    private final JPanel drawablePanel;

    public Draw3d(JPanel drawablePanel) {
        this.drawablePanel = drawablePanel;
        throw new RuntimeException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPanelToDrawOn(JPanel drawablePanel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
