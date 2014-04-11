package de.metalcon.middleware.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.core.UserSession;

/**
 * basic Metalcon view (_site)<br>
 * (holds PJAXR values)
 */
public abstract class BaseView implements View {

    @Autowired
    private ViewResolver viewResolver;

    private String name;

    private View view = null;

    private String pjaxrNamespace = "";

    private int pjaxrMatching = 0;

    protected UserSession userSession = null;

    protected UserLogin userLogin = null;

    public BaseView(
            String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    @PostConstruct
    private void init() throws Exception {
        view = viewResolver.resolveViewName("_base", Locale.GERMANY);
    }

    @Override
    public final String getContentType() {
        return view.getContentType();
    }

    @Override
    public final void render(
            Map<String, ?> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>(model);
        m.put("view", this);
        view.render(m, request, response);
    }

    public String getPjaxrNamespace() {
        return pjaxrNamespace;
    }

    public void setPjaxrNamespace(String pjaxrNamespace) {
        this.pjaxrNamespace = pjaxrNamespace;
    }

    public int getPjaxrMatching() {
        return pjaxrMatching;
    }

    public void setPjaxrMatching(int pjaxrMatching) {
        this.pjaxrMatching = pjaxrMatching;
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

    //// LEGACY TESTING CODE ///////////////////////////////////////////////////

    private String userId;

    private String pc;

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

}
