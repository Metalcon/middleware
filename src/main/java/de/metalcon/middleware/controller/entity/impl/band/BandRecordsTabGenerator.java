package de.metalcon.middleware.controller.entity.impl.band;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.EntityController.Data;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.domain.entity.RecordData;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.record.RecordEntry;

@Component
public class BandRecordsTabGenerator extends RecordsTabGenerator {

    @Autowired
    protected DispatcherFactory dispatcherFactory;

    @Override
    protected List<RecordData> getRecordsContent(Data data) {
        BandPage bandPage = (BandPage) data.getPage();
        List<RecordData> records = new LinkedList<RecordData>();
        for (RecordEntry record : bandPage.getRecords()) {
            RecordData recordsTabEntry =
                    new RecordData(data.getDispatcher(), data.getUserSession()
                            .getMuid(), record.getMuid());
            recordsTabEntry.setName(record.getName());
            if (record.getReleaseYear() != null) {
                recordsTabEntry.setReleaseYear(Integer.parseInt(record
                        .getReleaseYear()));
            } else {
                recordsTabEntry.setReleaseYear(666);
            }
            records.add(recordsTabEntry);
        }
        return records;
    }

    @Override
    protected List<RecordEntry> getRecordsPreview(Data data) {
        BandPage bandPage = (BandPage) data.getPage();
        List<RecordEntry> records = new LinkedList<RecordEntry>();

        if (bandPage.getRecords() != null) {
            int i = 0;
            for (RecordEntry record : bandPage.getRecords()) {
                records.add(record);
                if (++i == 5) {
                    break;
                }
            }
        }

        return records;
    }

}
