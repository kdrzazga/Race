package logic.test;

import static libs.Assert.assertion;
import static libs.Math2Assert.assertLineSectionsEqualNoMatterPointsOrder;
import static libs.UnitTest.showTestPassedMessage;
import libs.math2.General;
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

        track = BoardBuilder.createTrack(BoardBuilder.TrackType.RECTANGULAR_1);
        board.track = track;
        board.vehicles.add(vehicle);
    }

    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();   
        test.givenTrack_ShouldStartLineEqualExpected();
        test.givenRestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(10);        

        for (int vehiclesCount = 0; vehiclesCount < 10; vehiclesCount++) {
            test.givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(vehiclesCount);
        }
        
        //test.updateVehicleTravelledWayAngleTest();
        showTestPassedMessage(BoardTrackVehicleTest.class.getName());
    }

    public void givenTrack_ShouldStartLineEqualExpected() {
        Track rectTrack = BoardBuilder.createTrack(BoardBuilder.TrackType.TEST_RECTANGULAR);

        LineSection actualStartLine = rectTrack.computeVerticalStartLine();
        LineSection expectedStartLine1 = new LineSection(15, 0, 15, 10);

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
                
        float distanceBetweenVehicle0And1 = General.roundToFloat(vehicle0Position.distanceToAntherPointAG(vehicle1Position));

        for (int i = 2; i < testBoard.vehicles.size() - 1; i++) {
            PointAG vehiclePosition = testBoard.vehicles.get(i).v.position;
            PointAG nextVehiclePosition = testBoard.vehicles.get(i + 1).v.position;
            float distanceBetweenNextVehicles = General.roundToFloat(vehiclePosition.distanceToAntherPointAG(nextVehiclePosition));

            assertion(distanceBetweenVehicle0And1, distanceBetweenNextVehicles, methodName);
            testResultMessage.append(testBoard.vehicles.get(i).v.position).append(" ");
        }

        System.out.println(testResultMessage);
    }

    private Vehicle moveVehicle31times(Board board) {
        final int ITERATIONS = 31;

        for (int i = 0; i < ITERATIONS; i++) {
            board.moveVehicle(0);
            //all the time vehicle should be on the track
            assertion(track.isInsideTrack(board.getVehiclePosition(0)), "moveVehicle31times");
        }
        return board.vehicles.get(0);
    }

    public void updateVehicleTravelledWayAngleTest() {  
        Board rectBoard;
        Vehicle vehicleBeforeMove;
        Vehicle vehicleAfterMove;
        
        GIVEN_RECTANGULAR_TRACK_WITH_1_VEHICLE_SPEED_1:
        {
            rectBoard = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.RECTANGULAR_1);
            rectBoard.vehicles.get(0).v.value = 1;
            vehicleBeforeMove = rectBoard.vehicles.get(0).clone();
        }
        WHEN_VEHICLE_MOVED_31_TIMES:
        {
            vehicleAfterMove = moveVehicle31times(rectBoard);
        }
        THEN_TRAVELLED_WAY_INCREASED:
        {
            assertion(vehicleBeforeMove.travelledWayAngle, 0.0, "updateVehicleTravelledWayAngleTest");
            assertion(vehicleAfterMove.travelledWayAngle > 0, "updateVehicleTravelledWayAngleTest");            
        }
        GIVEN:
        {
            vehicleBeforeMove = vehicleAfterMove.clone();
        }
        WHEN_VEHICLE_MOVED_62_TIMES_MORE:
        {
            vehicleAfterMove = moveVehicle31times(rectBoard);
        }
        THEN:
        {
            throw new RuntimeException("Implementeation not finished");
        }
    }
}
