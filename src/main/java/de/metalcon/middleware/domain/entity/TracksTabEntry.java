package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;

public class TracksTabEntry extends Identity {

    private int trackNumber;

    public TracksTabEntry(
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
