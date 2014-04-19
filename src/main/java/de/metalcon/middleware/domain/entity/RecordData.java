package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;

public class RecordData extends EntityData {

    private int releaseYear;

    public RecordData(
            Muid muid) {
        super(muid);
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
