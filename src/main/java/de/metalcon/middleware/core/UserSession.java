package de.metalcon.middleware.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.domain.Muid;
import de.metalcon.domain.UidType;
import de.metalcon.exceptions.ServiceOverloadedException;

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

    private int id = -1;

    private int pagecount = 0;

    private Muid muid;

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

    public Muid getMuid() {
        return muid;
    }

    public void setMuid(final UserLogin login)
            throws ServiceOverloadedException {
        if (login == null) {
            muid = Muid.create(UidType.USER);
        } else {
            muid = login.getMuid();
        }
    }
}
