package de.metalcon.middleware.sdd.track;

import de.metalcon.middleware.sdd.SddOutput;

public class TrackEntry extends SddOutput {

    private String name;

    private String trackNumber;

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

}
