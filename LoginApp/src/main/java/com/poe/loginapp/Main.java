package com.poe.loginapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login auth = new Login();
        Message msgProcessor = new Message();

        System.out.println("--- Register ---");
        System.out.print("First Name: "); String fName = sc.nextLine();
        System.out.print("Last Name: "); String lName = sc.nextLine();
        System.out.print("Username: "); String user = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        System.out.print("Cell (+27): "); String cell = sc.nextLine();
        sc.nextLine();
        String regMsg = auth.registerUser(user, pass, fName, lName, cell);
        System.out.println("\n" + regMsg);

        if (regMsg.contains("successfully")) {
            System.out.println("\n--- Login ---");
            System.out.print("Username: "); String logU = sc.nextLine();
            System.out.print("Password: "); String logP = sc.nextLine();
            
            boolean loginSuccessful = auth.loginUser(logU, logP);
            System.out.println(auth.returnLoginStatus(loginSuccessful));

            // Part 2: QuickChat features run only upon successful login
            if (loginSuccessful) {
                System.out.println("\nWelcome to QuickChat.");
                
                int choice = 0;
                while (choice != 3) {
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choice: ");
                    
                    if (sc.hasNextInt()) {
                        choice = sc.nextInt();
                        sc.nextLine(); // Clear scanner buffer
                        
                        switch (choice) {
                            case 1:
                                // Option 1: Capture message details
                                System.out.print("Enter recipient cell number: ");
                                String recipient = sc.nextLine();
                                
                                System.out.print("Enter your message (max 250 characters): ");
                                String textBody = sc.nextLine();
                                
                                // Process and print the result output
                                String processResult = msgProcessor.processMessage(recipient, textBody);
                                System.out.println("\n" + processResult);
                                break;
                                
                            case 2:
                                // Option 2: Requirements specify showing 'Coming Soon.'
                                System.out.println("Coming Soon.");
                                break;
                                
                            case 3:
                                System.out.println("Exiting QuickChat. Goodbye!");
                                break;
                                
                            default:
                                System.out.println("Invalid option. Please select 1, 2, or 3.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid menu number.");
                        sc.nextLine(); // Clear the bad input
                    }
                }
            }
        }
        sc.close();
    }
}

