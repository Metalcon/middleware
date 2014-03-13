package de.metalcon.middleware.core;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * user session holding all relevant data from user and his social network
 */
@Configuration
public class UserSessionFactory {
    
    @Bean
    @Scope("globalSession")
    public UserSession getUserSession(){
        return new UserSession();
    }
}
