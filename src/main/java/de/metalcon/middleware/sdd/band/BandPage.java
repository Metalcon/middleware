package de.metalcon.middleware.sdd.band;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordEntry;
import de.metalcon.middleware.sdd.track.TrackEntry;

public class BandPage extends SddOutput {

    private String name;

    private TrackEntry[] tracks;

    private RecordEntry[] records;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrackEntry[] getTracks() {
        return tracks;
    }

    public void setTracks(TrackEntry[] tracks) {
        this.tracks = tracks;
    }

    public RecordEntry[] getRecords() {
        return records;
    }

    public void setRecords(RecordEntry[] records) {
        this.records = records;
    }

}
