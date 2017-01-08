package presentation.game_entities;

import libs.graphics3d.BaseEntity3d;
import libs.math2.Point3d;
import logic.Track;

public class Track3d extends BaseEntity3d   {

    
    private final Track track;

    public Track3d(Track track) {
        this.track = track;
        
        this.center = new Point3d(track.getTrackCentre(), 0);
    }

    @Override
    public void setCenter(float x, float y, float z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCenter(Point3d point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point3d getCenter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
