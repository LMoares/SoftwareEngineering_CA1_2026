/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
public interface AccountsFileInterface {
    
    public void saveAccount();
    
    public void loadFile();
   
    public void addAccount(UserAccount ua);
    
    public int getNextId();
    
    public String getNextAccountNum();
    
    public String getNextRegistrationNum()
    
}
