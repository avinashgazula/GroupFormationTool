package com.group8.dalsmartteamwork.courseadmin.models;

import com.group8.dalsmartteamwork.courseadmin.Pair;
import com.group8.dalsmartteamwork.accesscontrol.User;

import java.util.List;

public interface MakePairService {
    List<Pair<User, Boolean>> getUserDetails(List<User> users, List<Boolean> status);
}
