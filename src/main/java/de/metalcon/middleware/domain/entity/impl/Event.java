package de.metalcon.middleware.domain.entity.impl;

import java.util.Date;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.EventUrlData;

public class Event extends Entity<EventUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.EVENT;
    }

    private Muid city;

    private Muid venue;

    private Date date;

    public Event(
            Muid muid,
            String name) {
        super(muid, name);
        city = null;
        venue = null;
        date = null;
    }

    public Muid getCity() {
        return city;
    }

    public void setCity(Muid city) {
        this.city = city;
    }

    public Muid getVenue() {
        return venue;
    }

    public void setVenue(Muid venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public EventUrlData getUrlData() {
        return new EventUrlData(getMuid());
    }

}
