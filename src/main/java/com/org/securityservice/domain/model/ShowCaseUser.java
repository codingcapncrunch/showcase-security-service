package com.org.securityservice.domain.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class ShowCaseUser implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String displayName;
    private UserRegion region;
    private Set<SimpleGrantedAuthority> authorities;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    private boolean isCredentialsExpired;
    private boolean isEnabled;

    public ShowCaseUser(String id, String username, String displayName, UserRegion region, Set<UserRole> roles, boolean isAccountLocked,
                        boolean isAccountExpired, boolean isCredentialsExpired, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.region = region;
        this.isAccountLocked = isAccountLocked;
        this.isAccountExpired = isAccountExpired;
        this.isCredentialsExpired = isCredentialsExpired;
        this.isEnabled = isEnabled;

        this.authorities = new HashSet<>();
        for (UserRole role : roles){
            this.authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
    }

    public ShowCaseUser(String id, String username, String password, Set<UserRole> roles, boolean isAccountLocked,
                        boolean isAccountExpired, boolean isCredentialsExpired, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAccountLocked = isAccountLocked;
        this.isAccountExpired = isAccountExpired;
        this.isCredentialsExpired = isCredentialsExpired;
        this.isEnabled = isEnabled;

        this.authorities = new HashSet<>();
        for (UserRole role : roles){
            this.authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
