package com.starodub.service;

import com.starodub.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByMail(String email);

    void addUser(User user);

    Optional<User> verifyPassword(User email, User user);
}
