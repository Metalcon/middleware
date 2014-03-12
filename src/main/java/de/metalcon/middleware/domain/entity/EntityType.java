package de.metalcon.middleware.domain.entity;

/**
 * enumeration of all entity types<br>
 * (Metalcon objects having an own page)<br>
 * <br>
 * <ul>
 * <li>band</li>
 * <li>city</li>
 * <li>event</li>
 * <li>genre</li>
 * <li>instrument</li>
 * <li>record</li>
 * <li>tour</li>
 * <li>track</li>
 * <li>user</li>
 * <li>venue</li>
 * </ul>
 */
public enum EntityType {

    /**
     * band linked to a genre
     */
    BAND,

    /**
     * city
     */
    CITY,

    /**
     * event hosted in a venue
     */
    EVENT,

    /**
     * metal genre
     */
    GENRE,

    /**
     * music instrument
     */
    INSTRUMENT,

    /**
     * record of a band
     */
    RECORD,

    /**
     * collection of events
     */
    TOUR,

    /**
     * record track
     */
    TRACK,

    /**
     * user
     */
    USER,

    /**
     * venue located in a city
     */
    VENUE;

}
