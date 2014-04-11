package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.sdd.record.RecordEntry;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class RecordsTabContent extends EntityTabContent {

    private List<RecordEntry> records;

    public RecordsTabContent() {
        super(EntityTabType.RECORDS);
    }

    public List<RecordEntry> getRecords() {
        return records;
    }

    public void setRecords(List<RecordEntry> records) {
        this.records = records;
    }

}
