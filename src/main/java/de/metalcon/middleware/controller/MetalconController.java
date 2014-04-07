package de.metalcon.middleware.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.view.MetalconView;
import de.metalcon.middleware.view.ViewFactory;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";

    public String getMetalconNamespace() {
        return metalconNamespace;
    }

    @Autowired
    private Request.Factory requestFactory;

    @Autowired
    protected ViewFactory viewFactory;

    protected Request createRequest(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Map<String, String> pathVars,
            MetalconView view,
            UserLogin userLogin) {
        Request request = requestFactory.request();
        request.setHttpServletRequest(httpServletRequest);
        request.setHttpServletResponse(httpServletResponse);
        request.setPathVars(pathVars);
        request.setView(view);
        request.setUserLogin(userLogin);
        return request;
    }

    protected void handleRequest(Request request) {
        MetalconView view = request.getView();
        view.setUserSession(request.getUserSession());
        view.setUserLogin(request.getUserLogin());
        //UserSession user = prepareUserSession(params);
        //        view.setId(user.getMuid() + "");
        //        user.incPageCount();
        //        view.setPc(user.getPageCount() + "");
        //
        //        SddReadRequest read = new SddReadRequest();
        //        read.read(new de.metalcon.domain.Muid(7), "nested");
        //        dispatcher().execute(read, new Callback<Response>() {
        //
        //            @Override
        //            public void onSuccess(Response response) {
        //                if (response instanceof UsageErrorResponse) {
        //                    System.out.println(((UsageErrorResponse) response)
        //                            .getErrorMessage());
        //                } else if (response instanceof InternalServerErrorResponse) {
        //                    System.out.println(((InternalServerErrorResponse) response)
        //                            .getErrorMessage());
        //                } else if (response instanceof SddSucessfulReadResponse) {
        //                    for (Map.Entry<de.metalcon.domain.Muid, Map<String, String>> node : ((SddSucessfulReadResponse) response)
        //                            .get().entrySet()) {
        //                        System.out.println(node.getKey() + ":");
        //                        for (Map.Entry<String, String> output : node.getValue()
        //                                .entrySet()) {
        //                            System.out.println("  " + output.getKey() + "="
        //                                    + output.getValue());
        //                        }
        //                    }
        //                }
        //            }
        //
        //        });
        //
        //        dispatcher().gatherResults(700);
        request.getDispatcher().gatherResults(7);
    }

    protected void handleGet(Request request) {
        handleRequest(request);
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
    //    private UserSession prepareUserSession(RequestParameters params) {
    //        UserSession user = userSessionFactory.getUserSession();
    //        Cookie globalState = null;
    //
    //        // get the global state cookie
    //        for (Cookie c : params.getRequest().getCookies()) {
    //            if (c.getName().equals(GlobalConstants.GLOBAL_SESSION_COOKIE)) {
    //                globalState = c;
    //                break;
    //            }
    //        }
    //        // if it does not exist create one
    //        if (globalState == null) {
    //            globalState =
    //                    new Cookie(GlobalConstants.GLOBAL_SESSION_COOKIE,
    //                            (new Muid((long) (Math.random() * 1000000))
    //                                    .toString()));
    //            globalState.setMaxAge(3600 * 24 * 180);
    //            params.getResponse().addCookie(globalState);
    //        }
    //
    //        // push the global state to the user session object which live through the local http session
    //        user.setMuid(new Muid(Long.parseLong(globalState.getValue())));
    //        return user;
    //    }
}
