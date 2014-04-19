package de.metalcon.middleware.controller.entity.impl.record;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.domain.entity.TrackData;
import de.metalcon.middleware.sdd.record.RecordPage;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class RecordTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TrackData>
        getTracksContent(final EntityController.Data data) {
        RecordPage recordPage = (RecordPage) data.getPage();
        List<TrackData> tracks = new LinkedList<TrackData>();
        if (recordPage.getTracks() != null) {
            for (TrackEntry track : recordPage.getTracks()) {
                TrackData tracksTabEntry =
                        new TrackData(data.getDispatcher(), data
                                .getUserSession().getMuid(), track.getMuid());
                tracksTabEntry.setName(track.getName());
                tracksTabEntry.setTrackNumber(Integer.parseInt(track
                        .getTrackNumber()));
                tracks.add(tracksTabEntry);
            }
        }
        return tracks;
    }

    @Override
    protected List<TrackEntry>
        getTracksPreview(final EntityController.Data data) {
        RecordPage recordPage = (RecordPage) data.getPage();
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
