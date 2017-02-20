package logic.test;

import java.util.ArrayList;
import logic.Board;
import logic.Vehicle;

public class BoardWrapper extends Board {

    public TrackWrapper mockTrack;
    
    public BoardWrapper(Board board)
    {
        this.track = board.track.clone();
        this.vehicles = (ArrayList<Vehicle>) board.vehicles.clone();
        this.mockTrack = new TrackWrapper(track);        
    }

    public BoardWrapper(int numberOfVehicles, TrackWrapper mockTrack) {

        init(mockTrack);
        super.initVehicles(numberOfVehicles);
    }

    private void init(TrackWrapper mockTrack) {
        this.mockTrack = mockTrack;
        this.vehicles = new ArrayList<>();
    }
}
