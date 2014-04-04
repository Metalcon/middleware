package de.metalcon.middleware.springconfig;

import java.util.Locale;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonEncoding;

/**
 * resolves JSON views
 */
public class JsonViewResolver implements ViewResolver {

    /**
     * resolves JSON views by name
     * 
     * @return empty view for JSON output
     */
    @Override
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setContentType(MediaType.APPLICATION_JSON_VALUE);
        view.setEncoding(JsonEncoding.UTF8);
        view.setPrettyPrint(true);
        //view.setExposePathVariables(true);
        //view.setExtractValueFromSingleKeyModel(true);
        return view;
    }

}
