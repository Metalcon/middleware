package de.metalcon.middleware.controller.entity.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.BandsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.EventsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.PhotosTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecordsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.ReviewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.TracksTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.VenuesTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.VenuesTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.UserView;

@Controller
public class UserController extends EntityController<UserView> implements
        AboutTabGenerating, BandsTabGenerating, EventsTabGenerating,
        NewsfeedTabGenerating, PhotosTabGenerating,
        RecommendationsTabGenerating, RecordsTabGenerating,
        ReviewsTabGenerating, TracksTabGenerating, UsersTabGenerating,
        VenuesTabGenerating {

    @Autowired
    private UserAboutTabGenerator aboutTabGenerator;

    @Autowired
    private UserBandsTabGenerator bandsTabGenerator;

    @Autowired
    private UserEventsTabGenerator eventsTabGenerator;

    @Autowired
    private UserNewsfeedTabGenerator newsfeedTabGenerator;

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
