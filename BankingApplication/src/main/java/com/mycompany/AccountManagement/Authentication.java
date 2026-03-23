/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AccountManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author moise
 */
public abstract class Authentication implements RegistrationLoginInterface {
    
    //reference the AccountsFile does not create it
    private AccountsFileInterface accountsFile;
    
    //dependency injection
    //sperate responsibility of authentication and file handling
    //Authentication isn't forced to user AccountsFile everytime
    //testing Authentication is simpler without using real files
    public Authentication(AccountsFileInterface accountsFile){
        this.accountsFile=accountsFile;
    }
    //register a new account if validation is passed
    public String createAccount(String fName, String lName, int accountId, String email, String phoneNumber, 
            String passwordHash, int age, double balance, String dateOfBirth, String gender, String address) {
        
        //validation
        if (fName == null || fName.equals("")) {
            throw new IllegalArgumentException("First name must be filled.");
        }
        if (lName == null || lName.equals("")) {
            throw new IllegalArgumentException("Last name must be filled.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age is invalid.");
        }
        if (age < 16) {
            throw new IllegalArgumentException("You need to be at least 16 to open an account.");
        }
        //call method to check if the email passed to it is valid
        if (!isValidEmail(email) || email == null || email.equals("")) {
            throw new IllegalArgumentException("The email is not valid, needs to include '@'/meet minimum length");
        }
        //check null empty before
        if ( phoneNumber == null || phoneNumber.equals("") || phoneNumber.length() < 1 || phoneNumber.length() > 10) {
            throw new IllegalArgumentException("Phone number length is not the valid.");
        }
        
        //call method to validate birthdate
        if (!isValidBirthday(dateOfBirth)){
            throw new IllegalArgumentException("That is an Invald Date of birth.");
        }

        if (gender.equals("") || gender == null || !(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            throw new IllegalArgumentException("That is not a valid gender.");
        }

        if (address == null || address.equals("")) {
            throw new IllegalArgumentException("An address must be provided.");
        }
        
        //initialize new UserAccount Object with its variables after validation completion
        UserAccount ua= new UserAccount( fName,  lName,  accountId,  email,  phoneNumber, 
             passwordHash,  age,  balance,  dateOfBirth,  gender,  address);
        
        //add Account to the ArrayList
        accountsFile.addAccount(ua);
        
        //save the Account
        accountsFile.saveAccount();
        
        return "Congratulations your account was created successfully!.";

    }

    //ensures email is valid
    public boolean isValidEmail(String email) {
        return (email != null && !email.equals("") && email.contains("@") && email.length() >= 5);
 
    }

    public boolean isValidBirthday(String dob) {
        //assign user entered dob as a String to birthdateIn
        String birthDateIn = dob;

        //defines expected pattern of birthdate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {

            //converts birthDateIn to LocalDate and check if it matches pattern in formatter
            LocalDate birthDate = LocalDate.parse(birthDateIn, formatter);

            //check if the users' birthdate is after the 16 years mark from now
            if (birthDate.isAfter(LocalDate.now().minusYears(16))) {
                return true;
            } else {
                throw new IllegalArgumentException("Must be at least 16 years old to open an account");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Date of birth");

        }
    }

    public String login(int accountId, String email,String password){
        

    }
}
