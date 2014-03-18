package de.metalcon.middleware.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Helper object to pass request paramters around.
 */
public class RequestParameters {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String, String> pathVars;

    public RequestParameters(
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, String> pathVars) {
        this.request = request;
        this.response = response;
        this.pathVars = pathVars;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Map<String, String> getPathVars() {
        return pathVars;
    }

    public void setPathVars(Map<String, String> pathVars) {
        this.pathVars = pathVars;
    }

}
