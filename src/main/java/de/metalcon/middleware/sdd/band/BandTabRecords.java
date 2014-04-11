package de.metalcon.middleware.sdd.band;

import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.sdd.record.RecordEntry;

public class BandTabRecords extends SddOutput {

    private RecordEntry[] records;

    public RecordEntry[] getRecords() {
        return records;
    }

}
