package de.metalcon.middleware.controller.entity.impl.tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.TourView;

@Controller
public class TourController extends EntityController<TourView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        //        EventPage bp = (EventPage) data.getPage();

        EntityData rd =
                new EntityData(data.getDispatcher(), data.getUserSession()
                        .getMuid(), data.getMuid());
        return rd;
    }

    @Autowired
    private TourAboutTabGenerator aboutTabGenerator;

    @Autowired
    private TourNewsTabGenerator newsTabGenerator;

    public TourController() {
        super(EntityType.TOUR, TourView.class);
    }

    @Override
    public AboutTabGenerator getAboutTabGenerator() {
        return aboutTabGenerator;
    }

    @Override
    public NewsTabGenerator getNewsTabGenerator() {
        return newsTabGenerator;
    }

}
