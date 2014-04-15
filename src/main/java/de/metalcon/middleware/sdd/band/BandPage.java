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

    public TrackEntry[] getTracks() {
        return tracks;
    }

    public RecordEntry[] getRecords() {
        return records;
    }

}
