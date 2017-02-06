package logic;

import java.util.ArrayList;
import java.util.Arrays;

import libs.math2.CircleAG;
import libs.math2.Numbers;
import libs.math2.PointAG;
import libs.math2.PolygonAG;
import logic.drive_algorithms.DriveAlgorithm;
import logic.drive_algorithms.HumanDriveNullObject;
import logic.drive_algorithms.TurnLeftAlgorithm;

public class BoardBuilder {

    public enum TrackType {
        RECTANGULAR, DONUT, KIDNEY, SINE, TRIANGLE, PENTAGON, WERONIKA, TEST_RECTANGULAR;

        @Override
        public String toString() {
            switch (this) {
                case RECTANGULAR:
                    return "Rectangular track";
                case DONUT:
                    return "Donut";
                case KIDNEY:
                    return "Kidney";
                case SINE:
                    return "Sine";
                case TRIANGLE:
                    return "Triagle";
                case PENTAGON:
                    return "Pentagon track";
                case WERONIKA:
                    return "Weronika";
                default:
                    return "Test rectangular";
            }
        }
    };

    // <editor-fold defaultstate="collapsed" desc="Point arrays for tracks">
    private static final PointAG[] RECT_OUTER_BOUND_PTS = {new PointAG(50, 710), new PointAG(550, 710), new PointAG(550, 400), new PointAG(325, 400),
        new PointAG(325, 275), new PointAG(350, 275), new PointAG(350, 345), new PointAG(370, 345), new PointAG(370, 375), new PointAG(550, 375),
        new PointAG(550, 50), new PointAG(50, 50)};
    private static final PointAG[] RECT_INNER_BOUND_PTS = {new PointAG(150, 600), new PointAG(450, 600), new PointAG(450, 600), new PointAG(450, 500),
        new PointAG(250, 500), new PointAG(250, 200), new PointAG(425, 200), new PointAG(425, 300), new PointAG(450, 300), new PointAG(450, 100),
        new PointAG(150, 100), new PointAG(150, 150), new PointAG(150, 200), new PointAG(150, 250), new PointAG(150, 300),new PointAG(150, 350)
          , new PointAG(150, 360), new PointAG(150, 370), new PointAG(150, 380), new PointAG(150, 400), new PointAG(150, 450), new PointAG(150, 500), new PointAG(150, 550)};

    private static final PointAG[] TEST_RECT_OUTER_BOUND_PTS = {new PointAG(0, 0), new PointAG(30, 0), new PointAG(30, 30), new PointAG(0, 30)};
    private static final PointAG[] TEST_RECT_INNER_BOUND_PTS = {new PointAG(10, 10), new PointAG(20, 10), new PointAG(20, 20), new PointAG(10, 20)};

    private static final PointAG[] TRIANGLE_OUTER_BOUND_PTS = {new PointAG(529, 680), new PointAG(500, 700), new PointAG(410, 710), new PointAG(190, 720),
        new PointAG(145, 705), new PointAG(30, 80), new PointAG(54, 50), new PointAG(84, 53), new PointAG(140, 88), new PointAG(535, 640)};

    private static final PointAG[] PENTAGON_OUTER_BOUND_PTS = {new PointAG(8, 640), new PointAG(20, 120), new PointAG(45, 80), new PointAG(170, 29), new PointAG(230, 17), new PointAG(534, 130),
        new PointAG(544, 166), new PointAG(400, 680), new PointAG(370, 700), new PointAG(250, 680), new PointAG(40, 690)};

    private static final PointAG[] WERONIKA_OUTER_BOUND_PTS = {new PointAG(10, 10), new PointAG(500, 15), new PointAG(490, 900), new PointAG(10, 890)};
    private static final PointAG[] WERONIKA_INNER_BOUND_PTS = {new PointAG(240, 640), new PointAG(260, 640), new PointAG(260, 660), new PointAG(240, 660)};

