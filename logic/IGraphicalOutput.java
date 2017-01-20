package logic;

import javax.swing.JPanel;

public interface IGraphicalOutput {

    public void draw(Board board);
    
    public void draw(Track track);    
    
    public void draw(Vehicle vehicle);
    
    public void setPanelToDrawOn(JPanel drawablePanel);
    
    public void clearOutput();
}
