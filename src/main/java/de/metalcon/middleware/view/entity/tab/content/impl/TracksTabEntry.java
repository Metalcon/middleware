package de.metalcon.middleware.view.entity.tab.content.impl;

import de.metalcon.domain.Muid;

public class TracksTabEntry {

    private Muid muid;

    private String name;

    private String url;

    private int trackNumber;

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

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

}
