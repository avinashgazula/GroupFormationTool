package com.group8.dalsmartteamwork.courseadmin.models;

import com.group8.dalsmartteamwork.utils.User;

import java.util.List;

public interface IStudentImportManager {
    public List<Boolean> verifyRegistration(List<User> users);
}