    private static final PointAG[] KIDNEY_OUTER_BOUND_PTS = {
        new PointAG(427, 100), new PointAG(460, 150), new PointAG(475, 200), new PointAG(500, 275),
        new PointAG(525, 350), new PointAG(530, 413), new PointAG(525, 500), new PointAG(500, 575),
        new PointAG(475, 626), new PointAG(425, 675), new PointAG(350, 700), new PointAG(300, 709),
        new PointAG(200, 707), new PointAG(120, 709), new PointAG(100, 705), new PointAG(70, 696),
        new PointAG(23, 675), new PointAG(24, 640), new PointAG(35, 605), new PointAG(50, 566),
        new PointAG(75, 530), new PointAG(125, 505), new PointAG(175, 470), new PointAG(215, 425),
        new PointAG(212, 375), new PointAG(200, 325), new PointAG(175, 275), new PointAG(125, 240),
        new PointAG(100, 200),
        new PointAG(75, 150), new PointAG(100, 100), new PointAG(150, 50), new PointAG(200, 25),
        new PointAG(250, 20), new PointAG(300, 26), new PointAG(350, 40), new PointAG(388, 50)};
    private static final PointAG[] KIDNEY_INNER_BOUND_PTS = {
        new PointAG(250, 175), new PointAG(275, 150), new PointAG(325, 148), new PointAG(348, 180),
        new PointAG(367, 235), new PointAG(387, 298), new PointAG(409, 366), new PointAG(429, 420),
        new PointAG(440, 473), new PointAG(426, 518), new PointAG(400, 560), new PointAG(357, 607),
        new PointAG(300, 640), new PointAG(250, 635), new PointAG(204, 617), new PointAG(180, 586),
        new PointAG(189, 545), new PointAG(244, 505), new PointAG(288, 460), new PointAG(307, 440),
        new PointAG(305, 389), new PointAG(302, 312), new PointAG(267, 241)
    };
    //</editor-fold>

    public static Vehicle createVehicleAtPosition(int id, float x, float y) {
        Vehicle vehicle = new Vehicle(id, 1, new PointAG(x, y));
        vehicle.active = true;

        return vehicle;
    }

    public static Board createBoardWithTrack(int numberOfVehicles, TrackType trackType) {
        Board board = new Board();
        board.track = createTrack(trackType);
        board.vehicles = createVehicles(numberOfVehicles, board);
        return board;
    }

    private static ArrayList<Vehicle> createVehicles(int numberOfVehicles, Board board) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int initialSpeed = VelocityVector.V_MIN;

