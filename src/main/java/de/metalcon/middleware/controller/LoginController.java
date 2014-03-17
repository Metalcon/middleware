package de.metalcon.middleware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.core.UserSessionFactory;
import de.metalcon.middleware.exception.RedirectException;

@Controller
@SessionAttributes("loginFormBean")
public class LoginController extends MetalconController {

    @Autowired
    private UserSessionFactory userSessionFactory;

    // Invoked initially to create the "form" attribute
    // Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
    @ModelAttribute("loginFormBean")
    public LoginFormBean createFormBean() {
        return new LoginFormBean();
    }

    public final String handleLoginPost(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid LoginFormBean loginFormBean) throws RedirectException,
            NoSuchRequestHandlingMethodException {

        /**
         * assume for now the login is always successfull
         */
        //FIXME: need to integrate proper login

        UserSession user = userSessionFactory.getUserSession();
        if (user.isLoggedIn() == false) {
            user.setLoggedIn(true);
        }
        return "/";
    }
}
