package com.org.securityservice.domain.model.mockentity;

import com.org.securityservice.domain.model.UserRegion;
import com.org.securityservice.domain.model.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class MockUserEntity {

    private String id;
    private String username;
    private UserRegion region;
    private String displayName;
    private Set<UserRole> roles;

    public MockUserEntity(String id, String username, UserRegion region, String displayName, Set<UserRole> roles){
        this.id = id;
        this.username = username;
        this.region = region;
        this.displayName = displayName;
        this.roles = roles;
    }
}
