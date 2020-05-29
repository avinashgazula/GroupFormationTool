package com.group8.dalsmartteamwork;

import com.group8.dalsmartteamwork.utils.MySQLConnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class MySQLConnectionTest {

    @Test
    public void defaultConstructorTest() {
        MySQLConnection connection = new MySQLConnection();
        assertNotNull(connection.getStatement());
    }

    @Test
    public void updateRecordsTest() {
        MySQLConnection connection = new MySQLConnection();
        Random rand = new Random();
        String id = Integer.toString(rand.nextInt(1000000));
        String query = String
                .format("INSERT INTO Users VALUES ('%s', 'Avinash', 'Gazula', 'avinash@gmail.com', 'password')", id);
        int noRecords = connection.updateRecords(query);
        assertTrue(noRecords == 1);
    }

}