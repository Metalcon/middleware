package de.metalcon.middleware.controller.entity.impl.instrument;

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
import de.metalcon.middleware.view.entity.impl.InstrumentView;

@Controller
public class InstrumentController extends EntityController<InstrumentView>
        implements AboutTabGenerating, NewsfeedTabGenerating,
        RecommendationsTabGenerating, UsersTabGenerating {

    @Autowired
    private InstrumentAboutTabGenerator aboutTabGenerator;

    @Autowired
    private InstrumentNewsfeedTabGenerator newsfeedTabGenerator;

    @Autowired
    private InstrumentRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private InstrumentUsersTabGenerator usersTabGenerator;

    public InstrumentController() {
        super(EntityType.INSTRUMENT, InstrumentView.class);
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
