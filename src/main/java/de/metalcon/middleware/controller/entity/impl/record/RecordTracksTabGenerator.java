package de.metalcon.middleware.controller.entity.impl.record;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordPage;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class RecordTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TrackEntry> getTracksContent(SddOutput page) {
        RecordPage RecordPage = (RecordPage) page;
        List<TrackEntry> tracks = Arrays.asList(RecordPage.getTracks());
        return tracks;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(SddOutput page) {
        RecordPage RecordPage = (RecordPage) page;
        List<TrackEntry> tracks = new LinkedList<TrackEntry>();

        int i = 0;
        for (TrackEntry track : RecordPage.getTracks()) {
            tracks.add(track);
            if (++i == 5) {
                break;
            }
        }

        return tracks;
    }

}
