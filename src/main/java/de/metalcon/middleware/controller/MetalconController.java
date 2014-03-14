package de.metalcon.middleware.controller;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

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
            MetalconView mcView,
            RequestParameters params) {

        UserSession user = userSessionFactory.getUserSession();

        Cookie globalState = null;
        // get the global state cookie
        for (Cookie c : params.getRequest().getCookies()) {
            if (c.getName().equals("GLOBAL_STATE")) {
                globalState = c;
            }
            System.out.println(c.getMaxAge() + "\t" + c.getName() + "\t"
                    + c.getValue());
        }
        // if it does not exist create one
        if (globalState == null) {
            globalState =
                    new Cookie("GLOBAL_STATE", (new Muid(
                            (long) (Math.random() * 1000000)).toString()));
            globalState.setMaxAge(3600 * 24 * 180);
            params.getResponse().addCookie(globalState);
        }

        // push the global state to the user session object which live through the local http session
        user.setMuid(new Muid(Long.parseLong(globalState.getValue())));

        mcView.setId(user.getId() + "");
        user.incPageCount();
        mcView.setPc(user.getPageCount() + "");
        return mcView;
    }
}
