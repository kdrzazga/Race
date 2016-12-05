package logic.test;

import java.awt.Point;
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
        this.board = Board.getInstance();
        this.track = Track.getInstance();
        this.vehicle = new Vehicle();

        v = new VelocityVector(2, Math.PI / 2);
        v.position = new Point(75, 75);
        this.vehicle.v = v;

        track = Track.create_50_50__350_250_RectangularTrack();
        board.track = track;
        board.vehicles.add(vehicle);
    }

    public void moveVehicleTest(double angle, Point inputPos, Point expectedPos) {
        VelocityVector v2 = new VelocityVector(2, angle);
        v2.position = inputPos;
        Vehicle veh2 = new Vehicle();
        veh2.v = v2;
        board.vehicles.clear();
        board.vehicles.add(veh2);
        board.moveVehicle(0);

        assert veh2.v.position.x == expectedPos.x;
        assert veh2.v.position.y == expectedPos.y;
    }

    public void fullRotation() {
        board.vehicles.get(0).v.value = 15;

        for (double angle = 0; angle < 2 * Math.PI; angle += 0.01) {
            board.vehicles.get(0).v.position = new Point(75, 75);
            board.vehicles.get(0).v.angle = angle;
            board.moveVehicle(0);
            System.out.println(board.vehicles.get(0).v.position.x + " " + board.vehicles.get(0).v.position.y);
        }
    }

    public void zigZagDrive() {
        //change velocity vector
        for (int i = 0; i < 3; i++) {
            board.vehicles.get(0).v.angle = Math.PI / 7;
            board.vehicles.get(0).v.value = 3;
            moveVehicleTest2();
            System.out.println("--------");
            board.vehicles.get(0).v.angle = -Math.PI / 7;
            moveVehicleTest2();
            System.out.println("+++++++");
        }
    }

    private void moveVehicleTest2() {

        int ITERATIONS = 31;

        for (int i = 0; i < ITERATIONS; i++) {

            board.moveVehicle(0);
            System.out.println("Vehicle at" + v.position.x + ", " + v.position.y + " is on the track " + track.pointWithinTrack(v.position));
        }
    }

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        //test.moveVehicleTest2();
        //test.zigZagDrive();
        test.fullRotation();

        /*test.moveVehicleTest(0.0, new Point(60, 60), new Point(60, 62));        
        test.moveVehicleTest(Math.PI, new Point(63, 63), new Point(63, 61));
        test.moveVehicleTest(3*Math.PI/2, new Point(70, 60), new Point(70, 62));
        test.moveVehicleTest(3*Math.PI/4, new Point(70, 60), new Point(68, 61));*/
    }

}
