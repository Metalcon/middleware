package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.List;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.domain.entity.TrackData;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.TracksTabPreview;
import de.metalcon.urlmappingserver.api.requests.ResolveMuidRequest;
import de.metalcon.urlmappingserver.api.responses.MuidResolvedResponse;

@Component
public abstract class TracksTabGenerator extends
        EntityTabGenerator<TracksTabContent, TracksTabPreview> {

    public TracksTabGenerator() {
        super(EntityTabType.TRACKS);
    }

    @Override
    public TracksTabContent
        generateTabContent(final EntityController.Data data) {
        final TracksTabContent tabContent = super.generateTabContent(data);

        final Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                List<TrackData> tracks = getTracksContent(data.getPage());
                for (final TrackData track : tracks) {
                    dispatcher.execute(new ResolveMuidRequest(track.getMuid()),
                            new Callback<MuidResolvedResponse>() {

                                @Override
                                public void onSuccess(
                                        MuidResolvedResponse response) {
                                    track.setUrl(response.getUrl());
                                }

                            });
                }
                tabContent.setTracks(tracks);
            }

        }, data.getPageCallback());

        return tabContent;
    }

    @Override
    public TracksTabPreview
        generateTabPreview(final EntityController.Data data) {
        final TracksTabPreview tabPreview = super.generateTabPreview(data);

        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                tabPreview.setTracks(getTracksPreview(data.getPage()));
            }

        }, data.getPageCallback());

        return tabPreview;
    }

    protected abstract List<TrackData> getTracksContent(SddOutput page);

    protected abstract List<TrackEntry> getTracksPreview(SddOutput page);
}
