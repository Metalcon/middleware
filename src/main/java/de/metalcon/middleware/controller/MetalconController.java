package de.metalcon.middleware.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
            HttpServletRequest request) {
        Cookie globalState = null;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("GLOBAL_STATE")) {
                System.out.println("found ya");
                globalState = c;
            }
            System.out.println(c.getDomain() + "\t" + c.getMaxAge() + "\t"
                    + c.getName() + "\t" + c.getPath() + "\t" + c.getValue());

        }
        if (globalState == null) {
            globalState =
                    new Cookie("GLOBAL_STATE", (new Muid(
                            (long) (Math.random() * 1000000)).toString()));
            globalState.setMaxAge(3600 * 24 * 180);
            // TODO: set cookie in response
        }

        UserSession user = userSessionFactory.getUserSession();
        mcView.setId(user.getId() + "");
        user.incPageCount();
        mcView.setPc(user.getPageCount() + "");
        return mcView;
    }
}
