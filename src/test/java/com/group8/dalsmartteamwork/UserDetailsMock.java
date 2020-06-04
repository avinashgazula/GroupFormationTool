package com.group8.dalsmartteamwork;

import com.group8.dalsmartteamwork.utils.IUser;

public class UserDetailsMock implements IUser {
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;

    public UserDetailsMock() {
        setToDefault();
    }

    public void setToDefault() {
        id = "B99999999";
        lastName = "TEST_LAST_NAME";
        firstName = "TEST_FIRST_NAME";
        email = "TEST_EMAIL@EMAIL.COM";
        password = "TEST_PASSWORD";
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}