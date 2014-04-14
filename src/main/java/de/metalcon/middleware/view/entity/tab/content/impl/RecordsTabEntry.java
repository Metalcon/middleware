package de.metalcon.middleware.view.entity.tab.content.impl;

import de.metalcon.domain.Muid;

public class RecordsTabEntry {

    private Muid muid;

    private String name;

    private String url;

    private int releaseYear;

    public Muid getMuid() {
        return muid;
    }

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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
