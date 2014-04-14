package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class TracksTabContent extends EntityTabContent {

    private List<TracksTabEntry> tracks;

    public TracksTabContent() {
        super(EntityTabType.TRACKS);
    }

    public List<TracksTabEntry> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksTabEntry> tracks) {
        this.tracks = tracks;
    }

}
