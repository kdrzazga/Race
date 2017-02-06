package presentation.test;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import libs.Colors;
import libs.math2.PolygonAG;
import logic.Track;
import presentation.ColorSettings;
import presentation.Draw2d;

public class MockDraw2d extends Draw2d {

    public MockDraw2d(JPanel drawablePanel) {
        super(drawablePanel);
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
    
    private void drawCheckpoints(Track track)
    {
        int colorIndex = 0;
            for (PolygonAG checkpoint : track.checkpoints) {
                Color color = Colors.getDarkColor(colorIndex);
                g.setColor(color);
                System.out.print("Color " + color + " index " + colorIndex + "\t");
                g.drawPolygon(checkpoint.convertToPolygon());
                colorIndex++;
            }
    }
}
