package de.metalcon.middleware.controller.entity.impl.record;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.domain.entity.TracksTabEntry;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordPage;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class RecordTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TracksTabEntry> getTracksContent(SddOutput page) {
        RecordPage recordPage = (RecordPage) page;
        List<TracksTabEntry> tracks = new LinkedList<TracksTabEntry>();
        if (recordPage.getTracks() != null) {
            for (TrackEntry track : recordPage.getTracks()) {
                TracksTabEntry tracksTabEntry =
                        new TracksTabEntry(track.getMuid());
                tracksTabEntry.setName(track.getName());
                tracksTabEntry.setTrackNumber(Integer.parseInt(track
                        .getTrackNumber()));
                tracks.add(tracksTabEntry);
            }
        }
        return tracks;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(SddOutput page) {
        RecordPage recordPage = (RecordPage) page;
        List<TrackEntry> tracks = new LinkedList<TrackEntry>();

        int i = 0;
        if (recordPage.getTracks() != null) {
            for (TrackEntry track : recordPage.getTracks()) {
                tracks.add(track);
                if (++i == 5) {
                    break;
                }
            }
        }
        return tracks;
    }

}
