package logic;

import java.util.Arrays;

import libs.math2.CircleAG;
import libs.math2.PointAG;

public class Mocks {

    public enum TrackType {
        RECTANGULAR_1, CIRCULAR_1, TEST_RECTANGULAR;

        @Override
        public String toString() {
            switch (this) {
                case RECTANGULAR_1:
                    return "Rectangular track";
                case CIRCULAR_1:
                    return "Circular track";
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


    public static Board createBoardWithNVehiclesOnTrack(int numberOfVehicles, TrackType trackType) {
        Board result = new Board();

        if (trackType.equals(TrackType.CIRCULAR_1)) {
            result.track = create_50_50__550_550_DonutTrack();
        } else {
            result.track = create_50_50__350_250_RectangularTrack();
        }

        int initialSpeed = VelocityVector.V_MIN;

        for (int i = 0; i < numberOfVehicles; i++) {
            PointAG vehiclePosition = result.track.getStartPosition(i, numberOfVehicles);
            boolean active = true;
            result.vehicles.add(new Vehicle(i, initialSpeed, vehiclePosition, active));
        }

        return result;
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

        PointAG outerBoundPts[] = {new PointAG(50, 50), new PointAG(350, 50), new PointAG(350, 250), new PointAG(50, 250)};
        PointAG innerBoundPts[] = {new PointAG(100, 100), new PointAG(250, 100), new PointAG(250, 150), new PointAG(100, 150)};

        rectangularTrack.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        rectangularTrack.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        return rectangularTrack;
    }

    /*
    creates a donut track bounded by rectangle(50,50, 550,550)
     */
    public static Track create_50_50__550_550_DonutTrack() {

        Track donutTrack = new Track();
        PointAG donutCenter = new PointAG(300, 300);
        int outerBoundRadius = 250;
        int innerBoundRadius = 100;
        int numberOfPoints = 360;

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
}
