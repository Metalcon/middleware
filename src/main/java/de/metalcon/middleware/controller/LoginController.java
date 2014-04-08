package de.metalcon.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.view.LoginView;

@Controller
//@SessionAttributes("loginFormBean")
public class LoginController extends BaseController {

    //    @Autowired
    //    private UserSessionFactory userSessionFactory;
    //
    //    // Invoked initially to create the "form" attribute
    //    // Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
    //    @ModelAttribute("loginFormBean")
    //    public LoginFormBean createFormBean() {
    //        return new LoginFormBean();
    //    }
    //
    //    public final String handleLoginPost(
    //            HttpServletRequest request,
    //            HttpServletResponse response,
    //            @Valid LoginFormBean loginFormBean) throws RedirectException,
    //            NoSuchRequestHandlingMethodException {
    //
    //        /**
    //         * assume for now the login is always successful
    //         */
    //        //FIXME: need to integrate proper login
    //
    //        UserSession user = userSessionFactory.getUserSession();
    //        if (user.isLoggedIn() == false) {
    //            user.setLoggedIn(true);
    //        }
    //        return "/";
    //    }

    public LoginView mappingLogin(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @AuthenticationPrincipal UserLogin userLogin) {
        Request request = requestFactory.request();
        request.setHttpServletRequest(httpServletRequest);
        request.setHttpServletResponse(httpServletResponse);
        request.setUserLogin(userLogin);

        LoginView view = viewFactory.createLoginView();
        request.setView(view);

        handleGet();

        return view;
    }

}
