package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;

public class Instrument extends Entity {

    @Override
    public EntityType getEntityType() {
        return EntityType.INSTRUMENT;
    }

    public Instrument(
            Muid muid,
            String name) {
        super(muid, name);
    }

}
