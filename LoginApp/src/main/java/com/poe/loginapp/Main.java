package com.poe.loginapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login auth = new Login();

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
            System.out.println(auth.returnLoginStatus(auth.loginUser(logU, logP)));
        }
    }
}
