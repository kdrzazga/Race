package logic.test;

import java.util.ArrayList;
import logic.Board;
import logic.Vehicle;

public class MockBoard extends Board {

    public MockTrack mockTrack;
    
    public MockBoard(Board board)
    {
        this.track = board.track.clone();
        this.vehicles = (ArrayList<Vehicle>) board.vehicles.clone();
        this.mockTrack = new MockTrack(track);        
    }

    public MockBoard(int numberOfVehicles, MockTrack mockTrack) {

        init(mockTrack);
        super.initVehicles(numberOfVehicles);
    }

    private void init(MockTrack mockTrack) {
        this.mockTrack = mockTrack;
        this.vehicles = new ArrayList<>();
    }
}
