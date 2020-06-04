package com.group8.dalsmartteamwork.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.group8.dalsmartteamwork.utils.Props;

import org.junit.jupiter.api.Test;

public class PropsTest {
    private static final String TEST_KEY = "test.key";
    private static final String TEST_VALUE = "TEST";

    @Test
    public void defaultConstructorTest() {
        Props props = new Props();
        assertNotNull(props.getProperties());
    }

    @Test
    public void getValueTest() {
        Props props = new Props();
        assertTrue(props.getValue(TEST_KEY).equals(TEST_VALUE));
    }
}