package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.Arrays;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.record.RecordPage;
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
                switch (data.getEntityType()) {
                    case BAND:
                        BandPage bandPage = (BandPage) data.getPage();
                        tabContent.setTracks(Arrays.asList(bandPage.getTracks()));
                        break;

                    case RECORD:
                        RecordPage recordPage = (RecordPage) data.getPage();
                        tabContent.setTracks(Arrays.asList(recordPage
                                .getTracks()));
                        break;

                    default:
                }
            }

        }, data.getPageCallback());

        return tabContent;
    }

}
