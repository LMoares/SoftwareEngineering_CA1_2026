/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
public class AccountUpdate implements AccountUpdateInterface {

    //reference to AccountsFile does not create it
    private AccountsFileInterface accountsFile;
    //reference to Authentication does not create it
    private RegistrationLoginInterface auth;
    //reference to PasswordHashing does not create it
    private PasswordHashing hash;

    //dependency injection
    //seperate responsibility of AccountUpdate and file handling
    //Authentication isn't forced to use AccountsFile,Authentication and PassworHashing everytime
    //testing AccountUpdate is simpler without using real files
    public AccountUpdate(AccountsFile accountsFile, Authentication auth, PasswordHashing hash) {
        this.accountsFile = accountsFile;
        this.auth = auth;
        this.hash = hash;
    }

    //method to update first name and last name
    @Override
    public String updateProfile(String registrationNum, String password, String newfName, String newlName) {

        //validate password
        if (password == null || password.isEmpty() || password.length() < 8) {
            throw new IllegalArgumentException("A password must be provided.");
        }
        //find user by registration number and get their details
        UserAccount ua = accountsFile.findByRegistrationNum(registrationNum);

        //throw exception if no user with registration Number and password don't match
        if (ua == null || !hash.verifyPassword(password, ua.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect registration number or password entered.");
        }
        //if first name is provided update
        if (newfName != null && !newfName.isEmpty()) {
            //set new first name
            ua.setfName(newfName);
        }
        if (newlName != null && !newlName.isEmpty()) {
            //set new last name
            ua.setlName(newlName);
        }

        //save updated account to file
        accountsFile.saveAccount();

        return "Profile has been successfully update.";
    }

    //method to update contact information
    @Override
    public String updateContact(String registrationNum, String password, String newEmail, String newPhoneNumber, String newAddress) {

        //validate passowrd
        if (password == null || password.isEmpty() || password.length() < 8) {
            throw new IllegalArgumentException("A password must be provided.");
        }

        //find user by registration number and get their details
        UserAccount ua = accountsFile.findByRegistrationNum(registrationNum);

        //throw exception if no user with registration Number and password don't match
        if (ua == null || !hash.verifyPassword(password, ua.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect registration number or password entered.");
        }

        //if an email is provided update account
        if (newEmail != null && !newEmail.isEmpty()) {
            //call method from Authentication to check if the email passed to it is valid
            if (!auth.isValidEmail(newEmail)) {
                throw new IllegalArgumentException("The email is not valid, needs to include '@' and meet minimum length");
            }
            //update email
            ua.setEmail(newEmail);
        }

        //if phone number is provided update account
        if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
            //ensure phone number is valid and update it
            if (newPhoneNumber.length() < 1 || newPhoneNumber.length() > 10) {
                throw new IllegalArgumentException("The phone number length is not valid.");
            }
            //update phone number
            ua.setPhoneNumber(newPhoneNumber);
        }
        //if address is provided update account
        if (newAddress != null && !newAddress.isEmpty()) {
            //update address
            ua.setAddress(newAddress);
        }

        //save file
        accountsFile.saveAccount();
        return "Contacts have been successfully update.";

    }

}
