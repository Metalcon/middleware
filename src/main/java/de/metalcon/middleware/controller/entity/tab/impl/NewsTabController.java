package de.metalcon.middleware.controller.entity.tab.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.backend.newsserver.NewsServer;
import de.metalcon.middleware.controller.Request;
import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.tab.EntityTabController;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public class NewsTabController extends EntityTabController {

    @Autowired
    private NewsServer newsServer;

    public NewsTabController() {
        super(EntityTabType.NEWS);
    }

    public void createNewsItem(
            Request request,
            EntityController<?> entityController)
            throws NoSuchRequestHandlingMethodException, RedirectException,
            IOException {
        Muid muid =
                entityController.getMuidAndCheck404(getEntityTabType(),
                        request.getPathVars(), request.getHttpServletRequest());

        newsServer.postNews(muid.toString(), muid.toString(), true,
                "TODO: get form messages");

        throw new RedirectException("");
    }

}
