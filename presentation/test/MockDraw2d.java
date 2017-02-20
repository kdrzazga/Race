package presentation.test;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import libs.Colors;
import logic.Track;
import logic.test.MockTrack;
import presentation.ColorSettings;
import presentation.Draw2d;

public class MockDraw2d extends Draw2d {

    public MockDraw2d(JPanel drawablePanel) {
        super(drawablePanel);
    }
    
    public void draw(MockTrack mockTrack)
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
