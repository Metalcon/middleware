package de.metalcon.middleware.core;

import de.metalcon.middleware.domain.Muid;

/**
 * user session holding all relevant data from user and his social network
 */
public class UserSession {

    private int id = -1;

    private int pagecount = 0;

    Muid muid;

    public int getId() {
        if (id < 0) {
            id = (int) (Math.random() * 100);
        }
        return id;
    }

    public int getPageCount() {
        return pagecount;
    }

    public void incPageCount() {
        pagecount++;
    }

    public void setMuid(Muid muid) {
        this.muid = muid;
    }

    public Muid getMuid() {
        return muid;
    }
}
