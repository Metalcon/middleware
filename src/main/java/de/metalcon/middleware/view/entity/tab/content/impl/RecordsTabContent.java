package de.metalcon.middleware.view.entity.tab.content.impl;

import java.util.List;

import de.metalcon.middleware.domain.entity.RecordData;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;

public class RecordsTabContent extends EntityTabContent {

    private List<RecordData> records;

    public RecordsTabContent() {
        super(EntityTabType.RECORDS);
    }

    public List<RecordData> getRecords() {
        return records;
    }

    public void setRecords(List<RecordData> records) {
        this.records = records;
    }

}
