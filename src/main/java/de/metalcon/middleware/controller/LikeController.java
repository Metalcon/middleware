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

    public ModelAndView showLikeButton(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {
        Data data = new Data();
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

        // Uid
        ModelMap model = new ModelMap();
        model.addAttribute("uidSerialized", serializedUid);
        model.addAttribute("likemessage", "Performed like up");

        return new ModelAndView("like", model);
    }

    public ModelAndView neutral(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {

        // Uid
        ModelMap model = new ModelMap();
        model.addAttribute("uidSerialized", serializedUid);
        model.addAttribute("likemessage", "Performed like neutral");

        return new ModelAndView("like", model);
    }

    public ModelAndView down(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin,
            @PathVariable("uid") final String serializedUid) {

        // Uid
        ModelMap model = new ModelMap();
        model.addAttribute("uidSerialized", serializedUid);
        model.addAttribute("likemessage", "Performed like down");

        return new ModelAndView("like", model);
    }

    //    public LoginView showLikeButton2(
    //            final HttpServletRequest httpServletRequest,
    //            final HttpServletResponse httpServletResponse,
    //            @AuthenticationPrincipal final UserLogin userLogin) {
    //        Data data = new Data();
    //        data.setHttpServletRequest(httpServletRequest);
    //        data.setHttpServletResponse(httpServletResponse);
    //        data.setUserLogin(userLogin);
    //
    //        LoginView view = viewFactory.createLoginView();
    //        data.setView(view);
    //
    //        beforeRequest(data);
    //
    //        afterRequest(data);
    //
    //        return view;
    //    }

}
