package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class TracksTabContent extends EntityTabContent {

    private List<TrackEntry> tracks;

    public TracksTabContent() {
        super(EntityTabType.TRACKS);
    }

    public List<TrackEntry> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntry> tracks) {
        this.tracks = tracks;
    }

}
