package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.TourUrlData;

public class Tour extends Entity<TourUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.TOUR;
    }

    public Integer year;

    public Tour(
            Muid muid,
            String name) {
        super(muid, name);
        year = null;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public TourUrlData getUrlData() {
        return new TourUrlData(getMuid());
    }

}
