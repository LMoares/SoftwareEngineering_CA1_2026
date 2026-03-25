/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AccountManagement;

/**
 *
 * @author moise
 */
//imort bCrypt package
import org.mindrot.jbcrypt.BCrypt;
public class PasswordHashing {
    //both methods are static because they do not access or modify methods and won't change across instances
    //hash the plain password,static as it doesn't use an instance variable, transoforms input to output
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));//generate a random salt, work factor 12 2^12
    }
    
    //verify the password
    public static boolean verifyPassword(String plainPassword,String storedHash){
        if(plainPassword==null|| storedHash==null ){
            return false;
        }
        //check if storedhash and salt are the same
        return BCrypt.checkpw(plainPassword,storedHash);
    }
    
}
