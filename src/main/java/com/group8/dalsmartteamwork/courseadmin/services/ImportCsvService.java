package com.group8.dalsmartteamwork.courseadmin.services;

import com.group8.dalsmartteamwork.utils.User;

import java.util.List;

public interface ImportCsvService {
    public List<Boolean> verifyRegistration(List<User> users);
}