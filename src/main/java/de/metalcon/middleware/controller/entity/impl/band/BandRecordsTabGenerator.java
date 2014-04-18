package de.metalcon.middleware.controller.entity.impl.band;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.domain.entity.RecordsTabEntry;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.band.BandPage;
import de.metalcon.middleware.sdd.record.RecordEntry;

@Component
public class BandRecordsTabGenerator extends RecordsTabGenerator {

    @Override
    protected List<RecordsTabEntry> getRecordsContent(SddOutput page) {
        BandPage bandPage = (BandPage) page;
        List<RecordsTabEntry> records = new LinkedList<RecordsTabEntry>();
        for (RecordEntry record : bandPage.getRecords()) {
            RecordsTabEntry recordsTabEntry =
                    new RecordsTabEntry(record.getMuid());
            recordsTabEntry.setName(record.getName());
            recordsTabEntry.setReleaseYear(Integer.parseInt(record
                    .getReleaseYear()));
            records.add(recordsTabEntry);
        }
        return records;
    }

    @Override
    protected List<RecordEntry> getRecordsPreview(SddOutput page) {
        BandPage bandPage = (BandPage) page;
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
