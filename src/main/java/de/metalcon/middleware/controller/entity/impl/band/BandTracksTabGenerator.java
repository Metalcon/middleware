package de.metalcon.middleware.controller.entity.impl.band;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class BandTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TrackEntry> getTracksContent(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<TrackEntry> tracks = Arrays.asList(bandPage.getTracks());
        return tracks;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<TrackEntry> tracks = new LinkedList<TrackEntry>();

        int i = 0;
        for (TrackEntry track : bandPage.getTracks()) {
            tracks.add(track);
            if (++i == 5) {
                break;
            }
        }

        return tracks;
    }

}
