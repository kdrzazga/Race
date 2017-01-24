package miscallenous;

import java.util.ArrayList;
import java.util.Arrays;

import libs.math2.CircleAG;
import libs.math2.General;
import libs.math2.PointAG;
import logic.Board;
import logic.Track;
import logic.Vehicle;
import logic.VelocityVector;

public class Mocks {

    public enum TrackType {
        RECTANGULAR_1, CIRCULAR_1, KIDNEY, SINE, TEST_RECTANGULAR;

        @Override
        public String toString() {
            switch (this) {
                case RECTANGULAR_1:
                    return "Rectangular track";
                case CIRCULAR_1:
                    return "Circular track";
                case KIDNEY:
                    return "Kidney track";
                case SINE:
                    return "Sine track";
                default:
                    return "Test rectangular";
            }
        }
    };

    public static Vehicle createVehicleAtPosition(int id, float x, float y) {
        Vehicle result = new Vehicle(id, 1, new PointAG(x, y));
        result.active = true;

        return result;
    }

    public static Board createBoardOnTrack(int numberOfVehicles, TrackType trackType) {
        Board board = new Board();
        board.track = createTrack(trackType);
        board.vehicles = createVehicles(numberOfVehicles, board);
        return board;
    }

    private static ArrayList<Vehicle> createVehicles(int numberOfVehicles, Board board) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int initialSpeed = VelocityVector.V_MIN;

        for (int i = 0; i < numberOfVehicles; i++) {
            PointAG vehiclePosition = board.track.getStartPosition(i, numberOfVehicles);
            boolean active = true;
            vehicles.add(new Vehicle(i, initialSpeed, vehiclePosition, active));
        }

        return vehicles;
    }

    private static Track createTrack(TrackType trackType) {
        switch (trackType) {
            case CIRCULAR_1:
                return create_50_50__550_550_DonutTrack();
            case RECTANGULAR_1:
                return create_50_50__350_250_RectangularTrack();
            case KIDNEY:
                return create_7_5__550_800_KidneyTrack();
            case TEST_RECTANGULAR:
                return create_0_0__30_30_TestRectangularTrack();
            default:
                //SINE:
                return create_50_50__450_500_SineTrack();                
        }
    }

    public static Board createBoardWith2VehiclesOnRectTrack() {
        Board result = new Board();
        result.track = create_50_50__350_250_RectangularTrack();
        result.vehicles.add(createVehicleAtPosition(0, 100, 100));
        result.vehicles.add(createVehicleAtPosition(1, 100, 120));

        return result;
    }

    public static Track create_50_50__350_250_RectangularTrack() {
        Track rectangularTrack = new Track();

        PointAG outerBoundPts[] = {new PointAG(50, 250), new PointAG(350, 250), new PointAG(350, 50), new PointAG(50, 50)};
        PointAG innerBoundPts[] = {new PointAG(100, 150), new PointAG(250, 150), new PointAG(250, 100), new PointAG(100, 100)};

        rectangularTrack.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        rectangularTrack.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        return rectangularTrack;
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

        return donutTrack;
    }

    public static Track create_0_0__30_30_TestRectangularTrack() {
        Track rectangularTrack = new Track();

        PointAG outerBoundPts[] = {new PointAG(0, 0), new PointAG(30, 0), new PointAG(30, 30), new PointAG(0, 30)};
        PointAG innerBoundPts[] = {new PointAG(10, 10), new PointAG(20, 10), new PointAG(20, 20), new PointAG(10, 20)};

        rectangularTrack.innerBound.points.addAll(Arrays.asList(innerBoundPts));
        rectangularTrack.outerBound.points.addAll(Arrays.asList(outerBoundPts));

        return rectangularTrack;
    }

    public static Track create_7_5__550_800_KidneyTrack() {
        Track kidneyTrack = new Track();

        PointAG outerBoundPts[] = {
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

        PointAG innerBoundPts[] = {
            new PointAG(250, 175), new PointAG(275, 150), new PointAG(325, 148), new PointAG(348, 180),
            new PointAG(367, 235), new PointAG(387, 298), new PointAG(409, 366), new PointAG(429, 420),
            new PointAG(440, 473), new PointAG(426, 518), new PointAG(400, 560), new PointAG(357, 607),
            new PointAG(300, 640), new PointAG(250, 635), new PointAG(204, 617), new PointAG(180, 586),
            new PointAG(189, 545), new PointAG(244, 505), new PointAG(288, 460), new PointAG(307, 440),
            new PointAG(305, 389), new PointAG(302, 312), new PointAG(267, 241)
        };

        kidneyTrack.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        kidneyTrack.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        return kidneyTrack;
    }

    public static Track create_50_50__450_500_SineTrack() {
        Track sineTrack = new Track();
        ArrayList<PointAG> outerBoundPts = new ArrayList<>();
        ArrayList<PointAG> innerBoundPts = new ArrayList<>();

        final int WIDTH_FACTOR = 90;
        final double VERTICAL_FACTOR = 2 * Math.PI / 500;
        final int HORIZONTAL_TRACK_OFFSET = 55;
        final int TRACK_HEIGHT = 630;
        final int LEFT_SIDE_OFFSET = 70;
        final int RIGHT_SIDE_OFFSET = 370;
        final int ITERATION_STEP_IN_PIXELS = 20;

        int y = 90;

        while (y < TRACK_HEIGHT) {
            float x = General.roundToFloat(WIDTH_FACTOR * Math.sin(VERTICAL_FACTOR * y)
                    + LEFT_SIDE_OFFSET + HORIZONTAL_TRACK_OFFSET);
            outerBoundPts.add(new PointAG(x, y));
            y += ITERATION_STEP_IN_PIXELS;
        }

        y = (int) (0.9 * y);

        while (y > 0.1 * TRACK_HEIGHT) {
            float x = General.roundToFloat(WIDTH_FACTOR * Math.sin(VERTICAL_FACTOR * y)
                    + RIGHT_SIDE_OFFSET + HORIZONTAL_TRACK_OFFSET);
            outerBoundPts.add(new PointAG(x, y));
            y -= ITERATION_STEP_IN_PIXELS;
        }

        final float INNER_OFFSET_HORIZONTAL = 195;
        final float INNER_OFFSET_VERTICAL = 70;

        for (int i = 0; i < outerBoundPts.size(); i++) {

            float ptx = General.roundToFloat(outerBoundPts.get(i).x * 0.3 + INNER_OFFSET_HORIZONTAL);
            float pty = General.roundToFloat(outerBoundPts.get(i).y * 0.8 + INNER_OFFSET_VERTICAL);
            innerBoundPts.add(new PointAG(ptx, pty));
        }

        sineTrack.outerBound.points.addAll(outerBoundPts);
        sineTrack.innerBound.points.addAll(innerBoundPts);

        return sineTrack;
    }
}
