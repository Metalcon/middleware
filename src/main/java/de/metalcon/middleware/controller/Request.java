package de.metalcon.middleware.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.view.BaseView;

public class Request {

    @Configuration
    public static class Factory {

        @Bean
        @Scope("request")
        public Request request() {
            return new Request();
        }

    }

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    private Map<String, String> pathVars;

    private BaseView view;

    @Autowired
    private UserSession userSession;

    private UserLogin userLogin;

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Map<String, String> getPathVars() {
        return pathVars;
    }

    public void setPathVars(Map<String, String> pathVars) {
        this.pathVars = pathVars;
    }

    public BaseView getView() {
        return view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

}
