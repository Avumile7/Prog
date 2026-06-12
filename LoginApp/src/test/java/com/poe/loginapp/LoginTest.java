package com.poe.loginapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    // =========================================================================
    // 1. LOGIN CLASS TESTS
    // =========================================================================

    @Test
    public void testCheckUserNameSuccess() {
        Login auth = new Login();
        // Valid: Contains underscore and length <= 5
        assertTrue(auth.checkUserName("kyl_1")); 
    }

    @Test
    public void testCheckUserNameFailure() {
        Login auth = new Login();
        // Invalid: Too long and does not contain an underscore
        assertFalse(auth.checkUserName("johnsmith")); 
    }

    @Test
    public void testCheckPasswordComplexitySuccess() {
        Login auth = new Login();
        // Valid: >= 8 characters, capital letter, number, and special character
        assertTrue(auth.checkPasswordComplexity("Ch@llenge2026")); 
    }

    @Test
    public void testCheckPasswordComplexityFailure() {
        Login auth = new Login();
        // Invalid: Missing special characters and numbers
        assertFalse(auth.checkPasswordComplexity("password")); 
    }

    @Test
    public void testCheckCellPhoneNumberSuccess() {
        Login auth = new Login();
        // Valid: Starts with +27 followed by exactly 9 digits
        assertTrue(auth.checkCellPhoneNumber("+27812345678"));
    }

    @Test
    public void testCheckCellPhoneNumberFailure() {
        Login auth = new Login();
        // Invalid: Wrong format / missing international prefix
        assertFalse(auth.checkCellPhoneNumber("0812345678"));
    }

    @Test
    public void testRegisterUserSuccess() {
        Login auth = new Login();
        String result = auth.registerUser("kyl_1", "Ch@llenge2026", "Kylie", "Janse", "+27812345678");
        assertTrue(result.contains("Username successfully captured."));
    }

    @Test
    public void testLoginUserSuccess() {
        Login auth = new Login();
        // Setup registration state first
        auth.registerUser("kyl_1", "Ch@llenge2026", "Kylie", "Janse", "+27812345678");
        
        // Assert correct login details match match
        assertTrue(auth.loginUser("kyl_1", "Ch@llenge2026"));
    }

    // =========================================================================
    // 2. MESSAGE CLASS TESTS
    // =========================================================================

    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message();
        String validMessage = "This is a short message well under the 250 character limit.";
        assertTrue(msg.checkMessageLength(validMessage));
    }

    @Test
    public void testCheckMessageLengthFailure() {
        Message msg = new Message();
        // Generate an explicitly long string that exceeds 250 characters
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("x");
        }
        assertFalse(msg.checkMessageLength(longMsg.toString()));
    }

    @Test
    public void testGenerateMessageIDLengthAndFormat() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        
        assertNotNull(id);
        assertEquals(10, id.length());          // Must be exactly 10 characters
        assertTrue(id.matches("^[0-9]+$"));    // Must be completely numeric
    }

    @Test
    public void testCreateMessageHashFormat() {
        Message msg = new Message();
        String testId = "0598765432";
        String testText = "hello team quickchat message completed";
        
        // Layout expectation:
        // First 2 digits of ID: "05"
        // Length of raw text string: 37
        // First word ("hello") + Last word ("completed") in uppercase: "HELLOCOMPLETED"
        String expectedHash = "05:37:HELLOCOMPLETED";
        
        String actualHash = msg.createMessageHash(testId, testText);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testCreateMessageHashSingleWord() {
        Message msg = new Message();
        String testId = "9912345678";
        String testText = "Hello";
        
        // First 2 digits: "99"
        // Length: 5
        // First and last word are identical: "HELLOHELLO"
        String expectedHash = "99:5:HELLOHELLO";
        
        String actualHash = msg.createMessageHash(testId, testText);
        assertEquals(expectedHash, actualHash);
    }
}
@Test
    public void testSearchMessageByID_NotFound() {
        Message msg = new Message();
        // Searching an empty array system should return not found string
        String result = msg.searchMessageByID("9999999999");
        assertTrue(result.contains("not found"));
    }

    @Test
    public void testDeleteMessageByID_NotFound() {
        Message msg = new Message();
        String result = msg.deleteMessageByID("0000000000");
        assertTrue(result.contains("not found"));
    }