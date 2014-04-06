package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.UidType;

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

    public static EntityType fromUidType(UidType uidType) {
        switch (uidType) {
        
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
                throw new IllegalArgumentException("Given UidType \""
                        + uidType + "\" is not convertible to EntityType.");
        }
    }

    public static UidType toUidType(EntityType entityType) {
        switch (entityType) {
        //@formatter:off
            case BAND:       return UidType.BAND;
            case CITY:       return UidType.CITY;
            case EVENT:      return UidType.EVENT;
            case GENRE:      return UidType.GENRE;
            case INSTRUMENT: return UidType.INSTRUMENT;
            case RECORD:     return UidType.RECORD;
            case TOUR:       return UidType.TOUR;
            case TRACK:      return UidType.TRACK;
            case USER:       return UidType.USER;
            case VENUE:      return UidType.VENUE;
            //@formatter:on
            default:
                throw new IllegalStateException(
                        "Unimplemented EntityType to UidType conversion for: \""
                                + entityType + "\".");
        }
    }

}
