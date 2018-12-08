package com.starodub.controller.external.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starodub.model.User;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private String email;
    @JsonIgnore
    @NotNull
    private String password;
    @JsonIgnore
    private String verifiedPassword;
    private String token;
    private String firstName;
    private String lastName;


    public UserDto() {
    }

    public UserDto(String email, String password, String verifiedPassword, String token, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.verifiedPassword = verifiedPassword;
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getVerifiedPassword() {
        return verifiedPassword;
    }

    public void setVerifiedPassword(String verifiedPassword) {
        this.verifiedPassword = verifiedPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static UserDto of(User user) {
        UserDto userDto = new UserDto();
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setToken(user.getToken());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
