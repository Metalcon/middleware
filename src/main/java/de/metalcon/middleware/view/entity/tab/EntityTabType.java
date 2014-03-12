package de.metalcon.middleware.view.entity.tab;

/**
 * type of a tab on entity page
 */
public enum EntityTabType {

    EMPTY,

    /**
     * general information to entity
     */
    ABOUT,

    /**
     * bands
     * <ul>
     * <li>user likes</li>
     * <li>playing at event</li>
     * <li>located in a city</li>
     * <li>related to genre</li>
     * </ul>
     */
    BANDS,

    EVENTS,

    /**
     * news feed for entity
     */
    NEWSFEED,

    PHOTOS,

    RECOMMENDATIONS,

    RECORDS,

    REVIEWS,

    TRACKS,

    USERS,

    /**
     * venues in a city
     */
    VENUES;

}
