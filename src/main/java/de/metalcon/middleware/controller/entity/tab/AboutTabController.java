package de.metalcon.middleware.controller.entity.tab;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.controller.RequestParameters;
import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public class AboutTabController extends EntityTabController {

    @Override
    public EntityTabType getEntityTabType() {
        return EntityTabType.ABOUT;
    }

    public EntityView handleGet(
            EntityController entityController,
            RequestParameters params)
            throws NoSuchRequestHandlingMethodException, RedirectException {
        return entityController.handleTab(getEntityTabType(), params);
    }

}
