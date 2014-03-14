package de.metalcon.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.core.UserSessionFactory;
import de.metalcon.middleware.view.MetalconView;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";

    public String getMetalconNamespace() {
        return metalconNamespace;
    }

    @Autowired
    private UserSessionFactory userSessionFactory;

    protected MetalconView handleRequest(
            MetalconView view,
            RequestParameters params) {
        UserSession user = userSessionFactory.getUserSession();
        view.setId(user.getId() + "");
        user.incPageCount();
        view.setPc(user.getPageCount() + "");
        return view;
    }

    protected MetalconView
        handleGet(MetalconView view, RequestParameters params) {
        return handleRequest(view, params);
    }

}
