package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.List;

import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordEntry;
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
                tabContent.setRecords(getRecordsContent(data.getPage()));
            }

        }, data.getPageCallback());

        return tabContent;
    }

    @Override
    public RecordsTabPreview
        generateTabPreview(final EntityController.Data data) {
        final RecordsTabPreview tabPreview = super.generateTabPreview(data);

        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                tabPreview.setRecords(getRecordsPreview(data.getPage()));
            }

        }, data.getPageCallback());

        return tabPreview;
    }

    protected abstract List<RecordEntry> getRecordsContent(SddOutput page);

    protected abstract List<RecordEntry> getRecordsPreview(SddOutput page);

}