        for (int i = 0; i < numberOfVehicles; i++) {
            PointAG vehiclePosition = board.track.computeStartPosition(i, numberOfVehicles);
            DriveAlgorithm driveAlgorithm;
            boolean active = true;

            if (i >= Game.NUMBER_OF_HUMAN_CONTROLLED_VEHICLES) {
                driveAlgorithm = new TurnLeftAlgorithm(board.track);
            } else {
                driveAlgorithm = new HumanDriveNullObject();
            }
            Vehicle vehicle = new Vehicle(i, initialSpeed, vehiclePosition, driveAlgorithm, active);
            driveAlgorithm.setVehicle(vehicle);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    public static Track createTrack(TrackType trackType) {
        switch (trackType) {
            case DONUT:
                return create_50_50__550_550_DonutTrack();
            case RECTANGULAR:
                return createTrack(RECT_OUTER_BOUND_PTS, RECT_INNER_BOUND_PTS);
            case KIDNEY:
                return createTrack(KIDNEY_OUTER_BOUND_PTS, KIDNEY_INNER_BOUND_PTS);
            case TRIANGLE:
                return createTrack(TRIANGLE_OUTER_BOUND_PTS, 0.5f);
            case PENTAGON:
                return createTrack(PENTAGON_OUTER_BOUND_PTS, 0.7f);
            case WERONIKA:
                return createTrack(WERONIKA_OUTER_BOUND_PTS, WERONIKA_INNER_BOUND_PTS);
            case TEST_RECTANGULAR:
                return createTrack(TEST_RECT_OUTER_BOUND_PTS, TEST_RECT_INNER_BOUND_PTS);
            default:
                //SINE:
                return create_50_50__450_500_SineTrack();
        }
    }

    private static Track createTrack(PointAG[] outerBoundPts, float scaleFactor) {
        Track track = new Track();
        track.outerBound.points.addAll(Arrays.asList(outerBoundPts));

        track.innerBound = new PolygonAG(track.outerBound);
        track.innerBound.scale(scaleFactor);

        track.computeCheckpoints();
        return track;
    }

    private static Track createTrack(PointAG[] outerBoundPts, PointAG[] innerBoundPts) {
        Track track = new Track();
        track.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        track.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        track.computeCheckpoints();
        return track;
    }

    public static Board createBoardWith2VehiclesOnRectTrack() {
        Board rectBoard = new Board();
        rectBoard.track = BoardBuilder.createTrack(BoardBuilder.TrackType.RECTANGULAR);
        rectBoard.vehicles.add(createVehicleAtPosition(0, 100, 100));
        rectBoard.vehicles.add(createVehicleAtPosition(1, 100, 120));

        return rectBoard;
    }

    /*
    creates a donut track bounded by rectangle(50,50, 550,550)
     */
    public static Track create_50_50__550_550_DonutTrack() {

        Track donutTrack = new Track();
        PointAG donutCenter = new PointAG(300, 370);
        int outerBoundRadius = 250;
        int innerBoundRadius = 100;
        int numberOfPoints = 90;

        CircleAG innerBound = new CircleAG(donutCenter, innerBoundRadius, numberOfPoints);
        CircleAG outerBound = new CircleAG(donutCenter, outerBoundRadius, numberOfPoints);

        donutTrack.innerBound = innerBound.getPoints();
        donutTrack.outerBound = outerBound.getPoints();
        donutTrack.computeCheckpoints();
        return donutTrack;
    }

    public static Track create_50_50__450_500_SineTrack() {
        Track sineTrack = new Track();
        ArrayList<PointAG> outerBoundPts = new ArrayList<>();
        ArrayList<PointAG> innerBoundPts = new ArrayList<>();

        final int WIDTH_FACTOR = 90;
        final double VERTICAL_FACTOR = 2 * Math.PI / 500;
        final int HORIZONTAL_TRACK_OFFSET = 55;
        final int TRACK_HEIGHT = 870;
        final int LEFT_SIDE_OFFSET = 70;
        final int RIGHT_SIDE_OFFSET = 370;
        final int ITERATION_STEP_IN_PIXELS = 20;

        int y = 10;

        while (y < TRACK_HEIGHT) {
            float x = Numbers.roundToFloat(WIDTH_FACTOR * Math.sin(VERTICAL_FACTOR * y)
                    + LEFT_SIDE_OFFSET + HORIZONTAL_TRACK_OFFSET);
            outerBoundPts.add(new PointAG(x, y));
            y += ITERATION_STEP_IN_PIXELS;
        }

        y = (int) (0.9 * y);

        while (y > 0.1 * TRACK_HEIGHT) {
            float x = Numbers.roundToFloat(WIDTH_FACTOR * Math.sin(VERTICAL_FACTOR * y)
                    + RIGHT_SIDE_OFFSET + HORIZONTAL_TRACK_OFFSET);
            outerBoundPts.add(new PointAG(x, y));
            y -= ITERATION_STEP_IN_PIXELS;
        }

        final float INNER_OFFSET_HORIZONTAL = 195;
        final float INNER_OFFSET_VERTICAL = 70;

        for (int i = 0; i < outerBoundPts.size(); i++) {

            float ptx = Numbers.roundToFloat(outerBoundPts.get(i).x * 0.3 + INNER_OFFSET_HORIZONTAL);
            float pty = Numbers.roundToFloat(outerBoundPts.get(i).y * 0.8 + INNER_OFFSET_VERTICAL);
            innerBoundPts.add(new PointAG(ptx, pty));
        }

        sineTrack.outerBound.points.addAll(outerBoundPts);
        sineTrack.innerBound.points.addAll(innerBoundPts);
        sineTrack.computeCheckpoints();
        return sineTrack;
    }

}
