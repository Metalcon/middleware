package de.metalcon.middleware.controller;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.core.GlobalConstants;
import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.core.UserSessionFactory;
import de.metalcon.middleware.domain.Muid;
import de.metalcon.middleware.view.MetalconView;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";

    public String getMetalconNamespace() {
        return metalconNamespace;
    }

    @Autowired
    private UserSessionFactory userSessionFactory;

    public MetalconView handleRequest(
            MetalconView view,
            RequestParameters params) {

        UserSession user = prepareUserSession(params);
        view.setId(user.getMuid() + "");
        user.incPageCount();
        view.setPc(user.getPageCount() + "");
        return view;
    }

    /**
     * Checks if there is a global user session active and assigns the Muid of
     * the global session to the User Session bean which lives as a cached
     * object in the current http session
     * 
     * The global state lives for 180 days and is currently not renewed. upon
     * login this cookie should be renewed
     * 
     * also see bug: https://github.com/Metalcon/middleware/issues/10
     * 
     * @param params
     *            Request parameters of the http request containing httpRequest
     *            and httpResponse objects
     * @return the UserSession object of the usersession bean
     * 
     * @see MetalconController
     */
    private UserSession prepareUserSession(RequestParameters params) {
        UserSession user = userSessionFactory.getUserSession();
        Cookie globalState = null;

        // get the global state cookie
        for (Cookie c : params.getRequest().getCookies()) {
            if (c.getName().equals(GlobalConstants.GLOBAL_SESSION_COOKIE)) {
                globalState = c;
                break;
            }
        }
        // if it does not exist create one
        if (globalState == null) {
            globalState =
                    new Cookie(GlobalConstants.GLOBAL_SESSION_COOKIE,
                            (new Muid((long) (Math.random() * 1000000))
                                    .toString()));
            globalState.setMaxAge(3600 * 24 * 180);
            params.getResponse().addCookie(globalState);
        }

        // push the global state to the user session object which live through the local http session
        user.setMuid(new Muid(Long.parseLong(globalState.getValue())));
        return user;
    }
}
