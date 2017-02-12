package logic.test;

import static libs.Assert.assertion;
import static libs.Math2Assert.assertLineSectionsEqualNoMatterPointsOrder;
import static libs.UnitTest.showTestPassedMessage;
import libs.math2.Numbers;
import libs.math2.LineSection;
import libs.math2.PointAG;
import logic.Board;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;
import logic.BoardBuilder;

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

        track = BoardBuilder.createTrack(BoardBuilder.TrackType.RECTANGULAR);
        board.track = track;
        board.vehicles.add(vehicle);
    }

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        test.testStartLine();
        test.givenRestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(10);

        for (int vehiclesCount = 0; vehiclesCount < 10; vehiclesCount++) {
            test.givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(vehiclesCount);
        }

        test.testIncreasingLaps();
        showTestPassedMessage(BoardTrackVehicleTest.class.getName());
    }

    public void testStartLine() {
        Track rectTrack;
        LineSection actualStartLine;
        LineSection expectedStartLine1;
        
        GIVEN:
        rectTrack = BoardBuilder.createTrack(BoardBuilder.TrackType.TEST_RECTANGULAR);
        expectedStartLine1 = new LineSection(15, 0, 15, 10);
        
        WHEN:
        actualStartLine = rectTrack.computeVerticalStartLine();
        
        THEN:
        assertLineSectionsEqualNoMatterPointsOrder(actualStartLine, expectedStartLine1);
        System.out.println("givenTrack_ShouldStartLineEqualExpected passed");
    }

    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(int numberOfVehicles) {
        String methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1";

        StringBuilder testResultMessage = new StringBuilder();
        Track rectTrack = BoardBuilder.createTrack(BoardBuilder.TrackType.TEST_RECTANGULAR);

        LineSection startLine = rectTrack.computeVerticalStartLine();
        float startLineX = startLine.p1.x;
        Board testBoard = new Board(numberOfVehicles, rectTrack);

        for (Vehicle veh : testBoard.vehicles) {
            assertion(veh.v.position.x, startLineX, methodName);
            testResultMessage.append(" ").append(veh.v.position).append(" ");
        }

        testResultMessage.append(methodName).append(" passed for ");
        System.out.println(testResultMessage);
    }

    public void givenRestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(int numberOfVehicles) {
        String methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther";

        StringBuilder testResultMessage = new StringBuilder();
        testResultMessage.append(methodName).append(" passed for ");

        Board testBoard = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.DONUT);

        PointAG vehicle0Position = testBoard.vehicles.get(0).v.position;
        PointAG vehicle1Position = testBoard.vehicles.get(1).v.position;

        float distanceBetweenVehicle0And1 = Numbers.roundToFloat(vehicle0Position.distanceToAntherPointAG(vehicle1Position));

        for (int i = 2; i < testBoard.vehicles.size() - 1; i++) {
            PointAG vehiclePosition = testBoard.vehicles.get(i).v.position;
            PointAG nextVehiclePosition = testBoard.vehicles.get(i + 1).v.position;
            float distanceBetweenNextVehicles = Numbers.roundToFloat(vehiclePosition.distanceToAntherPointAG(nextVehiclePosition));

            assertion(distanceBetweenVehicle0And1, distanceBetweenNextVehicles, methodName);
            testResultMessage.append(testBoard.vehicles.get(i).v.position).append(" ");
        }

        System.out.println(testResultMessage);
    }

    private Vehicle moveVehicleNTimes(Board board, int N, int vehicleId) {

        for (int i = 0; i < N; i++) {
            board.moveVehicle(vehicleId);
            //all the time vehicle should be on the track
            assertion(board.track.isInsideTrack(board.getVehiclePosition(vehicleId))
                , "moveVehicle31times - design your test in the way the vehicle will be in track all the time "
            + " current pos=" + board.vehicles.get(vehicleId).v.position);
        }
        return board.vehicles.get(vehicleId);
    }

    public void testIncreasingLaps() {
        Board rectBoard;
        Vehicle veh;
        final int vehicleId = 0;
        int lapsBeforeMove;    
        
        GIVEN_WERONIKA_TRACK_WITH_1_VEHICLE_SPEED_1:
        {
            rectBoard = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.WERONIKA);
            veh = rectBoard.vehicles.get(vehicleId);
            veh.v.value = 1;
            lapsBeforeMove = veh.laps;
        }
        WHEN_VEHICLE_TRAVELLED_SQUARE_DISTANCE:
        {
            veh = moveVehicleNTimes(rectBoard, 31, vehicleId);//go right
            turnVehicleRight90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 250, vehicleId);//go down
            turnVehicleRight90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 62, vehicleId);//go left
            turnVehicleRight90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 250, vehicleId);//go up
            turnVehicleRight90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 31, vehicleId);//go right to starting position     
        }
        THEN_LAP_WAS_INCREASED:
        {
            assertion(veh.laps, lapsBeforeMove + 1, "updateVehicleTravelledWayAngleTest");
        }        
    }

    private void turnVehicleRight90deg(Vehicle vehicle1) {
        float iteration = 0;
        float iterationsToDo90degTurn = Numbers.roundToFloat(Math.PI/ 2/ VelocityVector.ROTATION_UNIT);
        while (iteration < iterationsToDo90degTurn) {
            vehicle1.turnRight();
            iteration++;
        }
    }
}
