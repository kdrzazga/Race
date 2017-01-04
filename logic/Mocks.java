package logic;

import java.util.Arrays;
import libs.math2.CircleAG;
import libs.math2.PointAG;

public class Mocks {

    public enum TrackType {
        RECTANGULAR_1, CIRCULAR_1;

        @Override
        public String toString() {
            if (this.equals(RECTANGULAR_1)) {
                return "Rectangular track";
            } else {
                return "Circular track";
            }
        }
    };

    public static Vehicle createVehicle0At100_100() {
        Vehicle result = new Vehicle(0, 1, new PointAG(100, 100));
        result.active = true;

        return result;
    }

    public static Vehicle createVehicle1At100_120() {
        Vehicle result = new Vehicle(1, 1, new PointAG(100, 120));
        result.active = true;

        return result;
    }

    private static Track convertTrackTypeToTrack(TrackType trackType) {
        if (trackType.equals(TrackType.CIRCULAR_1)) {
            return create_50_50__550_550_DonutTrack();
        } else {
            return create_50_50__350_250_RectangularTrack();
        }
    }

    public static Board createBoardWithNVehiclesOnTrack(int numberOfVehicles, TrackType trackType) {
        Board result = new Board();

        result.track = convertTrackTypeToTrack(trackType);

        int initialSpeed = 0;

        for (int i = 0; i < numberOfVehicles; i++) {
            PointAG vehiclePosition = result.track.getStartPosition(i);
            result.vehicles.add(new Vehicle(i, initialSpeed, vehiclePosition));
        }

        return result;
    }

    public static Board createBoardWith2VehiclesOnTrack(TrackType trackType) {
        Board result = new Board();

        result.track = convertTrackTypeToTrack(trackType);

        result.vehicles.add(createVehicle0At100_100());
        result.vehicles.add(createVehicle1At100_120());

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
}
