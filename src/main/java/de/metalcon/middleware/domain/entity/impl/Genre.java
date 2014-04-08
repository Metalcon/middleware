package de.metalcon.middleware.domain.entity.impl;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.urlmappingserver.api.requests.registration.GenreUrlData;

public class Genre extends Entity<GenreUrlData> {

    @Override
    public EntityType getEntityType() {
        return EntityType.GENRE;
    }

    public Genre(
            Muid muid,
            String name) {
        super(muid, name);
    }

    @Override
    public GenreUrlData getUrlData() {
        return new GenreUrlData(getMuid(), getName());
    }

}
