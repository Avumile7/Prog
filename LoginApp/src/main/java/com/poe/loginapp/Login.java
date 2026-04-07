package com.poe.loginapp;

import java.util.regex.Pattern;

public class Login {
    private String registeredUsername, registeredPassword, firstName, lastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") && 
               password.matches(".*\\d.*") && 
               password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        return Pattern.matches("^\\+27[0-9]{9}$", cellNumber);
    }

    public String registerUser(String user, String pass, String fName, String lName, String cell) {
        if (!checkUserName(user)) return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        if (!checkPasswordComplexity(pass)) return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        if (!checkCellPhoneNumber(cell)) return "Cell phone number incorrectly formatted or does not contain international code.";

        this.registeredUsername = user;
        this.registeredPassword = pass;
        this.firstName = fName;
        this.lastName = lName;
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully added.";
    }

    public boolean loginUser(String user, String pass) {
        return user.equals(this.registeredUsername) && pass.equals(this.registeredPassword);
    }

    public String returnLoginStatus(boolean loggedIn) {
        return loggedIn ? "Welcome " + firstName + ", " + lastName + " it is great to see you again." 
                        : "Username or password incorrect, please try again.";
    }
}
