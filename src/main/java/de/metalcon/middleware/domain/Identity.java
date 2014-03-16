package de.metalcon.middleware.domain;

import de.metalcon.middleware.domain.entity.EntityType;

/**
 * basic identity class for Metalcon objects that can be identified via a MUID<br>
 * (and therefore is accessible via an URL)<br>
 * <br>
 * object types:
 * <ul>
 * <li>photo</li>
 * <li>news item</li>
 * <li>each entity having an own page</li>
 * </ul>
 * 
 * @see EntityType
 */
public abstract class Identity {

    /**
     * object identifier
     */
    private Muid muid;

    /**
     * create basic identify class
     * 
     * @param muid
     *            object identifier
     */
    public Identity(Muid muid) {
        setMuid(muid);
    }

    /**
     * @return object identifier
     */
    public Muid getMuid() {
        return muid;
    }

    /**
     * @param muid
     *            object identifier
     */
    public void setMuid(Muid muid) {
        this.muid = muid;
    }

}
