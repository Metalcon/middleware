package de.metalcon.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

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
import de.metalcon.like.api.Direction;
import de.metalcon.like.api.Vote;
import de.metalcon.like.api.requests.LikeServerAddRelationRequest;
import de.metalcon.like.api.requests.LikeServerGetLikesRequest;
import de.metalcon.like.api.responses.LikeServerMuidListResponse;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.UserLogin;

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
        final Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setUserLogin(userLogin);

        beforeRequest(data);

        // Uid
        ModelMap model = new ModelMap();
        model.addAttribute("likemessage", serializedUid);
        model.addAttribute("uidSerialized", serializedUid);

        afterRequest(data);

        return new ModelAndView("like", model);
    }

    public ModelAndView up(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {
        final Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setUserLogin(userLogin);

        Muid userID = data.getUserLogin().getMuid();
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

        final Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setUserLogin(userLogin);

        Muid userID = data.getUserLogin().getMuid();
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

        final Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setUserLogin(userLogin);

        Muid userID = data.getUserLogin().getMuid();
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
                new LikeServerAddRelationRequest(userID, userID, vote), null);
    }

    public static LikeData getLikeCounts(
            final Dispatcher dispatcher,
            final Uid uid) {
        final LikeData likeData = new LikeData();
        LikeServerGetLikesRequest req =
                new LikeServerGetLikesRequest(uid, Direction.BOTH, Vote.UP);
        dispatcher.execute(req, new Callback<LikeServerMuidListResponse>() {

            @Override
            public void onSuccess(LikeServerMuidListResponse reply) {
                int num =
                        reply.getRawUids() == null
                                ? 0
                                : reply.getRawUids().length;
                likeData.setUpVoteNum(num);
            }
        });

        req = new LikeServerGetLikesRequest(uid, Direction.BOTH, Vote.DOWN);
        dispatcher.execute(req, new Callback<LikeServerMuidListResponse>() {

            @Override
            public void onSuccess(LikeServerMuidListResponse reply) {
                int num =
                        reply.getRawUids() == null
                                ? 0
                                : reply.getRawUids().length;
                likeData.setDownVoteNum(num);
            }
        });
        return likeData;
    }

}
