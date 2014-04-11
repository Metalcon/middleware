package de.metalcon.middleware.sdd.record;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.track.TrackEntry;

public class RecordTabTracks extends SddOutput {

    private TrackEntry[] tracks;

    public TrackEntry[] getTracks() {
        return tracks;
    }

    public void setTracks(TrackEntry[] tracks) {
        this.tracks = tracks;
    }

}
