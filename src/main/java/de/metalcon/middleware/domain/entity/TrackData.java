package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;

public class TrackData extends EntityData {

    private int trackNumber;

    public TrackData(
            Muid muid) {
        super(muid);
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
}
