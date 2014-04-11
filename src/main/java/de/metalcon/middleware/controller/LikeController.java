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
import de.metalcon.middleware.view.LikeView;
import de.metalcon.middleware.view.LoginView;

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

    public ModelAndView showLikeButton2(
            @PathVariable("Uid") final String serializedUid) {
        // Uid
        ModelMap model = new ModelMap();
        model.addAttribute("uid", serializedUid);

        LikeView view = viewFactory.createLikeView();

        return new ModelAndView("like", model);
    }

    public LoginView showLikeButton(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal final UserLogin userLogin) {
        Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setUserLogin(userLogin);

        LoginView view = viewFactory.createLoginView();
        data.setView(view);

        beforeRequest(data);

        afterRequest(data);

        return view;
    }

}
