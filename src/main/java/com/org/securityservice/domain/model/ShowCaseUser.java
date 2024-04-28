package com.org.securityservice.domain.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class ShowCaseUser extends User {

    private String id;
    private String displayName;
    private UserRegion region;

    public ShowCaseUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id, String displayName, UserRegion region) {
        super(username, password, authorities);
        this.id = id;
        this.displayName = displayName;
        this.region = region;
    }
}
