package de.metalcon.middleware.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PreDestroy;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.like.api.requests.LikeServerRequest;
import de.metalcon.middleware.options.DispatcherConfig;
import de.metalcon.musicstreamingserver.api.requests.MusicStreamingRequest;
import de.metalcon.recommendation.api.RecommendationRequest;
import de.metalcon.sdd.api.requests.SddRequest;
import de.metalcon.urlmappingserver.api.requests.UrlMappingRequest;

@Configuration
public class DispatcherFactory {

    private List<Dispatcher> dispatchers = Collections
            .synchronizedList(new LinkedList<Dispatcher>());

    @PreDestroy
    private void shutdownDispatchers() {
        System.out.println("Shutting down Dispatchers.");
        synchronized (dispatchers) {
            for (Dispatcher dispatcher : dispatchers) {
                dispatcher.shutdown();
            }
        }
    }

    /**
     * Thread scope doesn't support destoryMethod. This means on every instance
     * we have to close the dispatcher ourselves, when we know its lifetime has
     * ended.
     */
    @Bean
    @Scope("thread")
    public Dispatcher dispatcher() {
        System.out.println("Creating dispatcher: " + (dispatchers.size() + 1));
        Dispatcher dispatcher = new Dispatcher();
        dispatchers.add(dispatcher);
        registerAdapters(dispatcher);
        return dispatcher;
    }

    public Dispatcher dispatcherNoBean() {
        Dispatcher dispatcher = new Dispatcher();
        registerAdapters(dispatcher);
        return dispatcher;
    }

    private void registerAdapters(Dispatcher dispatcher) {
        dispatcher.registerService(SddRequest.class,
                DispatcherConfig.SDD_ENDPOINT);
        dispatcher.registerService(MusicStreamingRequest.class,
                DispatcherConfig.MUSIC_STREAMING_SERVER_ENDPOINT);
        dispatcher.registerService(UrlMappingRequest.class,
                DispatcherConfig.URL_MAPPING_SERVER_ENDPOINT);
        dispatcher.registerService(LikeServerRequest.class,
                DispatcherConfig.LIKE_SERVER_ENDPOINT);
        dispatcher.registerService(RecommendationRequest.class,
                DispatcherConfig.RECOMMENDATION_SERVER_ENDPOINT);
    }
}
