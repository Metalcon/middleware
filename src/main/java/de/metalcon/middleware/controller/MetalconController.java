package de.metalcon.middleware.controller;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.UserSessionFactory;
import de.metalcon.middleware.view.MetalconView;
import de.metalcon.middleware.view.ViewFactory;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";

    public String getMetalconNamespace() {
        return metalconNamespace;
    }

    @Autowired
    private DispatcherFactory dispatcherFactory;

    @Autowired
    private UserSessionFactory userSessionFactory;

    @Autowired
    protected ViewFactory viewFactory;

    protected Dispatcher dispatcher() {
        return dispatcherFactory.getDispatcher();
    }

    protected void handleRequest(MetalconView view, RequestParameters params) {
        //UserSession user = prepareUserSession(params);
        //        view.setId(user.getMuid() + "");
        //        user.incPageCount();
        //        view.setPc(user.getPageCount() + "");

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
    }

    protected void handleGet(MetalconView view, RequestParameters params) {
        handleRequest(view, params);
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
