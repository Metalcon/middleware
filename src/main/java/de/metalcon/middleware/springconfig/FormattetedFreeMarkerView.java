package de.metalcon.middleware.springconfig;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class FormattetedFreeMarkerView extends FreeMarkerView {

    @Override
    protected void processTemplate(
            Template template,
            SimpleHash model,
            HttpServletResponse response) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        String formattedTemplate = formatTemplate(writer.toString());
        response.getWriter().write(formattedTemplate);
    }

    protected abstract String formatTemplate(String template);

}
