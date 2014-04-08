package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.BandUrlData;

public class Band extends Entity<BandUrlData> {

    private String freeBaseId;

    @Override
    public EntityType getEntityType() {
        return EntityType.BAND;
    }

    public Band(
            Muid muid,
            String name) {
        super(muid, name);
    }

    public String getFreeBaseId() {
        return freeBaseId;
    }

    public void setFreeBaseId(String freeBaseId) {
        this.freeBaseId = freeBaseId;
    }

    @Override
    public BandUrlData getUrlData() {
        return new BandUrlData(getMuid(), getName());
    }

}
