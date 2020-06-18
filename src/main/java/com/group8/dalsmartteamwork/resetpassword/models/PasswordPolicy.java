package com.group8.dalsmartteamwork.resetpassword.models;

import java.util.ArrayList;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class PasswordPolicy {
    private String minLength;
    private String maxLength;
    private String minUpper;
    private String minLower;
    private String minSymbols;
    private String charsNotAllowed;
    private String historyConstraint;

    public PasswordPolicy() {
        loadPolicy();
    }

    public Boolean loadPolicy() {
        this.minLength = System.getenv("password.minLength");
        this.maxLength = System.getenv("password.maxLenth");
        this.minUpper = System.getenv("password.minUpper");
        this.minLower = System.getenv("password.minLower");
        this.minSymbols = System.getenv("password.minSymbols");
        this.charsNotAllowed = System.getenv("password.charsNotAllowed");
        this.historyConstraint = System.getenv("password.historyConstraint");
        return true;
    }

    public String getMinLength() {
        return minLength;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public String getMinUpper() {
        return minUpper;
    }

    public String getMinLower() {
        return minLower;
    }

    public String getMinSymbols() {
        return minSymbols;
    }

    public String getCharsNotAllowed() {
        return charsNotAllowed;
    }

    public String getHistoryConstraint() {
        return historyConstraint;
    }

    public Boolean validate(String password) {

        int passwordLength = password.length();

        if (!this.minLength.equals("false")) {
            int minLength = Integer.parseInt(this.minLength);
            if (minLength >= passwordLength) {
                return false;
            }
        }

        if (!this.maxLength.equals("false")) {
            int maxLength = Integer.parseInt(this.maxLength);
            if (maxLength <= passwordLength) {
                return false;
            }
        }

        if (!this.minUpper.equals("false")) {
            int minUpper = Integer.parseInt(this.minUpper);
            int count = 0;
            for (int i = 0; i < passwordLength; i++) {
                if (isUpperCase(password.charAt(i))) {
                    ++count;
                }
            }
            if (count < minUpper) {
                return false;
            }
        }

        if (!this.minLower.equals("false")) {
            int minLower = Integer.parseInt(this.minLower);
            int count = 0;
            for (int i = 0; i < passwordLength; i++) {
                if (isLowerCase(password.charAt(i))) {
                    ++count;
                }
            }
            if (count < minLower) {
                return false;
            }
        }

        if (!this.minSymbols.equals("false")) {
            int minSymbols = Integer.parseInt(this.minSymbols);
            String symbols = "+-*@#~";
            int count = 0;
            for (int i = 0; i < passwordLength; i++) {
                if (symbols.contains(Character.toString(password.charAt(i)))) {
                    ++count;
                }
            }
            if (count < minSymbols) {
                return false;
            }
        }

        if (!this.charsNotAllowed.equals("false")) {
            String symbolsNotAllowed = this.charsNotAllowed;
            for (int i = 0; i < passwordLength; i++) {
                if (symbolsNotAllowed.contains(Character.toString(password.charAt(i)))) {
                    return false;
                }
            }
        }

        return true;
    }

    public ArrayList<String> generateErrorMessage() {
        ArrayList<String> errorMessages = new ArrayList<>();

        if (!this.minLength.equals("false")) {
            String errorMessage = "";
            errorMessage += "The password must contain atleast " + this.minLength + " characters.";
            errorMessages.add(errorMessage);
        }

        if (!this.maxLength.equals("false")) {
            String errorMessage = "";
            errorMessage += "The password must contain atmost " + this.maxLength + " characters.";
            errorMessages.add(errorMessage);
        }

        if (!this.minUpper.equals("false")) {
            String errorMessage = "";
            errorMessage += "The password must contain atleast " + this.minUpper + " Upper Case characters.";
            errorMessages.add(errorMessage);
        }

        if (!this.minLower.equals("false")) {
            String errorMessage = "";
            errorMessage += "The password must contain atleast " + this.minLower + " Lower Case characters.";
            errorMessages.add(errorMessage);
        }

        if (!this.minSymbols.equals("false")) {
            String errorMessage = "";
            errorMessage += "The password must contain atleast " + this.minSymbols + " special characters.";
            errorMessages.add(errorMessage);
        }

        if (!this.charsNotAllowed.equals("false")) {
            String errorMessage = "";
            errorMessage += "Using ";
            for (int i = 0; i < charsNotAllowed.length() - 1; i++) {
                errorMessage += charsNotAllowed.charAt(i) + ", ";
            }
            errorMessage += charsNotAllowed.charAt(charsNotAllowed.length() - 1) + " is not allowed.";
            errorMessages.add(errorMessage);
        }
        return errorMessages;
    }
}