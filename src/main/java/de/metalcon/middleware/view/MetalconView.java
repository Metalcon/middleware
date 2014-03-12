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

/**
 * basic Metalcon view (_site)<br>
 * (holdes PJAXR values)
 */
public abstract class MetalconView implements View {

    @Autowired
    private ViewResolver viewResolver;

    private View view;

    private String pjaxrNamespace = "";
    private int pjaxrMatching = 0;

    public MetalconView() {
        view = null;
    }

    @PostConstruct
    private void init() throws Exception {
        view = viewResolver.resolveViewName("_site", Locale.GERMANY);
    }

    @Override
    public final String getContentType() {
        return view.getContentType();
    }

    @Override
    public final void render(Map<String, ?> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>(model);
        m.put("view", this);
        view.render(m, request, response);
    }

    public String getType() {
        return "site";
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

}
