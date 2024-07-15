package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.mockentity.MockUserEntity;

import java.util.Collection;

public interface UserRepository {

    Collection<MockUserEntity> getAllUsers();

    MockUserEntity getUserByUsername(String email);

    MockUserEntity getUserById(String id);

    void deleteUserByUserId(String id);

    MockUserEntity addUser(MockUserEntity user);

    MockUserEntity updateUser(MockUserEntity user);

}
