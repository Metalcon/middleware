package de.metalcon.middleware.core;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import de.metalcon.domain.Muid;

public class UserLogin extends User {

    private static final long serialVersionUID = 3836729362378666984L;

    private Muid muid;

    public UserLogin(
            Muid muid,
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.muid = muid;
    }

    public Muid getMuid() {
        return muid;
    }

}
