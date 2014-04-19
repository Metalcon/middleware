package de.metalcon.middleware.controller.entity.impl.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.VenueView;

@Controller
public class VenueController extends EntityController<VenueView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        //        EventPage bp = (EventPage) data.getPage();

        EntityData rd = new EntityData(data.getMuid());
        return rd;
    }

    @Autowired
    private VenueAboutTabGenerator aboutTabGenerator;

    @Autowired
    private VenueEventsTabGenerator eventsTabGenerator;

    @Autowired
    private VenueNewsTabGenerator newsTabGenerator;

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
    public UsersTabGenerator getUsersTabGenerator() {
        return usersTabGenerator;
    }

}
