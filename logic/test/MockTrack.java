package logic.test;

import libs.math2.LineSection;
import logic.Track;

public class MockTrack extends Track {

    public MockTrack(Track track)
    {
        this.checkpoints = track.checkpoints.clone();
        this.innerBound = track.innerBound.clone();
        this.outerBound = track.outerBound.clone();       
    }
    
    public LineSection getIntersectedInnerLine() {
        return this.intersectedInnerLine;
    }

    public LineSection getIntersectedOuterLine() {
        return this.intersectedOuterLine;
    }

    public LineSection getDownFromCenterLineSection() {
        return this.downFromCenterLineSection;
    }

}
