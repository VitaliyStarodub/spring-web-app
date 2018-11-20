package com.starodub.dao;

import com.starodub.model.User;

import java.util.Optional;

public interface UserDao {

    void addUser(User user);

    Optional<User> getByEmail(String email);
}
