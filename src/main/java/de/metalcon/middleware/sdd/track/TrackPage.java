package de.metalcon.middleware.sdd.track;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandEntry;
import de.metalcon.middleware.sdd.record.RecordEntry;

public class TrackPage extends SddOutput {

    private String name;

    private String trackNumber;

    private RecordEntry record;

    private BandEntry band;

    public String getName() {
        return name;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public RecordEntry getRecord() {
        return record;
    }

    public BandEntry getBand() {
        return band;
    }

}
