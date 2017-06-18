package unit_tests.logic_test;

import java.awt.Polygon;
import static libs.Math2Assert.assertLineSectionsEqualNoMatterPointsOrder;
import libs.UnitTest;
import libs.math2.Numbers;
import libs.math2.LineSection;
import libs.math2.PointAG;
import logic.Board;
import logic.BoardBuilder.TrackType;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;
import logic.BoardBuilder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class BoardTrackVehicleTest{

    Board board;
    Track rectTrack;
    Vehicle vehicle;
    VelocityVector v;

    public BoardTrackVehicleTest() {
        final BoardBuilder.TrackType track = BoardBuilder.TrackType.RECTANGULAR;
        this.board = new Board();
        this.rectTrack = new Track(track.toString());
        this.vehicle = new Vehicle(0);

        v = new VelocityVector(2, Math.PI / 2, new PointAG(75, 75));
        this.vehicle.v = v;

        this.rectTrack = BoardBuilder.createTrack(track);
        board.track = this.rectTrack;
        board.vehicles.add(vehicle);
    }
/*
    public static void main(String[] args) {
        BoardTrackVehicleTest test = new BoardTrackVehicleTest();
        
        test.givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(10);

        for (int vehiclesCount = 0; vehiclesCount < 10; vehiclesCount++) {
            test.givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(vehiclesCount);
        }   
    }
*/
    @Test
    public void testStartLine() {
        Track rectTrack;
        LineSection actualStartLine;
        LineSection expectedStartLine1;

        GIVEN_TRACK_UNDER_TEST:
        rectTrack = BoardBuilder.createTrack(BoardBuilder.TrackType.TEST_RECTANGULAR);
        expectedStartLine1 = new LineSection(15, 20, 15, 30);

        WHEN:
        actualStartLine = rectTrack.computeVerticalStartLine();

        THEN:
         assertLineSectionsEqualNoMatterPointsOrder(actualStartLine, expectedStartLine1);
        System.out.println("givenTrack_ShouldStartLineEqualExpected passed");
    }

    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(int numberOfVehicles) {
        String methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1";

        StringBuilder testResultMessage = new StringBuilder();
        Track track = BoardBuilder.createTrack(BoardBuilder.TrackType.TEST_RECTANGULAR);

        LineSection startLine = track.computeVerticalStartLine();
        float startLineX = startLine.p1.x;
        Board testBoard = new Board(numberOfVehicles, track);

        for (Vehicle veh : testBoard.vehicles) {
            assertEquals(veh.v.position.x, startLineX, methodName);
            testResultMessage.append(" ").append(veh.v.position).append(" ");
        }
    }
   
    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(int numberOfVehicles) {
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

            assertEquals(distanceBetweenVehicle0And1, distanceBetweenNextVehicles, methodName);            
        }
    }

    private Vehicle moveVehicleNTimes(Board board, int N, int vehicleId) {

        for (int i = 0; i < N; i++) {
            board.moveVehicle(vehicleId);
            //all the time vehicle should be on the track
            assertTrue(board.track.isInsideTrack(board.getVehiclePosition(vehicleId)),
                     "moveVehicle31times - design your test in the way the vehicle will be in track all the time "
                    + " current pos=" + board.vehicles.get(vehicleId).v.position);
        }
        return board.vehicles.get(vehicleId);
    }

    @Test
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
            turnVehicleLeft90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 250, vehicleId);//go down
            turnVehicleLeft90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 62, vehicleId);//go left
            turnVehicleLeft90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 250, vehicleId);//go up
            turnVehicleLeft90deg(veh);
            veh = moveVehicleNTimes(rectBoard, 31, vehicleId);//go right to starting position     
        }
        THEN_LAP_WAS_INCREASED:
        {
            assertEquals(veh.laps, lapsBeforeMove + 1, "updateVehicleTravelledWayAngleTest");
        }
    }

    @Test
    public void testIfInnerBoundsAreInsideOuters() {
        Track trackUnderTest;
        TrackType tracksToTest[] = {
            TrackType.DONUT,
            TrackType.KIDNEY,
            TrackType.PENTAGON,
            TrackType.RECTANGULAR,
            TrackType.SINE,
            TrackType.TEST_RECTANGULAR,
            TrackType.TRIANGLE,
            TrackType.WERONIKA
        };

        for (TrackType trackType : tracksToTest) {
            GIVEN:
            trackUnderTest = BoardBuilder.createTrack(trackType);

            WHEN:
            testIfInnerBoundIsInsideOuter(trackUnderTest);
        }
    }
    
    private void testIfInnerBoundIsInsideOuter(Track track) {
        track.innerBound.points.forEach((PointAG innerPoint) -> {            
            Polygon truncatedOuterBnd = track.outerBound.convertToPolygon();
            
            THEN:
            assertTrue(truncatedOuterBnd.contains(innerPoint.x, innerPoint.y),
                    "(At least) one of points of inner bound is "
                    + " not inside outer bound for track " + track.getName()
                    + " invalid point is " + innerPoint);
        });
    }

    private void turnVehicleLeft90deg(Vehicle vehicle1) {
        float iteration = 0;
        float iterationsToDo90degTurn = Numbers.roundToFloat(Math.PI / 2 / VelocityVector.ROTATION_UNIT);
        while (iteration < iterationsToDo90degTurn) {
            vehicle1.turnLeft();
            iteration++;
        }
    }
}
