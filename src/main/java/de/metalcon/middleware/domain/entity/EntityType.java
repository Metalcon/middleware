package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.MuidType;

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
    BAND("band"),

    /**
     * city
     */
    CITY("city"),

    /**
     * event hosted in a venue
     */
    EVENT("event"),

    /**
     * metal genre
     */
    GENRE("genre"),

    /**
     * music instrument
     */
    INSTRUMENT("instrument"),

    /**
     * record of a band
     */
    RECORD("record"),

    /**
     * collection of events
     */
    TOUR("tour"),

    /**
     * record track
     */
    TRACK("track"),

    /**
     * user
     */
    USER("user"),

    /**
     * venue located in a city
     */
    VENUE("venue");

    private String identifier;

    private EntityType(
            String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    public static EntityType fromMuidType(MuidType muidType) {
        switch (muidType) {
        //@formatter:off
            case BAND:       return EntityType.BAND;
            case CITY:       return EntityType.CITY;
            case EVENT:      return EntityType.EVENT;
            case GENRE:      return EntityType.GENRE;
            case INSTRUMENT: return EntityType.INSTRUMENT;
            case RECORD:     return EntityType.RECORD;
            case TOUR:       return EntityType.TOUR;
            case TRACK:      return EntityType.TRACK;
            case USER:       return EntityType.USER;
            case VENUE:      return EntityType.VENUE;
            //@formatter:on
            default:
                throw new IllegalArgumentException("Given MuidType \""
                        + muidType + "\" is not convertible to EntityType.");
        }
    }

    public static MuidType toMuidType(EntityType entityType) {
        switch (entityType) {
        //@formatter:off
            case BAND:       return MuidType.BAND;
            case CITY:       return MuidType.CITY;
            case EVENT:      return MuidType.EVENT;
            case GENRE:      return MuidType.GENRE;
            case INSTRUMENT: return MuidType.INSTRUMENT;
            case RECORD:     return MuidType.RECORD;
            case TOUR:       return MuidType.TOUR;
            case TRACK:      return MuidType.TRACK;
            case USER:       return MuidType.USER;
            case VENUE:      return MuidType.VENUE;
            //@formatter:on
            default:
                throw new IllegalStateException(
                        "Unimplemented EntityType to MuidType conversion for: \""
                                + entityType + "\".");
        }
    }

}
