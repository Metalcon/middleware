package de.metalcon.middleware.sdd.band;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.track.TrackEntry;

public class BandTabTracks extends SddOutput {

    private TrackEntry[] tracks;

    public TrackEntry[] getTracks() {
        return tracks;
    }

    public void setTracks(TrackEntry[] tracks) {
        this.tracks = tracks;
    }

}
