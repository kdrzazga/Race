package logic;

import javax.swing.JPanel;

public interface IGraphicalOutput {

    public void draw(Board board);

    public void draw(Track track);

    public void drawTrackBorder(Track track);

    public void drawStartLine(Track track);

    public void draw(Vehicle vehicle);

    public void drawAllVehicles(Board board);

    public void setPanelToDrawOn(JPanel drawablePanel);

    public void clearOutput();

    public void eraseVehicles(Board track);
}
