package de.metalcon.middleware.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UserSessionFactory {

    @Bean
    @Scope("session")
    public UserSession getUserSession() {
        return new UserSession();
    }
}
