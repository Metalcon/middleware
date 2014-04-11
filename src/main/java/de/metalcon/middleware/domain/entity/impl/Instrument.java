package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.InstrumentUrlData;

public class Instrument extends Entity<InstrumentUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.INSTRUMENT;
    }

    public Instrument(
            Muid muid,
            String name) {
        super(muid, name);
    }

    @Override
    public InstrumentUrlData getUrlData() {
        return new InstrumentUrlData(getMuid(), getName());
    }

}
