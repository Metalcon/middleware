package de.metalcon.middleware.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * user session holding all relevant data from user and his social network
 */
@Configuration
public class UserSessionFactory {

    @Bean
    @Scope("session")
    public UserSession getUserSession() {
        return new UserSession();
    }
}
