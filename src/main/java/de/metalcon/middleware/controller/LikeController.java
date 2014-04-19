package de.metalcon.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.domain.Muid;
import de.metalcon.domain.Uid;
import de.metalcon.exceptions.ServiceOverloadedException;
import de.metalcon.like.api.Vote;
import de.metalcon.like.api.requests.LikeServerAddRelationRequest;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.core.UserSession;

@Controller
public class LikeController extends BaseController {

    @SuppressWarnings("unused")
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private DispatcherFactory dispatcherFactory;

    public LikeController() {
    }

    /**
     * View for external web sites
     * 
     * @param httpServletRequest
     * @param httpServletResponse
     * @param userLogin
     * @param serializedUid
     * @return
     */
    public ModelAndView showLikeButton(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {

        ModelMap model = new ModelMap();
        return new ModelAndView("like", model);
    }

    public ModelAndView up(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {
        UserSession userSession = userSessionFactory.userSession();

        try {
            userSession.setMuid(userLogin);
        } catch (ServiceOverloadedException e) {
            // TODO: Show user that the Server is overloaded
        }

        Muid userID = userSession.getMuid();
        Muid entity = Muid.createFromID(serializedUid);

        requestAddLike(userID, entity, Vote.UP);

        ModelMap model = new ModelMap();
        return new ModelAndView("likeResponse", model);
    }

    public ModelAndView neutral(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {

        UserSession userSession = userSessionFactory.userSession();

        try {
            userSession.setMuid(userLogin);
        } catch (ServiceOverloadedException e) {
            // TODO: Show user that the Server is overloaded
        }

        Muid userID = userSession.getMuid();
        Muid entity = Muid.createFromID(serializedUid);

        requestAddLike(userID, entity, Vote.NEUTRAL);

        ModelMap model = new ModelMap();
        return new ModelAndView("likeResponse", model);
    }

    public ModelAndView down(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {

        UserSession userSession = userSessionFactory.userSession();

        try {
            userSession.setMuid(userLogin);
        } catch (ServiceOverloadedException e) {
            // TODO: Show user that the Server is overloaded
        }

        Muid userID = userSession.getMuid();
        Muid entity = Muid.createFromID(serializedUid);

        requestAddLike(userID, entity, Vote.DOWN);

        ModelMap model = new ModelMap();
        return new ModelAndView("likeResponse", model);
    }

    private void requestAddLike(
            final Muid userID,
            final Uid targetID,
            final Vote vote) {
        /*
         * Execute the write request. Ignore the response as it's anyway just a
         * RequestQueuedResponse
         */
        dispatcherFactory.dispatcher().execute(
                new LikeServerAddRelationRequest(userID, targetID, vote), null);
    }
}
