package de.metalcon.middleware.controller.entity.impl.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsfeedTabGenerator;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.TourView;

@Controller
public class TourController extends EntityController<TourView> implements
        AboutTabGenerating, NewsfeedTabGenerating {

    @Autowired
    private TourAboutTabGenerator aboutTabGenerator;

    @Autowired
    private TourNewsfeedTabGenerator newsfeedTabGenerator;

    public TourController() {
        super(EntityType.TOUR, TourView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
    }

    @Override
    public NewsfeedTabGenerator getNewsfeedTabGenerator() {
        return newsfeedTabGenerator;
    }

}
