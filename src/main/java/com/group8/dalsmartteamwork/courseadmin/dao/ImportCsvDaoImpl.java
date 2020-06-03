package com.group8.dalsmartteamwork.courseadmin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group8.dalsmartteamwork.utils.User;
import com.group8.dalsmartteamwork.utils.DbConnection;
import com.group8.dalsmartteamwork.utils.Encryption;
import com.group8.dalsmartteamwork.utils.Mail;

public class ImportCsvDaoImpl implements ImportCsvDao {

    @Override
    public Boolean isUserInDb(String id){
        try {
            DbConnection dbConnection = new DbConnection();
            String selectQuery = String.format("SELECT * FROM Users where BannerId='%s'", id);
            ResultSet resultSet = dbConnection.getRecords(selectQuery);
            Boolean status = resultSet.next();
            dbConnection.close();
            return status;
        }
        catch (Exception e){
            //TODO: Add to Log
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addUserToDb(User user){
        try {
            DbConnection dbConnection = new DbConnection();
            String insertQuery = String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s', '%s')",
                    user.getId(), user.getLastName(), user.getFirstName(), user.getEmail(), user.getPassword());
            dbConnection.updateRecords(insertQuery);
            dbConnection.close();
        }
        catch (Exception e){
            //TODO: Add to Log
            e.printStackTrace();
        }
    }




}