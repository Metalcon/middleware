package de.metalcon.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.view.LoginView;

@Controller
public class LoginController extends BaseController {

    public LoginView mappingLogin(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal UserLogin userLogin) {
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
