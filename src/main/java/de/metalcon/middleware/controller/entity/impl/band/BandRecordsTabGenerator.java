package de.metalcon.middleware.controller.entity.impl.band;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.record.RecordEntry;

@Component
public class BandRecordsTabGenerator extends RecordsTabGenerator {

    @Override
    protected List<RecordEntry> getRecordsContent(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<RecordEntry> records = Arrays.asList(bandPage.getRecords());
        return records;
    }

    @Override
    protected List<RecordEntry> getRecordsPreview(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<RecordEntry> records = new LinkedList<RecordEntry>();

        int i = 0;
        for (RecordEntry record : bandPage.getRecords()) {
            records.add(record);
            if (++i == 5) {
                break;
            }
        }

        return records;
    }

}
