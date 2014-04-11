package de.metalcon.middleware.sdd.record;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandEntry;
import de.metalcon.middleware.sdd.track.TrackEntry;

public class RecordPage extends SddOutput {

    private String name;

    private String releaseYear;

    private BandEntry band;

    private TrackEntry[] tracks;

    public String getName() {
        return name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public BandEntry getBand() {
        return band;
    }

    public TrackEntry[] getTracks() {
        return tracks;
    }

}
