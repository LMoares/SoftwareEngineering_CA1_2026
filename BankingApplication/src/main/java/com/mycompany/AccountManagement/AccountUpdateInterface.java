/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
public interface AccountUpdateInterface {
    
    public String updateProfile(String registrationNum, String password, String newfName, String newlName);
    public String updateContact(String registrationNum, String password, String newEmail, String newPhoneNumber, String newAddress);
}
