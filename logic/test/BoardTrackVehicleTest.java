package logic.test;

import static libs.Assert.assertion;
import libs.math2.PointAG;
import logic.Board;
import logic.Mocks;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;

public class BoardTrackVehicleTest {

    Board board;
    Track track;
    Vehicle vehicle;
    VelocityVector v;

    public BoardTrackVehicleTest() {
        this.board = new Board();
        this.track = new Track();
        this.vehicle = new Vehicle(0);

        v = new VelocityVector(2, Math.PI / 2, new PointAG(75, 75));
        
        this.vehicle.v = v;

        track = Mocks.create_50_50__350_250_RectangularTrack();
        board.track = track;
        board.vehicles.add(vehicle);
    }

    public void moveVehicleTest(double angle, PointAG inputPos, PointAG expectedPos) {
        VelocityVector v2 = new VelocityVector(2, angle, inputPos);
        Vehicle veh2 = new Vehicle(1);
        veh2.v = v2;
        board.vehicles.clear();
        board.vehicles.add(veh2);
        board.moveVehicle(0);

        assertion (veh2.v.position.x == expectedPos.x, "moveVehicleTest");
        assertion (veh2.v.position.y == expectedPos.y, "moveVehicleTest");
    }

    public void halfRotation() {
        board.vehicles.get(0).v.value = 15;

        for (double angle = 0; angle < Math.PI; angle += 0.01) {
            board.vehicles.get(0).v.position = new PointAG(75, 75);
            board.vehicles.get(0).v.angle = angle;
            board.moveVehicle(0);
            System.out.println(board.vehicles.get(0).v.position.x + "," + board.vehicles.get(0).v.position.y);
        }
    }

    public void zigZagDrive() {
        board.vehicles.get(0).v.value = 3;
        VelocityVector v = board.vehicles.get(0).v;
        
        v.position = new PointAG(75f, 75f);
                
        PointAG expectedPositions[][] = {
            {new PointAG(123.65405f, 98.43054f), new PointAG(207.44714f, 58.077923f)}
            ,{new PointAG(291.24023f, 98.43054f), new PointAG(348.0033f, 71.09491f) }
            ,{new PointAG(348.0033f, 71.09491f), new PointAG(348.0033f, 71.09491f)}
        };
                
        //change velocity vector so that the vehicle travles a zig zag route
        
        for (int i = 0; i < 3; i++) {
            v.angle = Math.PI / 7;
            
            moveVehicle31times();
            assertion(board.getVehiclePosition(0).x == expectedPositions[i][0].x, "zigZagDrive index=" + i + "0 ");
            assertion(board.getVehiclePosition(0).y == expectedPositions[i][0].y, "zigZagDrive index=" + i + "0 ");
            
            v.angle = -Math.PI / 7;
            moveVehicle31times();            
            assertion(board.getVehiclePosition(0).x == expectedPositions[i][1].x, "zigZagDrive index=" + i + "1 ");
            assertion(board.getVehiclePosition(0).y == expectedPositions[i][1].y, "zigZagDrive index=" + i + "1 ");
        }
        
        System.out.println("No assertionion returned exception - zigZagDrive test passed");
    }

    private void moveVehicle31times() {

        int ITERATIONS = 31;

        for (int i = 0; i < ITERATIONS; i++) {

            board.moveVehicle(0);
            
            //all the time vehicle should be on the track
            assertion (track.pointWithinTrack(board.getVehiclePosition(0)), "moveVehicle31times");
        }
    }
    
    private void updateVehicleTravelledWayAngleTest()
    {
        throw new RuntimeException("Not implemented yet");
    }

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        test.zigZagDrive();
        givenCIRCULAR_1TrackAllVehiclesOnStartLineShouldHaveSameYCoordinate();
        //test.halfRotation(); -> TODO: to be moved to graphical tsts

    }

    
    public static void givenCIRCULAR_1TrackAllVehiclesOnStartLineShouldHaveSameYCoordinate()
    {
        int numberOfVehicles = 7;
        Board mockBoard = Mocks.createBoardWithNVehiclesOnTrack(numberOfVehicles, Mocks.TrackType.CIRCULAR_1);
        
        //
        for (Vehicle vehicle : mockBoard.vehicles)
        {
            assertion(vehicle.v.position.y == mockBoard.track.getRaceStartLine().p1.y, "testGetStartPosition()");
            assertion(vehicle.v.position.y == mockBoard.track.getRaceStartLine().p2.y, "testGetStartPosition()");
        }
        
    }
    
}
