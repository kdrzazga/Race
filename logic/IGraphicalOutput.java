package logic;

import javax.swing.JPanel;

public interface IGraphicalOutput {

    public void drawBoard(Board board);
    
    public void drawTrack(Track track);    
    
    public void drawVehicle(Vehicle vehicle);
    
    public void setPanelToDrawOn(JPanel drawablePanel);
    
}
