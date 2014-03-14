package de.metalcon.middleware.controller.entity.impl.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.EventsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.PhotosTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.VenueView;

@Controller
public class VenueController extends EntityController<VenueView> implements
        AboutTabGenerating, EventsTabGenerating, NewsfeedTabGenerating,
        PhotosTabGenerating, RecommendationsTabGenerating, UsersTabGenerating {

    @Autowired
    private VenueAboutTabGenerator aboutTabGenerator;

    @Autowired
    private VenueEventsTabGenerator eventsTabGenerator;

    @Autowired
    private VenueNewsfeedTabGenerator newsfeedTabGenerator;

    @Autowired
    private VenuePhotosTabGenerator photosTabGenerator;

    @Autowired
    private VenueRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private VenueUsersTabGenerator usersTabGenerator;

    public VenueController() {
        super(EntityType.VENUE, VenueView.class);
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
    public NewsfeedTabGenerator getNewsfeedTabGenerator() {
        return newsfeedTabGenerator;
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
    public UsersTabGenerator getUsersTabGenerator() {
        return usersTabGenerator;
    }

}
