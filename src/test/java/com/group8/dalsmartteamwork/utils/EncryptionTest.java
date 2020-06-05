package com.group8.dalsmartteamwork.utils;

import com.group8.dalsmartteamwork.utils.Encryption;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EncryptionTest {
    private static final String TEST_STRING = "HelloWorld123@";
    private static final String TEST_ENC_STRING = "N3UEE4Uf/MNu9uc3qpeY0g==";

    @Test
    public void encryptTest() {
        Encryption encryption = new Encryption();
        assertTrue(encryption.encrypt(TEST_STRING).equals(TEST_ENC_STRING));
    }

    @Test
    public void decryptTest() {
        Encryption encryption = new Encryption();
        assertTrue(encryption.decrypt(TEST_ENC_STRING).equals(TEST_STRING));
    }

    @Test
    public void encryptDecryptTest() {
        Encryption encryption = new Encryption();
        String encrypted_text = encryption.encrypt("HelloWorld123@");
        assertTrue(encryption.decrypt(encrypted_text).equals("HelloWorld123@"));
    }
}