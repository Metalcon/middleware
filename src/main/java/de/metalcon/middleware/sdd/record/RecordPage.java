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

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public BandEntry getBand() {
        return band;
    }

    public void setBand(BandEntry band) {
        this.band = band;
    }

    public TrackEntry[] getTracks() {
        return tracks;
    }

    public void setTracks(TrackEntry[] tracks) {
        this.tracks = tracks;
    }

}
