package org.kd.logic.creation;

import org.kd.libs.math2.CircleAG;
import org.kd.libs.math2.Numbers;
import org.kd.libs.math2.PointAG;
import org.kd.libs.math2.PolygonAG;
import org.kd.logic.Track;
import org.kd.logic.creation.BoardBuilder.TrackType;

import java.util.*;

import static org.kd.logic.creation.BoardBuilder.TrackType.*;

public class TrackFactory {
    // <editor-fold defaultstate="collapsed" desc="Point arrays for tracks">
    private static final PointAG[] RECT_OUTER_BOUND_PTS = {new PointAG(50, 710), new PointAG(350, 710), new PointAG(550, 710), new PointAG(550, 400), new PointAG(325, 400),
            new PointAG(325, 275), new PointAG(350, 275), new PointAG(350, 345), new PointAG(370, 345), new PointAG(370, 375), new PointAG(550, 375),
            new PointAG(550, 50), new PointAG(50, 50)};
    private static final PointAG[] RECT_INNER_BOUND_PTS = {new PointAG(150, 600), new PointAG(450, 600), new PointAG(450, 600), new PointAG(450, 500),
            new PointAG(250, 500), new PointAG(250, 200), new PointAG(425, 200), new PointAG(425, 300), new PointAG(450, 300), new PointAG(450, 100),
            new PointAG(150, 100), new PointAG(150, 150), new PointAG(150, 200), new PointAG(150, 250), new PointAG(150, 300), new PointAG(150, 350),
            new PointAG(150, 360), new PointAG(150, 370), new PointAG(150, 380), new PointAG(150, 400), new PointAG(150, 450), new PointAG(150, 500), new PointAG(150, 550)};

    private static final PointAG[] TEST_RECT_OUTER_BOUND_PTS = {new PointAG(0, 0), new PointAG(30, 0), new PointAG(30, 30), new PointAG(0, 30)};
    private static final PointAG[] TEST_RECT_INNER_BOUND_PTS = {new PointAG(10, 10), new PointAG(20, 10), new PointAG(20, 20), new PointAG(10, 20)};

    private static final PointAG[] TRIANGLE_OUTER_BOUND_PTS = {
            new PointAG(140, 88), new PointAG(535, 640),
            new PointAG(529, 680), new PointAG(500, 700), new PointAG(410, 710), new PointAG(190, 720), new PointAG(145, 705), new PointAG(30, 80), new PointAG(54, 50), new PointAG(84, 53)};

    private static final PointAG[] PENTAGON_OUTER_BOUND_PTS = {new PointAG(8, 640), new PointAG(20, 120), new PointAG(45, 80), new PointAG(170, 29), new PointAG(230, 17), new PointAG(534, 130),
            new PointAG(544, 166), new PointAG(400, 680), new PointAG(370, 700), new PointAG(250, 680), new PointAG(40, 690)};

    private static final PointAG[] WERONIKA_OUTER_BOUND_PTS = {new PointAG(10, 10), new PointAG(500, 15), new PointAG(490, 900), new PointAG(10, 890)};
    private static final PointAG[] WERONIKA_INNER_BOUND_PTS = {new PointAG(240, 640), new PointAG(260, 640), new PointAG(260, 660), new PointAG(240, 660)};

