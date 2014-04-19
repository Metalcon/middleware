package de.metalcon.middleware.controller.entity.impl.instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.domain.entity.EntityData;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.InstrumentView;

@Controller
public class InstrumentController extends EntityController<InstrumentView> {

    @Override
    protected EntityData createEntityDataObject(Data data) {
        EntityData rd =
                new EntityData(data.getDispatcher(), data.getUserSession()
                        .getMuid(), data.getMuid());
        return rd;
    }

    @Autowired
    private InstrumentAboutTabGenerator aboutTabGenerator;

    @Autowired
    private InstrumentNewsTabGenerator newsTabGenerator;

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
