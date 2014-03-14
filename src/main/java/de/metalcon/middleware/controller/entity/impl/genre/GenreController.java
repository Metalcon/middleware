package de.metalcon.middleware.controller.entity.impl.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.BandsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.EventsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecordsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.ReviewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.TracksTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.GenreView;

@Controller
public class GenreController extends EntityController<GenreView> implements
        AboutTabGenerating, BandsTabGenerating, EventsTabGenerating,
        NewsfeedTabGenerating, RecommendationsTabGenerating,
        RecordsTabGenerating, ReviewsTabGenerating, TracksTabGenerating,
        UsersTabGenerating {

    @Autowired
    private GenreAboutTabGenerator aboutTabGenerator;

    @Autowired
    private GenreBandsTabGenerator bandsTabGenerator;

    @Autowired
    private GenreEventsTabGenerator eventsTabGenerator;

    @Autowired
    private GenreNewsfeedTabGenerator newsfeedTabGenerator;

    @Autowired
    private GenreRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private GenreRecordsTabGenerator recordsTabGenerator;

    @Autowired
    private GenreReviewsTabGenerator reviewsTabGenerator;

    @Autowired
    private GenreTracksTabGenerator tracksTabGenerator;

    @Autowired
    private GenreUsersTabGenerator usersTabGenerator;

    public GenreController() {
        super(EntityType.GENRE, GenreView.class);
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

}
