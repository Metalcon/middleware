package de.metalcon.middleware.sdd;

import de.metalcon.domain.Muid;

public abstract class SddOutput {

    private Muid muid;

    private String name;

    public Muid getMuid() {
        return muid;
    }

    public String getName() {
        return name;
    }

}
