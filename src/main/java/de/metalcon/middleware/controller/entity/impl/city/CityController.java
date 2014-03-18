package de.metalcon.middleware.controller.entity.impl.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.VenuesTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.CityView;

@Controller
public class CityController extends EntityController<CityView> {

    @Autowired
    private CityAboutTabGenerator aboutTabGenerator;

    @Autowired
    private CityBandsTabGenerator bandsTabGenerator;

    @Autowired
    private CityEventsTabGenerator eventsTabGenerator;

    @Autowired
    private CityNewsTabGenerator newsTabGenerator;

    @Autowired
    private CityPhotosTabGenerator photosTabGenerator;

    @Autowired
    private CityRecommendationsTabGenerator recommendationsTabGenerator;

    @Autowired
    private CityUsersTabGenerator usersTabGenerator;

    @Autowired
    private CityVenuesTabGenerator venuesTabGenerator;

    public CityController() {
        super(EntityType.CITY, CityView.class);
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
    public UsersTabGenerator getUsersTabGenerator() {
        return usersTabGenerator;
    }

    @Override
    public VenuesTabGenerator getVenuesTabGenerator() {
        return venuesTabGenerator;
    }

}
