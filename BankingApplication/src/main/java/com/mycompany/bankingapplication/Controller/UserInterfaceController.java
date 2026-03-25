/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankingapplication.Controller;

import com.mycompany.AccountManagement.AccountLoginGUI;
import com.mycompany.bankingapplication.Views.*;
import com.mycompany.AccountManagement.AccountManagementGUI;
import com.mycompany.AccountManagement.AccountRegistrationGUI;
import com.mycompany.AccountManagement.UpdateGUI;
import com.mycompany.AccountManagement.UserAccount;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Software Engineering Team Project - 2026 Classname
 * UserInterfaceController.java Date 03/03/2026
 *
 * @author Lorenzo, Jean, Aaron, Moise
 */
public class UserInterfaceController {

    private UserInterfaceView ui;

    //variable to store the current UserAccount
    private UserAccount currentAccount;

    private Map<String, Controllable> cards = new HashMap<>() {
        {
            //Place all panels that need to be displayed here
            //Example:
            //put(key, new PanelClass());
            //put("LoanApplication", new LoanApplication());
            put("Login", new LoginPanel());
            put("HomePage", new HomePage());
            put("AccountManagement", new AccountManagementGUI());
            put("AcccountRegistration", new AccountRegistrationGUI());
            put("AccountLogin", new AccountLoginGUI());
            put("Update", new UpdateGUI());
            put("LoanApplication", new LoanApplication());
        }
    };

    public UserInterfaceController() {
        //Passes a reference of this controller object to ui object -- allows for two-way communication
        ui = new UserInterfaceView(this);

        initializeCards();
        ui.generateCards();
        changeCard("Login");
        ui.setVisible(true);
    }

    public void initializeCards() {
        //Interates through each element in the cards collection
        //Calls setListener to pass controller reference to each panel -- allows for two-way communication
        //Each card and associated key then sent to UserInterfaceView to add card to internal card layout collection
        for (Map.Entry<String, Controllable> card : cards.entrySet()) {
            String key = card.getKey();
            Controllable controllableCard = card.getValue();
            //Call setListener -- implemented via Controllable interface
            controllableCard.setListener(this);
            //Cast controllableCard to JPanel so that View can add card to card layout collection
            JPanel panel = (JPanel) controllableCard;
            ui.addCards(panel, key);
        }
    }

    public void updateCardUserDetails() {
        //Interates through each element in the cards collection
        //Calls setUserDetails to update card data to reflect current logged in user
        for (Map.Entry<String, Controllable> card : cards.entrySet()) {
            Controllable controllableCard = card.getValue();
            //Call setUserDetails -- implemented via Controllable interface
            controllableCard.setUserDetails();
        }
    }

    public void changeCard(String key) {
        //TODO: Once Moise creates user entity, determine best way to send user data between cards
        var card = cards.get(key);

        //Error message -- will execute if given key is not in collection
        if (card == null) {
            System.out.println("ChangeCard Error: key not found");
            return;
        }

        ui.showCard(key);

    }

    public void removeMenu() {
        ui.removeMenu();
    }

    //set current user account after registration or login
    public void setUserDetails(UserAccount account) {
        this.currentAccount = account;
        updateCardUserDetails(); //update all cards to reflect current user
    }

    //TODO: User service - methods that will call for new user registration, login existing users, retrieve user data, save user data
    //allow GUI's to get access to current user account from the controller
    public UserAccount getUser() {
        return currentAccount;

    } //void -> User

    //Class responsible for creating new users, getting/saving user details from data file
    public void getUserService() {
    } //void -> UserService

    public void registerUser() {
    }

    public void loginUser() {
        ui.displayMenu();
        changeCard("HomePage");
    }

    public void signOut() {
        ui.removeMenu();
        changeCard("Login");
    }

}
