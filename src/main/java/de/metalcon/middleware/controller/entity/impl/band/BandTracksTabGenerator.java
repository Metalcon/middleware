package de.metalcon.middleware.controller.entity.impl.band;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabEntry;

@Component
public class BandTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TracksTabEntry> getTracksContent(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<TracksTabEntry> tracks = new LinkedList<TracksTabEntry>();
        for (TrackEntry track : bandPage.getTracks()) {
            TracksTabEntry tracksTabEntry = new TracksTabEntry();
            tracksTabEntry.setMuid(track.getMuid());
            tracksTabEntry.setName(track.getName());
            tracksTabEntry.setTrackNumber(Integer.parseInt(track
                    .getTrackNumber()));
            tracks.add(tracksTabEntry);
        }
        return tracks;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<TrackEntry> tracks = new LinkedList<TrackEntry>();

        if (bandPage.getTracks() != null) {
            int i = 0;
            for (TrackEntry track : bandPage.getTracks()) {
                tracks.add(track);
                if (++i == 5) {
                    break;
                }
            }
        }

        return tracks;
    }

}
