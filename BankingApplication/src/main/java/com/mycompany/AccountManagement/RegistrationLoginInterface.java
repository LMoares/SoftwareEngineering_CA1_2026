/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
//interface for Authentication
public interface RegistrationLoginInterface {
    public UserAccount createAccount(String fName, String lName, String email, String phoneNumber, 
            String password, double balance, String dateOfBirth, String gender, String address);
    
    public String login(String registrationNum, String password);

     public String updatePassword(String registrationNum, String password, String newPassword);
    
}
