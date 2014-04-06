package de.metalcon.middleware.core;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.metalcon.domain.Muid;

public class UserLoginService implements UserDetailsService {

    @Override
    public UserLogin loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // TODO: some sort of backend adaption

        Muid muid = null;
        String password = null;
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        switch (username) {
            case "user":
                muid = Muid.createFromID(1337L);
                password =
                        "$2a$10$nIjbnkK63WSdVe3QCQXxsODBZnIIYYJqWuaNQAd8bXHWSIzPX57cO";
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case "admin":
                muid = Muid.createFromID(42L);
                password =
                        "$2a$10$zAaRJ8ZZJIRnPChdbGg1ceT9jgxo93UQUkbLzHQfODMYG4cBbLgEe";
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;

            default:
                throw new UsernameNotFoundException(
                        "Could't find user with name: \"" + username + "\".");
        }

        return new UserLogin(muid, username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);
    }
}
