package de.metalcon.middleware.controller.entity.tab;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.backend.newsfeedserver.NewsFeedServer;
import de.metalcon.middleware.controller.RequestParameters;
import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.domain.Muid;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public class NewsfeedTabController extends EntityTabController {

    @Autowired
    private NewsFeedServer nfs;

    @Override
    public EntityTabType getEntityTabType() {
        return EntityTabType.NEWSFEED;
    }

    public EntityView handleGet(
            EntityController entityController,
            RequestParameters params)
            throws NoSuchRequestHandlingMethodException, RedirectException {
        return entityController.handleTab(getEntityTabType(), params);
    }

    public EntityView handlePost(
            EntityController entityController,
            RequestParameters params)
            throws NoSuchRequestHandlingMethodException, RedirectException,
            IOException {
        Muid muid =
                entityController.getMuidAndCheck404(getEntityTabType(), params);

        nfs.postNews(muid.toString(), muid.toString(), true,
                "TODO: get form messages");

        throw new RedirectException("");
    }

}
