package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class RecordsTabContent extends EntityTabContent {

    private List<RecordsTabEntry> records;

    public RecordsTabContent() {
        super(EntityTabType.RECORDS);
    }

    public List<RecordsTabEntry> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsTabEntry> records) {
        this.records = records;
    }

}
