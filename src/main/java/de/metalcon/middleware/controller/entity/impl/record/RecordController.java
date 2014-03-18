package de.metalcon.middleware.controller.entity.impl.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.RecordView;

@Controller
public class RecordController extends EntityController<RecordView> {

    @Autowired
    private RecordAboutTabGenerator aboutTabGenerator;

    @Autowired
    private RecordNewsTabGenerator newsTabGenerator;

    @Autowired
    private RecordRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private RecordReviewsTabGenerator reviewsTabGenerator;

    @Autowired
    private RecordTracksTabGenerator tracksTabGenerator;

    @Autowired
    private RecordUsersTabGenerator usersTabGenerator;

    public RecordController() {
        super(EntityType.RECORD, RecordView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
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
