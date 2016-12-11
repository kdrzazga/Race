package presentation;

import logic.Board;
import logic.Track;

public interface IGraphicalOutput {

    public void drawBoard(Board board);
    
    public void drawTrack(Track track);        
}
