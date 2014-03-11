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

public abstract class MetalconView implements View {

    @Autowired
    private ViewResolver viewResolver;

    private View view;

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
    public final void render(
            Map<String, ?> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>(model);
        m.put("view", this);
        this.getMatchingPjaxrNamespace(request);
        m.put("pjaxr", this.getMatchingPjaxrNamespace(request));
        view.render(m, request, response);
    }

    public String getType() {
        return "site";
    }
    
    public String getPjaxrNamespace() {
    	return "metalcon";
    }
    
    public String getMatchingPjaxrNamespace(HttpServletRequest request) {
    	if (request.getHeader("X-PJAX") != null && request.getHeader("X-PJAX-NAMESPACE") != null) { 
    		System.out.println("GOT PJAXR-REQUEST");
    		return "yes";
    	}
    	return "no";
    }
    
}
