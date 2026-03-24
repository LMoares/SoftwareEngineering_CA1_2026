/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
public class UserAccount {

    //variables
    private String fName, lName;
    private int accountId;
    private String accountNum,registrationNum, email, phoneNumber;
    private String passwordHash;
    private double balance;
    private String dateOfBirth, gender, address;

    //overloaded constructor
    public UserAccount(String fName, String lName, int accountId, String accountNum, String registrationNum, String email, String phoneNumber, String passwordHash, double balance, String dateOfBirth, String gender, String address) {
        this.fName = fName;
        this.lName = lName;
        this.accountId = accountId;
        this.accountNum = accountNum;
        this.registrationNum = registrationNum;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    //setters
    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }   

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //getters
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }
   
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    //get user details
    public String accountDetails() {
        return "Registration details\n" + "Account Id: " + accountId + "\nAccount Number: \n" + accountNum +"Registration Number"+
                registrationNum+"\nFirst Name: " + fName + "\nLast Name: " + lName + "\nEmail: "
                + "\nPhone Number: " + phoneNumber +"\nEmail: "+ email + "\nPassword: " + passwordHash + "\nBalance:" + balance
                + "\nDate of Birth " + dateOfBirth + "\n Gender" + gender + "\nAddress: " + address;
    }

    //method for a parseable format with the comma delimetre that can be saved to a file
    public String accountDetailsFileFormat() {
        return fName + "|" + lName + "|" + accountId + "|" + accountNum + "|" +registrationNum+"|"+ email + "|" + phoneNumber + "|" + passwordHash + "|" + balance + "|"
                + dateOfBirth + "|" + gender + "|" + address;
    }

}
