package de.metalcon.middleware.domain.entity;

import de.metalcon.domain.Muid;

public class RecordsTabEntry extends Identity {

    private int releaseYear;

    public RecordsTabEntry(
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
