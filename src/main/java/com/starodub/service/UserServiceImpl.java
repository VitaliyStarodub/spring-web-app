package com.starodub.service;

import com.starodub.dao.UserDao;
import com.starodub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> getUserByMail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void addUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());

        user.setToken(getToken());
        user.setPassword(hashedPassword);
        userDao.addUser(user);
    }

    @Override
    public Optional<User> verifyPassword(User email, User user) {

        String hashedPassword = hashPassword(user.getPassword());

        return hashedPassword.equals(email.getPassword()) ? Optional.of(user) : Optional.empty();
    }


    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
        MessageDigest digest;
        byte[] encodedHash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(encodedHash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