    private static final PointAG[] KIDNEY_OUTER_BOUND_PTS = {
            new PointAG(427, 100), new PointAG(460, 160), new PointAG(475, 210), new PointAG(492, 263), new PointAG(500, 275),
            new PointAG(525, 350), new PointAG(530, 413), new PointAG(525, 500), new PointAG(500, 575),
            new PointAG(475, 626), new PointAG(425, 675), new PointAG(350, 700), new PointAG(300, 709),
            new PointAG(200, 707), new PointAG(120, 709), new PointAG(100, 705), new PointAG(70, 696),
            new PointAG(23, 675), new PointAG(24, 640), new PointAG(35, 605), new PointAG(50, 566),
            new PointAG(75, 520), new PointAG(125, 500), new PointAG(175, 465), new PointAG(215, 421),
            new PointAG(212, 375), new PointAG(200, 325), new PointAG(175, 275), new PointAG(125, 240),
            new PointAG(100, 200),
            new PointAG(75, 150), new PointAG(100, 100), new PointAG(150, 50), new PointAG(200, 25),
            new PointAG(250, 20), new PointAG(300, 26), new PointAG(350, 40), new PointAG(388, 50)};
    private static final PointAG[] KIDNEY_INNER_BOUND_PTS = {
            new PointAG(250, 175), new PointAG(278, 140), new PointAG(325, 148), new PointAG(348, 180),
            new PointAG(351, 215), new PointAG(355, 218), new PointAG(365, 236),
            new PointAG(367, 238), new PointAG(370, 252), new PointAG(387, 308), new PointAG(409, 366), new PointAG(429, 420),
            new PointAG(437, 463), new PointAG(438, 468), new PointAG(439, 471), new PointAG(440, 475),
            new PointAG(441, 478), new PointAG(426, 518), new PointAG(426, 518), new PointAG(426, 518),
            new PointAG(400, 560), new PointAG(357, 607),
            new PointAG(272, 640), new PointAG(250, 635), new PointAG(204, 617), new PointAG(180, 586),
            new PointAG(189, 545), new PointAG(240, 505), new PointAG(278, 460), new PointAG(280, 440),
            new PointAG(285, 389), new PointAG(290, 312), new PointAG(267, 241)
    };
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Point arrays for routes for computer players">
    private static final PointAG[] DONUT_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(375, 525), new PointAG(450, 450), new PointAG(480, 390),
            new PointAG(435, 270), new PointAG(300, 190), new PointAG(160, 270),
            new PointAG(130, 380), new PointAG(200, 510), new PointAG(307, 540)
    };

    private static final PointAG[] RECT_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(490, 650), new PointAG(490, 450), new PointAG(290, 450),
            new PointAG(290, 240), new PointAG(375, 260), new PointAG(420, 340),
            new PointAG(500, 300), new PointAG(493, 80), new PointAG(95, 75),
            new PointAG(100, 650), new PointAG(260, 650)
    };

    private static final PointAG[] KIDNEY_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(448, 576), new PointAG(490, 433), new PointAG(341, 105),
            new PointAG(260, 80), new PointAG(200, 134), new PointAG(245, 425),
            new PointAG(92, 620), new PointAG(170, 665), new PointAG(340, 667)
    };

    private static final PointAG[] TRIANGLE_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(448, 620), new PointAG(425, 545), new PointAG(176, 205),
            new PointAG(93, 239), new PointAG(180, 630), new PointAG(265, 665)
    };

    private static final PointAG[] PENTAGON_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(363, 630), new PointAG(500, 180), new PointAG(230, 70),
            new PointAG(70, 130), new PointAG(42, 615), new PointAG(250, 635)
    };

    private static final PointAG[] WERONIKA_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(300, 650), new PointAG(250, 600), new PointAG(212, 640),
            new PointAG(250, 700), new PointAG(265, 680)
    };

    private static final PointAG[] TEST_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(25, 25), new PointAG(22, 5), new PointAG(7, 7),
            new PointAG(5, 25), new PointAG(17, 25)
    };

    private static final PointAG[] SINE_WAY_FOR_COMP_PLR_PTS = {
            new PointAG(420, 680), new PointAG(317, 378), new PointAG(323, 333),
            new PointAG(421, 152), new PointAG(213, 50), new PointAG(234, 127),
            new PointAG(230, 195), new PointAG(125, 366), new PointAG(231, 598),
            new PointAG(224, 645), new PointAG(212, 680), new PointAG(160, 800),
            new PointAG(282, 760)
    };

    //</editor-fold>

    public Track createTrack(TrackType trackType) {

        Map<TrackType, Track> createTrackMap = Map.of(
                DONUT, create_50_50__550_550_DonutTrack(),
                RECTANGULAR, createTrack(RECT_OUTER_BOUND_PTS, RECT_INNER_BOUND_PTS, RECT_WAY_FOR_COMP_PLR_PTS),
                KIDNEY, createTrack(KIDNEY_OUTER_BOUND_PTS, KIDNEY_INNER_BOUND_PTS, KIDNEY_WAY_FOR_COMP_PLR_PTS),
                TRIANGLE, createTrack(TRIANGLE_OUTER_BOUND_PTS, 0.5f, TRIANGLE_WAY_FOR_COMP_PLR_PTS),
                PENTAGON, createTrack(PENTAGON_OUTER_BOUND_PTS, 0.7f, PENTAGON_WAY_FOR_COMP_PLR_PTS),
                WERONIKA, createTrack(WERONIKA_OUTER_BOUND_PTS, WERONIKA_INNER_BOUND_PTS, WERONIKA_WAY_FOR_COMP_PLR_PTS),
                TEST_RECTANGULAR, createTrack(TEST_RECT_OUTER_BOUND_PTS, TEST_RECT_INNER_BOUND_PTS, TEST_WAY_FOR_COMP_PLR_PTS),
                SINE, create_50_50__450_500_SineTrack()
        );

        return Optional
                .ofNullable(createTrackMap.get(trackType))
                .orElse(create_50_50__450_500_SineTrack());
    }

    public Track createTrack(PointAG[] outerBoundPts, float scaleFactor,
                             PointAG[] routeForCompPlyrPts) {
        Track track = new Track();
        track.outerBound.points.addAll(Arrays.asList(outerBoundPts));

        track.innerBound = track.outerBound.clone();
        track.innerBound.scale(scaleFactor);

        track.computeCheckpoints();
        track.routeForCompPlyr = new PolygonAG(routeForCompPlyrPts);

        return track;
    }

    public Track createTrack(PointAG[] outerBoundPts, PointAG[] innerBoundPts,
                             PointAG[] routeForCompPlyrPts) {

        var track = new Track();
        track.outerBound.points.addAll(Arrays.asList(outerBoundPts));
        track.innerBound.points.addAll(Arrays.asList(innerBoundPts));

        track.computeCheckpoints();
        track.routeForCompPlyr = new PolygonAG(routeForCompPlyrPts);

        return track;
    }

    /*
creates a donut track bounded by rectangle(50,50, 550,550)
 */
    private Track create_50_50__550_550_DonutTrack() {

        var donutTrack = new Track();
        var donutCenter = new PointAG(300, 370);
        int outerBoundRadius = 250;
        int innerBoundRadius = 100;
        int numberOfPoints = 90;

        var innerBound = new CircleAG(donutCenter, innerBoundRadius, numberOfPoints);
        var outerBound = new CircleAG(donutCenter, outerBoundRadius, numberOfPoints);

        donutTrack.innerBound = innerBound.getPoints();
        donutTrack.outerBound = outerBound.getPoints();
        donutTrack.computeCheckpoints();
        donutTrack.routeForCompPlyr = new PolygonAG(DONUT_WAY_FOR_COMP_PLR_PTS);

        return donutTrack;
    }

    private Track create_50_50__450_500_SineTrack() {
        var sineTrack = new Track();
        List<PointAG> outerBoundPts = new ArrayList<>();
        List<PointAG> innerBoundPts = new ArrayList<>();

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

        for (PointAG outerBoundPt : outerBoundPts) {

            float ptx = Numbers.roundToFloat(outerBoundPt.getX() * 0.3 + INNER_OFFSET_HORIZONTAL);
            float pty = Numbers.roundToFloat(outerBoundPt.getY() * 0.8 + INNER_OFFSET_VERTICAL);
            innerBoundPts.add(new PointAG(ptx, pty));
        }

        sineTrack.outerBound.points.addAll(outerBoundPts);
        sineTrack.innerBound.points.addAll(innerBoundPts);
        sineTrack.computeCheckpoints();
        sineTrack.routeForCompPlyr = new PolygonAG(SINE_WAY_FOR_COMP_PLR_PTS);

        return sineTrack;
    }
}
