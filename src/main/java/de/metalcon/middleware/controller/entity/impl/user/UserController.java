package de.metalcon.middleware.controller.entity.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.VenuesTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.UserView;

@Controller
public class UserController extends EntityController<UserView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        //        EventPage bp = (EventPage) data.getPage();

        EntityData rd =
                new EntityData(data.getDispatcher(), data.getUserSession()
                        .getMuid(), data.getMuid());
        return rd;
    }

    @Autowired
    private UserAboutTabGenerator aboutTabGenerator;

    @Autowired
    private UserBandsTabGenerator bandsTabGenerator;

    @Autowired
    private UserEventsTabGenerator eventsTabGenerator;

    @Autowired
    private UserNewsTabGenerator newsTabGenerator;

    @Autowired
    private UserPhotosTabGenerator photosTabGenerator;

    @Autowired
    private UserRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private UserRecordsTabGenerator recordsTabGenerator;

    @Autowired
    private UserReviewsTabGenerator reviewsTabGenerator;

    @Autowired
    private UserTracksTabGenerator tracksTabGenerator;

    @Autowired
    private UserUsersTabGenerator usersTabGenerator;

    @Autowired
    private UserVenuesTabGenerator venuesTabGenerator;

    public UserController() {
        super(EntityType.USER, UserView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
    }

    @Override
    public BandsTabGenerator getBandsTabGenerator() {
        return bandsTabGenerator;
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
    public ReviewsTabGenerator getReviewsTabGenerator() {
        return reviewsTabGenerator;
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
    public VenuesTabGenerator getVenuesTabGenerator() {
        return venuesTabGenerator;
    }

}
