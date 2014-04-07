package de.metalcon.middleware.controller.entity.impl.band;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.controller.RequestParameters;
import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.impl.BandView;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

/**
 * controller handling band requests
 */
@Controller
public class BandController extends EntityController<BandView> {

    @Autowired
    private BandAboutTabGenerator aboutTabGenerator;

    @Autowired
    private BandEventsTabGenerator eventsTabGenerator;

    @Autowired
    private BandNewsTabGenerator newsTabGenerator;

    @Autowired
    private BandPhotosTabGenerator photosTabGenerator;

    @Autowired
    private BandRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private BandRecordsTabGenerator recordsTabGenerator;

    @Autowired
    private BandTracksTabGenerator tracksTabGenerator;

    @Autowired
    private BandUsersTabGenerator usersTabGenerator;

    public BandController() {
        super(EntityType.BAND, BandView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
    }

    @Override
    public EventsTabGenerator getEventsTabGenerator() {
        return eventsTabGenerator;
    }

    @Override
    public NewsTabGenerator getNewsTabGenerator() {
        return newsTabGenerator;
    }

    @Override
    public PhotosTabGenerator getPhotosTabGenerator() {
        return photosTabGenerator;
    }

    @Override
    public RecommendationsTabGenerator getRecommendationsTabGenerator() {
        return recommendationsTabGenerator;
    }

    @Override
    public RecordsTabGenerator getRecordsTabGenerator() {
        return recordsTabGenerator;
    }

    @Override
    public TracksTabGenerator getTracksTabGenerator() {
        return tracksTabGenerator;
    }

    @Override
    public UsersTabGenerator getUsersTabGenerator() {
        return usersTabGenerator;
    }

    @Override
    protected void handleGet(
            BandView view,
            RequestParameters params,
            EntityTabType entityTabType) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        super.handleGet(view, params, entityTabType);
        // TODO: here we need access to the entity 
        view.setFreebaseId("testID");
    }

}
