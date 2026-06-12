package com.poe.loginapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login auth = new Login();
        Message msgProcessor = new Message();

        // Hardcoded Part 3 Test Data Input
        msgProcessor.addMessageToArray("+27834557896", "Did you get the cake?", "Sent");
        msgProcessor.addMessageToArray("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        msgProcessor.addMessageToArray("+278344484567", "Yohoooo, I am at your gate.", "Disregard");
        msgProcessor.addMessageToArray("0838884567", "It is dinner time !", "Sent");
        msgProcessor.addMessageToArray("+27838884567", "Ok, I am leaving without you.", "Stored");

        System.out.println("--- Register ---");
        System.out.print("First Name: "); String fName = sc.nextLine();
        System.out.print("Last Name: "); String lName = sc.nextLine();
        System.out.print("Username: "); String user = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        System.out.print("Cell (+27): "); String cell = sc.nextLine();

        String regMsg = auth.registerUser(user, pass, fName, lName, cell);
        System.out.println("\n" + regMsg);

        if (regMsg.contains("successfully")) {
            System.out.println("\n--- Login ---");
            System.out.print("Username: "); String logU = sc.nextLine();
            System.out.print("Password: "); String logP = sc.nextLine();
            
            boolean loginSuccessful = auth.loginUser(logU, logP);
            System.out.println(auth.returnLoginStatus(loginSuccessful));

            if (loginSuccessful) {
                int choice = 0;
                while (choice != 5) {
                    System.out.println("\n--- MAIN MENU ---");
                    System.out.println("1) Send a New Message");
                    System.out.println("2) Stored Messages Options");
                    System.out.println("5) Exit");
                    System.out.print("Select an option: ");
                    
                    if (sc.hasNextInt()) {
                        choice = sc.nextInt();
                        sc.nextLine(); 
                        
                        if (choice == 1) {
                            System.out.print("Enter recipient number: ");
                            String rep = sc.nextLine();
                            System.out.print("Enter message: ");
                            String txt = sc.nextLine();
                            System.out.print("Enter flag status (Sent/Stored/Disregard): ");
                            String flg = sc.nextLine();
                            System.out.println(msgProcessor.addMessageToArray(rep, txt, flg));
                        } 
                        else if (choice == 2) {
                            System.out.println("\n--- STORED MESSAGES SUB-MENU ---");
                            System.out.println("a) Display Longest Message");
                            System.out.println("b) Search Message by ID");
                            System.out.println("c) Search Messages by Recipient Phone");
                            System.out.println("d) Delete Message by Hash");
                            System.out.println("e) Display Full Report");
                            System.out.print("Select task (a-e): ");
                            String subChoice = sc.nextLine().toLowerCase();
                            
                            switch (subChoice) {
                                case "a":
                                    System.out.println("Longest Message: " + msgProcessor.displayLongestMessage());
                                    break;
                                case "b":
                                    System.out.print("Enter Message ID: ");
                                    System.out.println(msgProcessor.searchMessageByID(sc.nextLine()));
                                    break;
                                case "c":
                                    System.out.print("Enter Recipient Number: ");
                                    msgProcessor.searchByRecipient(sc.nextLine());
                                    break;
                                case "d":
                                    System.out.print("Enter Message Hash to Delete: ");
                                    System.out.println(msgProcessor.deleteByHash(sc.nextLine()));
                                    break;
                                case "e":
                                    msgProcessor.displayReport();
                                    break;
                                default:
                                    System.out.println("Invalid sub-menu option.");
                            }
                        }
                    } else {
                        sc.nextLine();
                    }
                }
            }
        }
        sc.close();
    }
}
