package de.metalcon.middleware.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ViewFactory {

    @Bean
    @Scope("prototype")
    public LoginView createLoginView() {
        return new LoginView();
    }
}
