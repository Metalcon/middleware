package de.metalcon.middleware.core;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.zeromq.ZMQ;

import de.metalcon.musicstreamingserver.api.requests.MusicStreamingRequest;
import de.metalcon.sdd.api.requests.SddRequest;
import de.metalcon.urlmappingserver.api.requests.UrlMappingRequest;

@Configuration
public class DispatcherFactory {

    //TODO: why are this fields public
    public static final String SDD_SERVICE = "staticDataDeliveryServer";

    public static final String SDD_ENDPOINT = "tcp://127.0.0.1:1337";

    public static final String MUSIC_STREAMING_SERVER_SERVICE =
            "musicStreamingServer";

    public static final String MUSIC_STREAMING_SERVER_ENDPOINT =
            "tcp://127.0.0.1:6666";

    public static final String URL_MAPPING_SERVICE = "urlMappingServer";

    public static final String URL_MAPPING_SERVER_ENDPOINT =
            "tcp://127.0.0.1:12666";

    @Bean(
            destroyMethod = "close")
    public ZMQ.Context zmqContext() {
        return ZMQ.context(1);
    }

    public static int i = 0;

    /**
     * Thread scope doesn't support destoryMethod. This means on every instance
     * we have to close the dispatcher ourselves, when we know its lifetime has
     * ended.
     */
    @Bean
    @Scope("thread")
    public Dispatcher dispatcher() {
        System.out.println("Creating dispatcher: " + ++i);
        Dispatcher dispatcher = new Dispatcher();
        registerAdapters(dispatcher);
        return dispatcher;
    }

    public Dispatcher dispatcherNoBean() {
        Dispatcher dispatcher = new Dispatcher();
        registerAdapters(dispatcher);
        return dispatcher;
    }

    private void registerAdapters(Dispatcher dispatcher) {
        // StaticDataDelivery
        //        ZmqAdapter sddAdapter = new ZmqAdapter(zmqContext(), SDD_ENDPOINT);
        dispatcher.registerService(SddRequest.class, SDD_ENDPOINT);
        //        dispatcher.registerServiceAdapter(SDD_SERVICE, sddAdapter);
        //        dispatcher.setDefaultService(SddReadRequest.class, SDD_SERVICE);
        //        dispatcher.setDefaultService(SddWriteRequest.class, SDD_SERVICE);

        // Music Streaming
        //        ZmqAdapter musicStreamingAdapter =
        //                new ZmqAdapter(zmqContext(), MUSIC_STREAMING_SERVER_ENDPOINT);
        dispatcher.registerService(MusicStreamingRequest.class,
                MUSIC_STREAMING_SERVER_ENDPOINT);
        //        dispatcher.registerServiceAdapter(MUSIC_STREAMING_SERVER_SERVICE,
        //                musicStreamingAdapter);
        //        dispatcher.setDefaultService(MusicStreamingDeleteRequest.class,
        //                MUSIC_STREAMING_SERVER_SERVICE);
        //        dispatcher.setDefaultService(
        //                MusicStreamingReadMusicItemMetaDataRequest.class,
        //                MUSIC_STREAMING_SERVER_SERVICE);
        //        dispatcher.setDefaultService(MusicStreamingReadMusicItemRequest.class,
        //                MUSIC_STREAMING_SERVER_SERVICE);
        //        dispatcher.setDefaultService(MusicStreamingCreateRequest.class,
        //                MUSIC_STREAMING_SERVER_SERVICE);
        //        dispatcher.setDefaultService(MusicStreamingUpdateRequest.class,
        //                MUSIC_STREAMING_SERVER_SERVICE);

        // UrlMapping
        //        ZmqAdapter urlMappingAdapter =
        //                new ZmqAdapter(zmqContext(), URL_MAPPING_SERVER_ENDPOINT);
        dispatcher.registerService(UrlMappingRequest.class,
                URL_MAPPING_SERVER_ENDPOINT);
        //        dispatcher.registerServiceAdapter(URL_MAPPING_SERVICE,
        //                urlMappingAdapter);
        //        dispatcher.setDefaultService(UrlMappingResolveRequest.class,
        //                URL_MAPPING_SERVICE);
        //        dispatcher.setDefaultService(UrlMappingRegistrationRequest.class,
        //                URL_MAPPING_SERVICE);
    }
}
