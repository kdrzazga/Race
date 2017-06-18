package unit_tests.logic_test;

import libs.math2.PolygonAG;
import logic.BoardBuilder;
import logic.Track;
import logic.drive_algorithms.DriveAlgorithm;
import logic.drive_algorithms.KeepCloseInnerBound;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class DriveAlgorithmTests  {

    @Test
    public static void testComputeDesiredRoute() {

        Track tracksToTest[] = {
            BoardBuilder.createTrack(BoardBuilder.TrackType.TRIANGLE),
            BoardBuilder.createTrack(BoardBuilder.TrackType.DONUT),
           // BoardBuilder.createTrack(BoardBuilder.TrackType.KIDNEY),
            BoardBuilder.createTrack(BoardBuilder.TrackType.PENTAGON),
            //BoardBuilder.createTrack(BoardBuilder.TrackType.RECTANGULAR),
            //BoardBuilder.createTrack(BoardBuilder.TrackType.SINE),
            BoardBuilder.createTrack(BoardBuilder.TrackType.WERONIKA)
        };

        for (Track trackToTest : tracksToTest) {
            DriveAlgorithm algorithmsWithDesiredRoute[] = {
                new KeepCloseInnerBound(trackToTest)
            };

            for (DriveAlgorithm algorithm : algorithmsWithDesiredRoute) {
                testComputeDesiredRoute(algorithm);
            }
        }
    }

    private static void testComputeDesiredRoute(DriveAlgorithm driveAlgorithm) {
        if (!driveAlgorithm.algorithmWithDesiredRoute) {
            throw new RuntimeException("testComputeDesiredRoute error - test not supported for "
                    + driveAlgorithm.toString() + " This algorithm doesn't have desired route  field.");
        }
        Track track = driveAlgorithm.getTrack();
        PolygonAG desiredRoute = driveAlgorithm.getDesiredTrack();

        desiredRoute.points.forEach((point) -> {
            assertTrue (track.isInsideTrack(point));        
        });
    }
}
