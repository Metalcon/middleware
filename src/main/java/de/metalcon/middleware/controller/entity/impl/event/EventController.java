package de.metalcon.middleware.controller.entity.impl.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.BandsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.PhotosTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.EventView;

@Controller
public class EventController extends EntityController<EventView> implements
        AboutTabGenerating, BandsTabGenerating, NewsTabGenerating,
        PhotosTabGenerating, RecommendationsTabGenerating, UsersTabGenerating {

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
