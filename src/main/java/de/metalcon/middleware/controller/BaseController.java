package de.metalcon.middleware.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.exceptions.ServiceOverloadedException;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.view.BaseView;
import de.metalcon.middleware.view.ViewFactory;

public abstract class BaseController {

    public static class Data {

        private HttpServletRequest httpServletRequest;

        private HttpServletResponse httpServletResponse;

        private Map<String, String> pathVars;

        private BaseView view;

        private UserSession userSession;

        private UserLogin userLogin;

        private Dispatcher dispatcher;

        public HttpServletRequest getHttpServletRequest() {
            return httpServletRequest;
        }

        public void
            setHttpServletRequest(HttpServletRequest httpServletRequest) {
            this.httpServletRequest = httpServletRequest;
        }

        public HttpServletResponse getHttpServletResponse() {
            return httpServletResponse;
        }

        public void setHttpServletResponse(
                HttpServletResponse httpServletResponse) {
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

        public Dispatcher getDispatcher() {
            return dispatcher;
        }

        public void setDispatcher(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

    }

    protected static String METALCON_NAMESPACE = "metalcon";

    @Autowired
    protected DispatcherFactory dispatcherFactory;

    @Autowired
    protected ViewFactory viewFactory;

    @Autowired
    private UserSession.Factory userSessionFactory;

    protected void beforeRequest(Data data) {
        BaseView view = data.getView();
        UserSession userSession = userSessionFactory.userSession();
        view.setUserSession(userSession);
        view.setUserLogin(data.getUserLogin());

        try {
            userSession.setMuid(data.getUserLogin());
            data.setUserSession(userSession);
        } catch (ServiceOverloadedException e) {
            e.printStackTrace();
            // TODO: Show user that the Server is overloaded
        }
    }

    protected void afterRequest(Data data) {
        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.gatherResults(500);
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
