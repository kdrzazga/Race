package logic.test;

import static libs.Assert.assertion;
import static libs.Math2Assert.assertLineSectionsEqualNoMatterPointsOrder;
import libs.math2.LineSection;
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

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        test.givenVelocityVector_WhenVehicleMovedZigZagRoute_ThenVehiclePositionsEqualExpected();
        test.givenTrack_ShouldStartLineEqualExpected();

        for (int vehiclesCount = 0; vehiclesCount < 10; vehiclesCount++) {
            test.givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(vehiclesCount);
        }

        test.givenRestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(10);
        //test.halfRotation(); -> TODO: to be moved to graphical tsts
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

    public void givenVelocityVector_WhenVehicleMovedZigZagRoute_ThenVehiclePositionsEqualExpected() {
        board.vehicles.get(0).v.value = 3;
        VelocityVector v = board.vehicles.get(0).v;

        v.position = new PointAG(75f, 75f);

        PointAG expectedPositions[][] = {
            {new PointAG(123.65405f, 98.43054f), new PointAG(207.44714f, 58.077923f)},
            {new PointAG(291.24023f, 98.43054f), new PointAG(348.0033f, 71.09491f)},
            {new PointAG(348.0033f, 71.09491f), new PointAG(348.0033f, 71.09491f)}
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

    public void givenTrack_ShouldStartLineEqualExpected() {
        Track track = Mocks.create_0_0__30_30_TestRectangularTrack();

        LineSection actualStartLine = track.getRaceStartLine();
        LineSection expectedStartLine1 = new LineSection(15, 0, 15, 10);

        assertLineSectionsEqualNoMatterPointsOrder(actualStartLine, expectedStartLine1);
        System.out.println("givenTrackTestStartLine1 passed");
    }

    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(int numberOfVehicles) {
        String methodName = "givenTrackTestStartPositions";

        StringBuilder testResultMessage = new StringBuilder();
        Track rectTrack = Mocks.create_0_0__30_30_TestRectangularTrack();

        LineSection startLine = rectTrack.getRaceStartLine();

        float startLineX = startLine.p1.x;

        Board testBoard = new Board(numberOfVehicles, rectTrack);

        for (Vehicle vehicle : testBoard.vehicles) {
            assertion(vehicle.v.position.x, startLineX, methodName);
            testResultMessage.append(" ").append(vehicle.v.position).append(" ");

        }

        testResultMessage.append(methodName).append(" passed for ");
        System.out.println(testResultMessage);

    }

    public void givenRestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(int numberOfVehicles) {
        String methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther";

        StringBuilder testResultMessage = new StringBuilder();
        testResultMessage.append(methodName).append(" passed for ");
        Track rectTrack = Mocks.create_50_50__350_250_RectangularTrack();
        Board testBoard = new Board(numberOfVehicles, rectTrack);

        double distanceetweenVehicle1And2
                = this.getLineConnectingVehicles(testBoard.vehicles.get(0), testBoard.vehicles.get(1)).getLength();

        for (int i = 2; i < testBoard.vehicles.size() - 1; i++) {
            double distanceBetweenNextVehicles
                    = getLineConnectingVehicles(testBoard.vehicles.get(i), testBoard.vehicles.get(i + 1)).getLength();

            assertion(distanceetweenVehicle1And2, distanceBetweenNextVehicles, methodName);
            testResultMessage.append(testBoard.vehicles.get(i).v.position).append(" ");
        }

        System.out.println(testResultMessage);
    }

    private LineSection getLineConnectingVehicles(Vehicle vehicle1, Vehicle vehicle2) {
        PointAG vehicle1Position = vehicle1.v.position;
        PointAG vehicle2Position = vehicle2.v.position;

        return new LineSection(vehicle1Position, vehicle2Position);
    }

    private void moveVehicle31times() {

        int ITERATIONS = 31;

        for (int i = 0; i < ITERATIONS; i++) {

            board.moveVehicle(0);

            //all the time vehicle should be on the track
            assertion(track.pointWithinTrack(board.getVehiclePosition(0)), "moveVehicle31times");
        }
    }

    private void updateVehicleTravelledWayAngleTest() {
        throw new RuntimeException("Not implemented yet");
    }

    private void givenVelocityVector_ShouldVehicleMoveToSpecifiedPosition(double angle, PointAG inputPos, PointAG expectedPos) {
        VelocityVector v2 = new VelocityVector(2, angle, inputPos);
        Vehicle veh2 = new Vehicle(1);
        veh2.v = v2;
        board.vehicles.clear();
        board.vehicles.add(veh2);
        board.moveVehicle(0);

        assertion(veh2.v.position.x == expectedPos.x, "moveVehicleTest");
        assertion(veh2.v.position.y == expectedPos.y, "moveVehicleTest");
    }

}
