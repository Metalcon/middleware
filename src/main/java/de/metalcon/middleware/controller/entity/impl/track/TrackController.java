package de.metalcon.middleware.controller.entity.impl.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.TrackView;

@Controller
public class TrackController extends EntityController<TrackView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        //        EventPage bp = (EventPage) data.getPage();

        EntityData rd = new EntityData(data.getMuid());
        return rd;
    }

    @Autowired
    private TrackAboutTabGenerator aboutTabGenerator;

    @Autowired
    private TrackNewsTabGenerator newsTabGenerator;

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
    public NewsTabGenerator getNewsTabGenerator() {
        return newsTabGenerator;
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
