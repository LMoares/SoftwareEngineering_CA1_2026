/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.AccountManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author moise
 */
public class AccountsFile implements AccountsFileInterface {

    //array list for users
    private ArrayList<UserAccount> users = new ArrayList<>();
    //variable to increment the id, numeric part of accountnum and registration number for new users
    private int nextId = 1, nextAccountNum = 50201918, nextRegistrationNum=24875611;
    //variable to icnrement the registration number for the new users
    
    //method to save user to file
    @Override
    public void saveAccount() {
        //set to false so it is replaced with new file when changed
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", false))) {
            //iterate through ArrayList and add new Account to it
            for (int i = 0; i < users.size(); i++) {
                //get the UserAccount object at index i from the ArrayList
                UserAccount ua = users.get(i);
                //write to the text file Account details in the FileFormat
                bw.write(ua.accountDetailsFileFormat());
                //new line to write next Account
                bw.newLine();
            }
            System.out.println("The user's account was successfully saved was successfully saved.");

        } catch (IOException e) {
            System.out.println("There was an error saving: " + e);
        }
    }

    //method to load file with users
    @Override
    public void loadFile() {
        //clear list to prevent duplicates
        users.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            //variable to hold text file String
            String line;
            while ((line = br.readLine()) != null) {
                //array to store user account information
                String[] info = line.split("\\|");//treat pipe as a normal character instead of as or in regex

                //assign to variable the info from the save file
                String fName = info[0];
                String lName = info[1];
                int accountId = Integer.parseInt(info[2]);
                String accountNum=info[3];
                String registrationNum=info[4];
                String email = info[5];
                String phoneNumber = info[6];
                String passwordHash = info[7];
                double balance = Double.parseDouble(info[8]);
                String dateOfBirth = info[9];
                String gender = info[10];
                String address = info[11];
                
                //update the next id for use by the next user, splits responsibilities, keep id generation here
                //ensures the nextId is always larger, preventing duplicates
                nextId=Math.max(nextId, accountId+1);
                
                //update the user account number for use by the next user
                //ensures the nextAccountNum is always larger, preventing duplicates
                //use regex to only select the digits 
                nextAccountNum=Math.max(nextAccountNum, Integer.parseInt(accountNum.replaceAll("\\D", ""))+1);
                
                //update the registration number for use by the next user
                nextRegistrationNum=Math.max(nextRegistrationNum, Integer.parseInt(registrationNum)+1);
                
                //initialize new UserAccount object with the user information
                UserAccount account = new UserAccount(fName, lName, accountId, accountNum, registrationNum, email,
                        phoneNumber, passwordHash, balance, dateOfBirth, gender, address);
                

                //add accounts to arraylist
                users.add(account);
            }

        } catch (FileNotFoundException e) {
            System.out.println("The user.txt could not be found. " + e);
        } catch (IOException e) {
            System.out.println("There was an error reading the file.  " + e);
        }

    }
    
    //
    
    //method to add to ArrayList
    @Override
    public void addAccount(UserAccount ua){
        users.add(ua);
    }
    
    //increment nextid for use in Authentication to form accountId when a new account is registered and saved
    @Override
    public int getNextId(){
        //return 1 first then increments
        return nextId++;
    }
    
    //increment nextAccountNum for use in Authentication to form accountNum when a new account is registered and saved
    @Override
    public String getNextAccountNum(){
        //returns 502019180 then increments
        return "IESEBA35330"+(nextAccountNum++);
    }
    
    @Override
    public String getNextRegistrationNum(){
        return Integer.toString(nextRegistrationNum++);
    }
}
