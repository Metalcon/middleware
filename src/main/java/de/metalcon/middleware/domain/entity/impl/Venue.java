package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.VenueUrlData;

public class Venue extends Entity<VenueUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.VENUE;
    }

    private Muid city;

    public Venue(
            Muid muid,
            String name) {
        super(muid, name);
        city = null;
    }

    public Muid getCity() {
        return city;
    }

    public void setCity(Muid city) {
        this.city = city;
    }

    @Override
    public VenueUrlData getUrlData() {
        return new VenueUrlData(getMuid(), getName(), null);
    }

}
