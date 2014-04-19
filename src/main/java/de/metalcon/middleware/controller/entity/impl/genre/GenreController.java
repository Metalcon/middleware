package de.metalcon.middleware.controller.entity.impl.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.GenreView;

@Controller
public class GenreController extends EntityController<GenreView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        EntityData rd =
                new EntityData(data.getDispatcher(), data.getUserSession()
                        .getMuid(), data.getMuid());
        return rd;
    }

    @Autowired
    private GenreAboutTabGenerator aboutTabGenerator;

    @Autowired
    private GenreBandsTabGenerator bandsTabGenerator;

    @Autowired
    private GenreEventsTabGenerator eventsTabGenerator;

    @Autowired
    private GenreNewsTabGenerator newsTabGenerator;

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
    public NewsTabGenerator getNewsTabGenerator() {
        return newsTabGenerator;
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
