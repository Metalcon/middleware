package de.metalcon.middleware.view.entity.tab.preview.impl;

import java.util.List;

import de.metalcon.middleware.sdd.record.RecordEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

public class RecordsTabPreview extends EntityTabPreview {

    private List<RecordEntry> records;

    public RecordsTabPreview() {
        super(EntityTabType.RECORDS);
    }

    public List<RecordEntry> getRecords() {
        return records;
    }

    public void setRecords(List<RecordEntry> records) {
        this.records = records;
    }

}
