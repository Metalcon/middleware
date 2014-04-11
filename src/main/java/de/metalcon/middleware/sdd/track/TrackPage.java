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

    public void setName(String name) {
        this.name = name;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public RecordEntry getRecord() {
        return record;
    }

    public void setRecord(RecordEntry record) {
        this.record = record;
    }

    public BandEntry getBand() {
        return band;
    }

    public void setBand(BandEntry band) {
        this.band = band;
    }

}
