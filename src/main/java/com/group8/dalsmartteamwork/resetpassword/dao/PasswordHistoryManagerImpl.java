package com.group8.dalsmartteamwork.resetpassword.dao;

import com.group8.dalsmartteamwork.resetpassword.models.IPasswordPolicy;
import com.group8.dalsmartteamwork.resetpassword.models.PasswordPolicy;
import com.group8.dalsmartteamwork.database.CallStoredProcedure;
import com.group8.dalsmartteamwork.login.model.Encryption;

import java.sql.ResultSet;

public class PasswordHistoryManagerImpl implements IPasswordHistoryManager {

    @Override
    public Boolean moveCurrentPassword(String bannerID) {
        CallStoredProcedure storedProcedure = null;
        try {
            storedProcedure = new CallStoredProcedure("spMoveCurrentPassword(?, ?)");
            IPasswordPolicy passwordPolicy = new PasswordPolicy();
            storedProcedure.setParameter(1, bannerID);
            storedProcedure.setParameter(2, passwordPolicy.getHistoricalPasswordLimit());
            storedProcedure.execute();
            return true;
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return false;
    }

    @Override
    public Boolean passwordExists(String bannerID, String password) {
        CallStoredProcedure storedProcedure = null;
        ResultSet rs;
        try {
            Encryption encryption = new Encryption();
            String encryptedPassword = encryption.encrypt(password);
            storedProcedure = new CallStoredProcedure("spGetPasswordHistory(?, ?)");
            storedProcedure.setParameter(1, bannerID);
            storedProcedure.setParameter(2, encryptedPassword);
            rs = storedProcedure.executeWithResults();

            while (rs.next()) {
                return true;
            }
            storedProcedure = new CallStoredProcedure("spGetCurrentPassword(?)");
            storedProcedure.setParameter(1, bannerID);
            rs = storedProcedure.executeWithResults();
            while (rs.next()) {
                if (encryptedPassword.equals(rs.getString("Password"))) {
                    return true;
                }
            }
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return false;
    }
}
