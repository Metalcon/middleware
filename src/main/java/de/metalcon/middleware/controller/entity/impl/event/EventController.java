package de.metalcon.middleware.controller.entity.impl.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.EventView;

@Controller
public class EventController extends EntityController<EventView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        //        EventPage bp = (EventPage) data.getPage();

        EntityData rd =
                new EntityData(data.getDispatcher(), data.getUserSession()
                        .getMuid(), data.getMuid());
        return rd;
    }

    @Autowired
    private EventAboutTabGenerator aboutTabGenerator;

    @Autowired
    private EventBandsTabGenerator bandsTabGenerator;

    @Autowired
    private EventNewsTabGenerator newsTabGenerator;

    @Autowired
    private EventPhotosTabGenerator photosTabGenerator;

    @Autowired
    private EventRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private EventUsersTabGenerator usersTabGenerator;

    public EventController() {
        super(EntityType.EVENT, EventView.class);
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
