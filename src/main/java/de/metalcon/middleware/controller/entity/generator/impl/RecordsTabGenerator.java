package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.Arrays;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.RecordsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecordsTabPreview;

@Component
public abstract class RecordsTabGenerator extends
        EntityTabGenerator<RecordsTabContent, RecordsTabPreview> {

    public RecordsTabGenerator() {
        super(EntityTabType.RECORDS);
    }

    @Override
    public RecordsTabContent
        generateTabContent(final EntityController.Data data) {
        final RecordsTabContent tabContent = super.generateTabContent(data);

        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                switch (data.getEntityType()) {
                    case BAND:
                        BandPage page = (BandPage) data.getPage();
                        tabContent.setRecords(Arrays.asList(page.getRecords()));
                        break;

                    default:
                }
            }

        }, data.getPageCallback());

        return tabContent;
    }

}
