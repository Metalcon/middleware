package de.metalcon.middleware.core;

import net.hh.request_dispatcher.Dispatcher;
import net.hh.request_dispatcher.service_adapter.ZmqAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.zeromq.ZMQ;

import de.metalcon.api.responses.Response;
import de.metalcon.sdd.api.requests.SddReadRequest;
import de.metalcon.sdd.api.requests.SddRequest;
import de.metalcon.sdd.api.requests.SddWriteRequest;

@Configuration
public class DispatcherFactory {

    public static final String SDD_SERVICE = "staticDataDeliveryServer";

    public static final String SDD_ENDPOINT = "tcp://127.0.0.1:1337";

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
    @Scope("thread")
    public Dispatcher getDispatcher() {
        Dispatcher dispatcher = new Dispatcher();

        // StaticDataDelivery
        dispatcher.registerServiceAdapter(SDD_SERVICE, sddAdapter());
        dispatcher.setDefaultService(SddReadRequest.class, SDD_SERVICE);
        dispatcher.setDefaultService(SddWriteRequest.class, SDD_SERVICE);

        return dispatcher;
    }
}
