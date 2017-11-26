package logic;

import javax.swing.*;

public interface IGraphicalOutput {

    void draw(Board board);

    void draw(Track track);

    void drawTrackBorder(Track track);

    void drawStartLine(Track track);

    void draw(Vehicle vehicle);

    void drawAllVehicles(Board board);

    void setPanelToDrawOn(JPanel drawablePanel);

    void clearOutput();

    void eraseVehicles(Board track);
}
