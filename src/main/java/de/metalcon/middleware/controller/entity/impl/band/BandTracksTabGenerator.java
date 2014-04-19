package de.metalcon.middleware.controller.entity.impl.band;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController.Data;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.domain.entity.TrackData;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class BandTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TrackData> getTracksContent(final Data data) {
        BandPage bandPage = (BandPage) data.getPage();
        List<TrackData> tracks = new LinkedList<TrackData>();
        for (TrackEntry track : bandPage.getTracks()) {
            TrackData tracksTabEntry =
                    new TrackData(data.getDispatcher(), data.getUserSession()
                            .getMuid(), track.getMuid());
            tracksTabEntry.setName(track.getName());
            tracksTabEntry.setTrackNumber(Integer.parseInt(track
                    .getTrackNumber()));
            tracks.add(tracksTabEntry);
        }
        return tracks;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(final Data data) {
        BandPage bandPage = (BandPage) data.getPage();
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
