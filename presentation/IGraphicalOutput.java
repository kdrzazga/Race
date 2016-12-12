package presentation;

import logic.Board;
import logic.Track;
import logic.Vehicle;

public interface IGraphicalOutput {

    public void drawBoard(Board board);
    
    public void drawTrack(Track track);    
    
    public void drawVehicle(Vehicle vehicle);
}
