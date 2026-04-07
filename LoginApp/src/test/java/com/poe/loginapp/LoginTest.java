package com.poe.loginapp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Login class using PoE requirement data.
 */
public class LoginTest {
    
    // This creates the connection to your Login.java file
    Login login = new Login();

    @Test
    public void testUsernameCorrect() {
        // PoE Test Data: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        // PoE Test Data: "kyle!!!!!!!"
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordComplexitySuccess() {
        // PoE Test Data: "Ch&&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordComplexityFailure() {
        // PoE Test Data: "password"
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneSuccess() {
        // PoE Test Data: +27838968976
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCellPhoneFailure() {
        // PoE Test Data: 08966553
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}