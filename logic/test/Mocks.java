package logic.test;

import libs.math2.PointAG;
import logic.Board;
import logic.Track;
import logic.Vehicle;

public class Mocks {

    public static Vehicle createVehicle0At100_100()
    {
        Vehicle result = new Vehicle(0, 1);
        result.active = true;
        
        result.v.position = new PointAG(100,100);
             
        return result;
    }
    
        public static Vehicle createVehicle1At100_120()
    {
        Vehicle result = new Vehicle(1, 1);
        result.active = true;
        
        result.v.position = new PointAG(100,120);
             
        return result;
    }
        
    public static Board createBoardWith2VehiclesOnRectTrack()
    {
        Board result = new Board();
        
        result.track = Track.create_50_50__350_250_RectangularTrack();
        
        result.vehicles.add(createVehicle0At100_100());
        result.vehicles.add(createVehicle1At100_120());
        
        return result;
    }
}
