package de.metalcon.middleware.sdd.record;

import de.metalcon.middleware.sdd.SddOutput;

public class RecordEntry extends SddOutput {

    private String name;

    private String releaseYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

}
