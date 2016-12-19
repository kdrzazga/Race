package logic.test;

import java.awt.Point;
import static libs.Assert.assertion;
import libs.math2.PointAG;
import logic.Board;
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

        v = new VelocityVector(2, Math.PI / 2);
        v.position = new PointAG(75, 75);
        this.vehicle.v = v;

        track = Track.create_50_50__350_250_RectangularTrack();
        board.track = track;
        board.vehicles.add(vehicle);
    }

    public void moveVehicleTest(double angle, PointAG inputPos, PointAG expectedPos) {
        VelocityVector v2 = new VelocityVector(2, angle);
        v2.position = inputPos;
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
        VelocityVector v = board.vehicles.get(0).v;
        
        v.position = new PointAG(75f, 75f);
        
        
        
        PointAG expectedPositions[][] = new PointAG[3][2];

        expectedPositions[0][0] = new PointAG(123.65405f, 98.43054f);
        expectedPositions[0][1] = new PointAG(123.65405f, 98.43054f);
        expectedPositions[1][0] = new PointAG(233, 82);
        expectedPositions[1][1] = new PointAG(123.65405f, 98.43054f);
        expectedPositions[2][0] = new PointAG(327, 81);
        expectedPositions[2][1] = new PointAG(123.65405f, 98.43054f);
        
        board.vehicles.get(0).v.value = 3;
        //change velocity vector so that the vehicle travles a zig zag route
        
        for (int i = 0; i < 3; i++) {
            v.angle = Math.PI / 7;
            
            moveVehicle31times();
            
            //assertion(board.getVehiclePosition(0).x == expectedPositions[i].x, "zigZagDrive");
            //assertion(board.getVehiclePosition(0).y == expectedPositions[i].y, "zigZagDrive");
            
            board.vehicles.get(0).v.angle = -Math.PI / 7;
            moveVehicle31times();
            
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
        
    }

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        test.zigZagDrive();
        //test.halfRotation(); -> TODO: to be moved to graphical tsts

    }

}
