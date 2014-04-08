package de.metalcon.middleware.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.domain.Muid;

/**
 * user session holding all relevant data from user and his social network
 */
public class UserSession {

    @Configuration
    public static class Factory {

        @Bean
        @Scope("session")
        public UserSession userSession() {
            return new UserSession();
        }

    }

    private Muid muid;

    private boolean loggedIn;

    public void setMuid(Muid muid) {
        this.muid = muid;
    }

    public Muid getMuid() {
        return muid;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    //// LEGACY TESTING CODE ///////////////////////////////////////////////////

    private int id = -1;

    private int pagecount = 0;

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

}
