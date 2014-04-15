package de.metalcon.middleware.view.entity.tab.preview.impl;

import java.util.List;

import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

public class TracksTabPreview extends EntityTabPreview {

    private List<TrackEntry> tracks;

    public TracksTabPreview() {
        super(EntityTabType.TRACKS);
    }

    public List<TrackEntry> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntry> tracks) {
        this.tracks = tracks;
    }

}
