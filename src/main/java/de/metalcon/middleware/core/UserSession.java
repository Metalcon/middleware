package de.metalcon.middleware.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
