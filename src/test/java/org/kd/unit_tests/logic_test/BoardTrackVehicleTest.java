package org.kd.unit_tests.logic_test;

import org.kd.libs.math2.LineSection;
import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PointAG;
import org.kd.logic.Board;
import org.kd.logic.Track;
import org.kd.logic.Vehicle;
import org.kd.logic.VelocityVector;
import org.kd.logic.creation.BoardBuilder;
import org.kd.logic.creation.BoardBuilder.TrackType;
import org.kd.logic.creation.TrackFactory;
import org.testng.annotations.Test;


import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.kd.libs.math2.LineSection.lineSectionsEqualNoMatterPointsOrder;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BoardTrackVehicleTest {

    private static final Logger logger = Logger.getLogger(BoardTrackVehicleTest.class.getName());

    public BoardTrackVehicleTest() {

        var vehicle = new Vehicle(0);

        vehicle.v = new VelocityVector(2, Math.PI / 2, new PointAG(75, 75));

        Board board = new BoardBuilder()
                .createBoard()
                .withTrack(TrackType.RECTANGULAR)
                .withVehicles(List.of(vehicle))
                .build();
    }

    @Test
    public void testStartLine() {
        Track rectTrack;
        LineSection actualStartLine;
        LineSection expectedStartLine1;

        GIVEN_TRACK_UNDER_TEST:
        rectTrack = new TrackFactory().createTrack(TrackType.TEST_RECTANGULAR);
        expectedStartLine1 = new LineSection(15, 20, 15, 30);

        WHEN:
        actualStartLine = rectTrack.computeVerticalStartLine();

        THEN:
        assertTrue(lineSectionsEqualNoMatterPointsOrder(actualStartLine, expectedStartLine1));
        System.out.println("givenTrack_ShouldStartLineEqualExpected passed");
    }

    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1(int numberOfVehicles) {
        var methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameXCoordinate_1";

        var testResultMessage = new StringBuilder();
        var track = new TrackFactory().createTrack(TrackType.TEST_RECTANGULAR);

        LineSection startLine = track.computeVerticalStartLine();
        float startLineX = startLine.p1.getX();
        var testBoard = new Board(numberOfVehicles, track);

        for (Vehicle veh : testBoard.vehicles) {
            assertEquals(veh.v.position.getX(), startLineX, methodName);
            testResultMessage.append(" ").append(veh.v.position).append(" ");
        }

        logger.info(testResultMessage.toString());
    }

    public void givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther(int numberOfVehicles) {
        var methodName = "givenTestTrack_ShouldAllVehiclesOnStartLineHaveSameDistanceBetweenEachOther";

        var testResultMessage = new StringBuilder();
        testResultMessage.append(methodName).append(" passed for ");

        var testBoard = new BoardBuilder()
                .createBoard()
                .withTrack(TrackType.DONUT)
                .withVehicles(numberOfVehicles)
                .build();

        var vehicle0Position = testBoard.vehicles.get(0).v.position;
        var vehicle1Position = testBoard.vehicles.get(1).v.position;

        var distanceBetweenVehicle0And1 = Numbers.roundToFloat(vehicle0Position.distanceToAntherPointAG(vehicle1Position));

        IntStream.range(2, testBoard.vehicles.size() - 1).forEach(i -> {
            PointAG vehiclePosition = testBoard.vehicles.get(i).v.position;
            PointAG nextVehiclePosition = testBoard.vehicles.get(i + 1).v.position;
            var distanceBetweenNextVehicles = Numbers.roundToFloat(vehiclePosition.distanceToAntherPointAG(nextVehiclePosition));

            assertEquals(distanceBetweenVehicle0And1, distanceBetweenNextVehicles, methodName);
        });
        logger.info(testResultMessage.toString());
    }

    public Vehicle moveVehicleNTimes(Board board, int N, int vehicleId) {

        IntStream.range(0, N).forEach(i -> {
            board.moveVehicle(vehicleId);
            //all the time vehicle should be on the track
            assertTrue(board.track.isInsideTrack(board.getVehiclePosition(vehicleId)),
                    "moveVehicle31times - design your test in the way the vehicle will be in track all the time "
                            + " current pos=" + board.vehicles.get(vehicleId).v.position);
        });
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
            rectBoard = new BoardBuilder()
                    .createBoard()
                    .withTrack(TrackType.WERONIKA)
                    .withVehicles(3)
                    .build();

            veh = rectBoard.vehicles.get(vehicleId);
            veh.v.value = 1;
            lapsBeforeMove = veh.getLaps();
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
            assertEquals(veh.getLaps(), lapsBeforeMove + 1, "updateVehicleTravelledWayAngleTest");
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

        for (var trackType : tracksToTest) {
            GIVEN:
            trackUnderTest = new TrackFactory().createTrack(trackType);

            WHEN:
            testIfInnerBoundIsInsideOuter(trackUnderTest);
        }
    }

    private void testIfInnerBoundIsInsideOuter(Track track) {
        track.innerBound.points.forEach((PointAG innerPoint) -> {
            var truncatedOuterBnd = track.outerBound.convertToPolygon();

            THEN:
            assertTrue(truncatedOuterBnd.contains(innerPoint.getX(), innerPoint.getY()),
                    "(At least) one of points of inner bound is "
                            + " not inside outer bound for track "
                            + " invalid point is " + innerPoint);
        });
    }

    private void turnVehicleLeft90deg(Vehicle vehicle1) {
        var iteration = 0f;
        var iterationsToDo90degTurn = Numbers.roundToFloat(Math.PI / 2 / VelocityVector.ROTATION_UNIT);

        while (iteration < iterationsToDo90degTurn) {
            vehicle1.turnLeft();
            iteration++;
        }
    }
}
