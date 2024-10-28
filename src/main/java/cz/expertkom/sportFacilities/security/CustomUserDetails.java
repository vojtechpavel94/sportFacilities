package cz.expertkom.sportFacilities.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final String role;

    public CustomUserDetails(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + role.toUpperCase());  // Namapauje ROLE_ADMIN or ROLE_USER podle db
    }

    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return username; }
}