package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.List;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.track.TrackEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.TracksTabPreview;

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

        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                tabContent.setTracks(getTracksContent(data.getPage()));
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

    protected abstract List<TrackEntry> getTracksContent(SddOutput page);

    protected abstract List<TrackEntry> getTracksPreview(SddOutput page);
}
