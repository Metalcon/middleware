package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.domain.data.LikeData;

/**
 * basic identity class for Metalcon objects that can be identified via a MUID<br>
 * (and therefore is accessible via an URL)<br>
 * 
 * @see EntityType
 */
public abstract class Identity {

    /**
     * object identifier
     */
    private Muid muid;

    /**
     * Name to be printed on the web page
     */
    private String name;

    /**
     * URL to this identity
     */
    private String url;

    /**
     * Like statistics of this entity
     */
    private LikeData likeData;

    /**
     * create basic identify class
     * 
     * @param muid
     *            object identifier
     */
    public Identity(
            Muid muid) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LikeData getLikeData() {
        return likeData;
    }

    public void setLikeData(LikeData likeData) {
        this.likeData = likeData;
    }
}
