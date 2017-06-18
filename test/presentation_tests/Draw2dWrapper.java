package presentation_tests;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import libs.Colors;
import logic.Track;
import graphical_tests.TrackWrapper;
import presentation.ColorSettings;
import presentation.ColorSettings;
import presentation.Draw2d;
import presentation.Draw2d;

public class Draw2dWrapper extends Draw2d {

    public Draw2dWrapper(JPanel drawablePanel) {
        super(drawablePanel);
    }
    
    public void draw(TrackWrapper mockTrack)
    {
        Track track = mockTrack;        
        this.draw(track);        
    }

    @Override
    public void draw(Track track) {
        super.draw(track);
        drawTrackCenter(track);
        drawCheckpoints(track);
    }

    private void drawTrackCenter(Track track) {
        g.setColor(ColorSettings.START_LINE_COLOR);
        Point trackCenter = track.computeCenter().convertToPoint();
        g.drawOval(trackCenter.x - 2, trackCenter.y - 2, 4, 4);
    }

    private void drawCheckpoints(Track track) {
        int colorIndex = 0;
        for (int i = 0; i < track.checkpoints.length; i++) {
            Color color = Colors.getDarkColor(colorIndex);
            g.setColor(color);
            Point center = track.checkpoints[i].computeCenter().convertToPoint();
            
            g.drawString(Integer.toString(i), center.x, center.y);
            g.drawPolygon(track.checkpoints[i].convertToPolygon());
            colorIndex++;
        }
    }
}
