package com.poe.loginapp;

import java.util.Random;

public class Message {

    // Method to validate if the message length is under 250 characters
    public boolean checkMessageLength(String message) {
        return message != null && message.length() <= 250;
    }

    // Method to automatically generate a  random 10-digit numeric ID string
    public String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Custom method to structure the uppercase token format
    public String createMessageHash(String msgId, String message) {
        if (message == null || message.trim().isEmpty()) {
            return msgId.substring(0, 2) + ":0:";
        }
        
        String cleanMessage = message.trim();
        int length = cleanMessage.length();
        
        // Split the message by space boundaries to find text boundaries
        String[] words = cleanMessage.split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        // Format layout -> First Two Digits of ID : Length : Combined Words
        return msgId.substring(0, 2) + ":" + length + ":" + firstWord + lastWord;
    }

    // Main structural processor method to validate lengths and return the required layout
    public String processMessage(String recipient, String textBody) {
        if (!checkMessageLength(textBody)) {
            return "Message could not be sent, please ensure message is less than 250 characters.";
        }
        
        String uniqueID = generateMessageID();
        String uniqueHash = createMessageHash(uniqueID, textBody);
        
        return "Message successfully captured!\n" +
               "Message ID: " + uniqueID + "\n" +
               "Message Hash: " + uniqueHash;
    }
}
