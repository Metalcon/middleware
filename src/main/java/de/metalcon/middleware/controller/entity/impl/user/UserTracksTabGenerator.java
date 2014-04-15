package de.metalcon.middleware.controller.entity.impl.user;

import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabEntry;

@Component
public class UserTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TracksTabEntry> getTracksContent(SddOutput page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected List<TrackEntry> getTracksPreview(SddOutput page) {
        // TODO Auto-generated method stub
        return null;
    }

}
