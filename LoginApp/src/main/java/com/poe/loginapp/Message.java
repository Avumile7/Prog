package com.poe.loginapp;

import java.util.Random;

public class Message {
    
    // Arrays matching assignment criteria
    private String[] recipients = new String[100];
    private String[] textBodies = new String[100];
    private String[] messageIDs = new String[100];
    private String[] messageHashes = new String[100];
    private String[] messageFlags = new String[100]; // Tracks: Sent, Stored, Disregard
    private int messageCount = 0;

    public boolean checkMessageLength(String message) {
        return message != null && message.length() <= 250;
    }

    public String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String createMessageHash(String msgId, String message) {
        if (message == null || message.trim().isEmpty()) {
            return msgId.substring(0, 2) + ":0:";
        }
        String cleanMessage = message.trim();
        int length = cleanMessage.length();
        String[] words = cleanMessage.split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return msgId.substring(0, 2) + ":" + length + ":" + firstWord + lastWord;
    }

    public String addMessageToArray(String recipient, String textBody, String flag) {
        if (!checkMessageLength(textBody)) {
            return "Message could not be sent, please ensure message is less than 250 characters.";
        }
        if (messageCount >= 100) {
            return "Storage full!";
        }

        String uniqueID = generateMessageID();
        String uniqueHash = createMessageHash(uniqueID, textBody);

        recipients[messageCount] = recipient;
        textBodies[messageCount] = textBody;
        messageIDs[messageCount] = uniqueID;
        messageHashes[messageCount] = uniqueHash;
        messageFlags[messageCount] = flag; 
        messageCount++;

        return "Message successfully captured!";
    }

    // 2a & 2f: Display Full Details Report
    public void displayReport() {
        if (messageCount == 0) {
            System.out.println("No messages stored.");
            return;
        }
        System.out.println("\n--- MESSAGE REPORT ---");
        for (int i = 0; i < messageCount; i++) {
            if (messageIDs[i] != null) {
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + textBodies[i]);
                System.out.println("Hash: " + messageHashes[i]);
                System.out.println("Status: " + messageFlags[i]);
                System.out.println("-------------------------");
            }
        }
    }

    // 2b: Display the Longest Message
    public String displayLongestMessage() {
        String longest = "";
        for (int i = 0; i < messageCount; i++) {
            if (textBodies[i] != null && textBodies[i].length() > longest.length()) {
                longest = textBodies[i];
            }
        }
        return longest.isEmpty() ? "No messages found." : longest;
    }

    // 2c: Search by Message ID
    public String searchMessageByID(String id) {
        for (int i = 0; i < messageCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                return "Recipient: " + recipients[i] + "\nMessage: " + textBodies[i];
            }
        }
        return "Message ID not found.";
    }

    // 2d: Search All Messages for a Particular Recipient
    public void searchByRecipient(String recipientNumber) {
        boolean found = false;
        for (int i = 0; i < messageCount; i++) {
            if (recipients[i] != null && recipients[i].equals(recipientNumber)) {
                System.out.println("\nMessage: " + textBodies[i] + " [" + messageFlags[i] + "]");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for recipient: " + recipientNumber);
        }
    }

    // 2e: Delete Message Using Hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < messageCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                String deletedText = textBodies[i];
                messageIDs[i] = null;
                messageHashes[i] = null;
                textBodies[i] = null;
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
}