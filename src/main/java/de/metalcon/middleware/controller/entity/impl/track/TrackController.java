package de.metalcon.middleware.controller.entity.impl.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.TrackView;

@Controller
public class TrackController extends EntityController<TrackView> implements
        AboutTabGenerating, NewsfeedTabGenerating,
        RecommendationsTabGenerating, UsersTabGenerating {

    @Autowired
    private TrackAboutTabGenerator aboutTabGenerator;

    @Autowired
    private TrackNewsfeedTabGenerator newsfeedTabGenerator;

    @Autowired
    private TrackRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private TrackUsersTabGenerator usersTabGenerator;

    public TrackController() {
        super(EntityType.TRACK, TrackView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
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
    public UsersTabGenerator getUsersTabGenerator() {
        return usersTabGenerator;
    }

}
