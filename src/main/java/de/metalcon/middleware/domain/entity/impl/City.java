package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.CityUrlData;

public class City extends Entity<CityUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.CITY;
    }

    public City(
            Muid muid,
            String name) {
        super(muid, name);
    }

    @Override
    public CityUrlData getUrlData() {
        return new CityUrlData(getMuid(), getName());
    }

}
