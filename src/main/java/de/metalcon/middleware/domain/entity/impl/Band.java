package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;

public class Band extends Entity {

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

}
