package de.metalcon.middleware.core;

import net.hh.request_dispatcher.Dispatcher;
import net.hh.request_dispatcher.service_adapter.ZmqAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.zeromq.ZMQ;

import de.metalcon.api.responses.Response;
import de.metalcon.musicstreamingserver.api.requests.MusicStreamingRequest;
import de.metalcon.musicstreamingserver.api.requests.create.MusicStreamingCreateRequest;
import de.metalcon.musicstreamingserver.api.requests.delete.MusicStreamingDeleteRequest;
import de.metalcon.musicstreamingserver.api.requests.read.MusicStreamingReadMusicItemMetaDataRequest;
import de.metalcon.musicstreamingserver.api.requests.read.MusicStreamingReadMusicItemRequest;
import de.metalcon.musicstreamingserver.api.requests.update.MusicStreamingUpdateRequest;
import de.metalcon.sdd.api.requests.SddReadRequest;
import de.metalcon.sdd.api.requests.SddRequest;
import de.metalcon.sdd.api.requests.SddWriteRequest;
import de.metalcon.urlmappingserver.api.requests.UrlMappingRegistrationRequest;
import de.metalcon.urlmappingserver.api.requests.UrlMappingRequest;
import de.metalcon.urlmappingserver.api.requests.UrlMappingResolveRequest;

@Configuration
public class DispatcherFactory {

    //TODO: why are this fields public
    public static final String SDD_SERVICE = "staticDataDeliveryServer";

    public static final String MUSIC_STREAMING_SERVER_SERVICE =
            "musicStreamingServer";

    public static final String URL_MAPPING_SERVICE = "urlMappingServer";

    public static final String SDD_ENDPOINT = "tcp://127.0.0.1:1337";

    public static final String MUSIC_STREAMING_SERVER_ENDPOINT =
            "tcp://127.0.0.1:6666";

    public static final String URL_MAPPING_SERVER_ENDPOINT =
            "tcp://127.0.0.1:12666";

    @Bean(
            destroyMethod = "close")
    public ZMQ.Context zmqContext() {
        return ZMQ.context(1);
    }

    @Bean(
            destroyMethod = "close")
    public ZmqAdapter<SddRequest, Response> sddAdapter() {
        ZmqAdapter<SddRequest, Response> sddAdapter =
                new ZmqAdapter<SddRequest, Response>(zmqContext(), SDD_ENDPOINT);
        return sddAdapter;
    }

    @Bean(
            destroyMethod = "close")
    public ZmqAdapter<MusicStreamingRequest, Response> musicStreamingAdapter() {
        ZmqAdapter<MusicStreamingRequest, Response> musicStreamingAdapter =
                new ZmqAdapter<MusicStreamingRequest, Response>(zmqContext(),
                        MUSIC_STREAMING_SERVER_ENDPOINT);
        return musicStreamingAdapter;
    }

    @Bean(
            destroyMethod = "close")
    public ZmqAdapter<UrlMappingRequest, Response> urlMappingAdapter() {
        ZmqAdapter<UrlMappingRequest, Response> urlMappingAdapter =
                new ZmqAdapter<UrlMappingRequest, Response>(zmqContext(),
                        MUSIC_STREAMING_SERVER_ENDPOINT);
        return urlMappingAdapter;
    }

    @Bean(
            destroyMethod = "close")
    @Scope("thread")
    public Dispatcher getDispatcher() {
        Dispatcher dispatcher = new Dispatcher();

        // StaticDataDelivery
        dispatcher.registerServiceAdapter(SDD_SERVICE, sddAdapter());
        dispatcher.setDefaultService(SddReadRequest.class, SDD_SERVICE);
        dispatcher.setDefaultService(SddWriteRequest.class, SDD_SERVICE);

        // Music Streaming
        dispatcher.registerServiceAdapter(MUSIC_STREAMING_SERVER_SERVICE,
                musicStreamingAdapter());
        dispatcher.setDefaultService(MusicStreamingDeleteRequest.class,
                MUSIC_STREAMING_SERVER_SERVICE);
        dispatcher.setDefaultService(
                MusicStreamingReadMusicItemMetaDataRequest.class,
                MUSIC_STREAMING_SERVER_SERVICE);
        dispatcher.setDefaultService(MusicStreamingReadMusicItemRequest.class,
                MUSIC_STREAMING_SERVER_SERVICE);
        dispatcher.setDefaultService(MusicStreamingCreateRequest.class,
                MUSIC_STREAMING_SERVER_SERVICE);
        dispatcher.setDefaultService(MusicStreamingUpdateRequest.class,
                MUSIC_STREAMING_SERVER_SERVICE);

        // UrlMapping
        dispatcher.registerServiceAdapter(URL_MAPPING_SERVICE,
                urlMappingAdapter());
        dispatcher.setDefaultService(UrlMappingResolveRequest.class,
                URL_MAPPING_SERVICE);
        dispatcher.setDefaultService(UrlMappingRegistrationRequest.class,
                URL_MAPPING_SERVICE);

        return dispatcher;
    }
}
