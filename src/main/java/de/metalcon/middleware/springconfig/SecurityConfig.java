package de.metalcon.middleware.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import de.metalcon.middleware.core.GlobalConstants;
import de.metalcon.middleware.core.UserLoginService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        //@formatter:off
        auth
            .eraseCredentials(true)
            .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
        //@formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off
        web
            .ignoring()
                .antMatchers(GlobalConstants.RESOURCE_PATH_ANT);
        //@formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .and()
            .logout()
                .deleteCookies(GlobalConstants.SESSION_COOKIE_NAME)
                .logoutUrl("/logout").permitAll()
                .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices())
                .tokenValiditySeconds(GlobalConstants.REMEMBERME_VALIDITY_SECONDS)
                .key(GlobalConstants.REMEMBERME_KEY);
        //@formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserLoginService();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices(
                        GlobalConstants.REMEMBERME_KEY, userDetailsService(),
                        new InMemoryTokenRepositoryImpl());
        rememberMeServices
                .setCookieName(GlobalConstants.REMEMBERME_COOKIE_NAME);
        rememberMeServices.setParameter(GlobalConstants.REMEMBERME_PARAMETER);
        rememberMeServices
                .setTokenValiditySeconds(GlobalConstants.REMEMBERME_VALIDITY_SECONDS);
        return rememberMeServices;
    }
}
