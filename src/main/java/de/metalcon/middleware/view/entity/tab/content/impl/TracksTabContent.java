package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.domain.entity.TrackData;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class TracksTabContent extends EntityTabContent {

    private List<TrackData> tracks;

    public TracksTabContent() {
        super(EntityTabType.TRACKS);
    }

    public List<TrackData> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackData> tracks) {
        this.tracks = tracks;
    }

}
