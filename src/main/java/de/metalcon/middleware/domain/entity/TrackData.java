package de.metalcon.middleware.domain.entity;

import net.hh.request_dispatcher.Dispatcher;
import de.metalcon.domain.Muid;

public class TrackData extends EntityData {

    private int trackNumber;

    public TrackData(
            final Dispatcher dispatcher,
            final Muid userID,
            final Muid entityID) {
        super(dispatcher, userID, entityID);
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
}
