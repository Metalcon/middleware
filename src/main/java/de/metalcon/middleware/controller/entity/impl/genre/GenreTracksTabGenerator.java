package de.metalcon.middleware.controller.entity.impl.genre;

import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.domain.entity.TrackData;
import de.metalcon.middleware.sdd.track.TrackEntry;

@Component
public class GenreTracksTabGenerator extends TracksTabGenerator {

    @Override
    protected List<TrackData>
        getTracksContent(final EntityController.Data data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected List<TrackEntry>
        getTracksPreview(final EntityController.Data data) {
        // TODO Auto-generated method stub
        return null;
    }

}
