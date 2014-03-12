package de.metalcon.middleware.controller.entity.tab;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.tab.EntityTabType;

@Component
public class ReviewsTabController extends EntityTabController {

    @Override
    public EntityTabType getEntityTabType() {
        return EntityTabType.REVIEWS;
    }

    public EntityView handleGet(
            HttpServletRequest request,
            Map<String, String> pathVars)
            throws NoSuchRequestHandlingMethodException, RedirectException {
        return entityController
                .handleTab(getEntityTabType(), request, pathVars);
    }

}
