package com.group8.dalsmartteamwork.courseadmin.models;

import com.group8.dalsmartteamwork.utils.ICsvReader;
import com.group8.dalsmartteamwork.utils.User;

import java.util.List;

public class CsvParserImpl implements ICsvParser {
    private ICsvReader reader;

    public CsvParserImpl(ICsvReader reader) {
        this.reader = reader;
    }

    @Override
    public List<User> getUsers() {
        return reader.getUsers();
    }
}
