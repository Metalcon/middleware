package de.metalcon.middleware.springconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import de.metalcon.middleware.core.JsonViewResolver;

@Configuration
@ComponentScan("de.metalcon.middleware")
public class WebConfig extends RouterConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);

        configurer.replaceMediaTypes(mediaTypes).favorPathExtension(true)
                .useJaf(false).defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(freeMarkerViewResolver());
        resolvers.add(jsonViewResolver());

        ContentNegotiatingViewResolver resolver =
                new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(100 * 1024 * 1024);
        return cmr;
    }

    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setContentType(MediaType.TEXT_HTML_VALUE);
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }

    public JsonViewResolver jsonViewResolver() {
        JsonViewResolver viewResolver = new JsonViewResolver();
        return viewResolver;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        return taskExecutor;
    }

    @Override
    public List<String> listRouteFiles() {
        List<String> routeFiles = new ArrayList<String>();
        routeFiles.add("routes.conf");
        return routeFiles;
    }

}
