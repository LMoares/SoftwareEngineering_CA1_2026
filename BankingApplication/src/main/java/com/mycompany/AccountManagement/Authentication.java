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
    private PasswordHashing hash;
    
    //dependency injection
    //sperate responsibility of authentication and file handling
    //Authentication isn't forced to user AccountsFile everytime
    //testing Authentication is simpler without using real files
    public Authentication(AccountsFileInterface accountsFile, PasswordHashing hash){
        this.accountsFile=accountsFile;
        this.hash=hash;
    }
    
    //register a new account if validation is passed
    public String createAccount(String fName, String lName, String email, String phoneNumber, 
            String password, double balance, String dateOfBirth, String gender, String address) {
        
        //validation
        if (fName == null || fName.equals("")) {
            throw new IllegalArgumentException("First name must be filled.");
        }
        if (lName == null || lName.equals("")) {
            throw new IllegalArgumentException("Last name must be filled.");
        }

        //call method to check if the email passed to it is valid
        if (!isValidEmail(email) || email == null || email.equals("")) {
            throw new IllegalArgumentException("The email is not valid, needs to include '@'/meet minimum length");
        }
        //check if a valid passowrd as been given
        if(  password==null || password.equals("") || !(password.length()>=8 && password.length()<=12)){
            throw new IllegalArgumentException("A password of at least 8 and less than 12 characters must be provided");
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
        
        //validate balance
        if(balance<0){
            throw new IllegalArgumentException("Balance cannot be less than zero");
        }
        //hash password
        String passwordHash=hash.hashPassword(password);
        
        //update accountId and accountNum using methods from AccountsFile
        int accountId=accountsFile.getNextId();
        String accountNum=accountsFile.getNextAccountNum();
//        int accountId=ua.setAccountId(accountsFile.getNextId());
//        String accountNum=ua.setAccountNum(accountsFile.getNextAccountNum());
        
        //initialize new UserAccount Object with its variables after validation completion
        UserAccount ua= new UserAccount( fName,  lName,  accountId, accountNum, email,  phoneNumber, 
             passwordHash,  balance,  dateOfBirth,  gender,  address);
        
        
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
            if (birthDate.isBefore(LocalDate.now().minusYears(16))) {
                return true;
            } else {
                throw new IllegalArgumentException("Must be at least 16 years old to open an account");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Date of birth");

        }
    }

    public String login(int accountId, String email,String password){
        
        
        //call method to check if the email passed to it is valid
        if (!isValidEmail(email) || email == null || email.equals("")) {
            throw new IllegalArgumentException("The email is not valid, needs to include '@'/meet minimum length");
        }
        
        if(!(password.length()<8) || password==null || password.equals("")){
            throw new IllegalArgumentException("A password must be provided.");
        }
        //get save user accounts
        accountsFile.loadFile();
        
        //UserAccount ua=
        
        if(accountId==ua.accountId){
            
        }
                
        
        return "Login was successful.";

    }
}
