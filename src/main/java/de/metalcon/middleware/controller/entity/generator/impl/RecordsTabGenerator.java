package de.metalcon.middleware.controller.entity.generator.impl;

import java.util.List;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.domain.entity.RecordData;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.RecordsTabContent;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecordsTabPreview;
import de.metalcon.urlmappingserver.api.requests.ResolveMuidRequest;
import de.metalcon.urlmappingserver.api.responses.MuidResolvedResponse;

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

        final Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.promise(new Runnable() {

            @Override
            public void run() {
                List<RecordData> records =
                        getRecordsContent(data.getPage());
                for (final RecordData record : records) {
                    dispatcher.execute(
                            new ResolveMuidRequest(record.getMuid()),
                            new Callback<MuidResolvedResponse>() {

                                @Override
                                public void onSuccess(
                                        MuidResolvedResponse response) {
                                    record.setUrl(response.getUrl());
                                }

                            });
                }
                tabContent.setRecords(records);
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

    protected abstract List<RecordData> getRecordsContent(SddOutput page);

    protected abstract List<RecordEntry> getRecordsPreview(SddOutput page);

}
