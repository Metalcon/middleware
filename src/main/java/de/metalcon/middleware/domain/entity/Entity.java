package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.Identity;
import de.metalcon.urlmappingserver.api.requests.registration.EntityUrlData;

/**
 * basic class for Metalcon entities having an own page
 * 
 * @see EntityType
 */
public abstract class Entity<EntityUrlDataType extends EntityUrlData > extends
        Identity {

    /**
     * @return entity type
     */
    public abstract EntityType getEntityType();

    /**
     * human readable name
     */
    private String name;

    /**
     * create basic entity
     * 
     * @param muid
     *            entity identifier
     * @param name
     *            human readable name
     */
    public Entity(
            Muid muid,
            String name) {
        super(muid);
        setName(name);
    }

    /**
     * @return human readable name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            human readable name
     */
    public final void setName(String name) {
        this.name = name;
    }

    public abstract EntityUrlDataType getUrlData();

}
