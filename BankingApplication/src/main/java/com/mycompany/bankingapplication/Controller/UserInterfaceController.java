/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankingapplication.Controller;

import com.mycompany.bankingapplication.Views.AccountManagement;
import com.mycompany.bankingapplication.Views.HomePage;
import com.mycompany.bankingapplication.Views.LoginPanel;
import com.mycompany.bankingapplication.Views.UserInterfaceView;
import com.mycompany.AccountManagement.AccountManagementGUI;
import com.mycompany.AccountManagement.AccountRegistrationGUI;
import com.mycompany.AccountManagement.AccountLoginGUI;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Software Engineering Team Project - 2026
 * Classname UserInterfaceController.java
 * Date 03/03/2026
 * @author Lorenzo, Jean, Aaron, Moise
 */
public class UserInterfaceController {
    private UserInterfaceView ui;
    
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
            put("AccountLoginGUI", new AccountLogingGUI());
            
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
    
    public void changeCard(String key) {
        //TODO: Once Moise creates user entity, determine best way to send user data between cards
        var card = cards.get(key);
        
        //Error message -- will execute if given key is not in collection
        if(card == null) {
            System.out.println("ChangeCard Error: key not found");
            return;
        }
        
        card.setUserDetails();
        
        ui.showCard(key);
    }
    
    public void removeMenu() {
        ui.removeMenu();
    }
    
    //TODO: User service - methods that will call for new user registration, login existing users, retrieve user data, save user data
    public void getUser(){} //void -> User
    
    //Class responsible for creating new users, getting/saving user details from data file
    public void getUserService(){} //void -> UserService
    
    public void registerUser(){}
    
    public void loginUser() {
        ui.displayMenu();
        changeCard("HomePage");
    }
    
    public void signOut() {
        ui.removeMenu();
        changeCard("Login");
    }
    
    
    
}
public class UserInterfaceController {

    private UserInterfaceView ui;
    private UserService userHandler;
    //all panels are stored via this dictionary so that user actions can be monitored and responded to via this class
    private Map<String, IControllable> panels = new HashMap<>() {
        {
            put("Login", new LoginPanel());
            put("ChemWaste", new ChemicalWasteGUI());
            put("Recyclable", new RecyclableWasteGUI());
            put("NonRecyclable", new NonRecyclableWasteGUI());
            put("UserPortal", new UserPortal());
            put("SusCon", new SusConPanel());
            put("SusConQuestionnaire", new SusConQuestionnaire());
            put("NewUserRegistration", new NewUserRegistration());
            put("SusConConsultation", new SusConConsultation());
            put("tourism", new tourismHomePanel());
            put("bikeBooking", new bikeBookingGUI());
            put("customerFeedback", new custFeedbackGUI());
        }
    };

    public UserInterfaceController() {
        ui = new UserInterfaceView(this);

        initializePanels();
        ui.generateNavbar();
        ui.generateCards();
        //initial ui view panel
        ui.showPanel("Login");
        userHandler = new UserService();
    }

    public void showWindow() {
        ui.setVisible(true);
    }

    public void initializePanels() {

        //iterate through panel dictionary to set panelListener and intialize panels in card layout
        for (Map.Entry<String, IControllable> card : panels.entrySet()) {
            String key = card.getKey();
            IControllable panel = card.getValue();
            //setPanelListener implemented from IControllable interface
            panel.setPanelListener(this);
            //Cast panel from IControllable to JPanel for card addition to ui view cardlayout
            JPanel panelfix = (JPanel) panel;
            ui.initializeCards(panelfix, key);
        }
    }

    public void changePanel(String panel) {
        //ensures panel specific logic runs before panel is swapped into frame
        if (panel.equals("UserPortal")) {
            UserPortal up = (UserPortal) panels.get("UserPortal");
            up.updateEFScore();
        } else if (panel.equals("SusCon")) {
            //create reference to suscon panel - runs reset method on activation
            SusConPanel suscon = (SusConPanel) panels.get("SusCon");
            suscon.resetText();
        } else if (panel.equals("SusConConsultation")) {
            SusConConsultation susconCon = (SusConConsultation) panels.get("SusConConsultation");
            susconCon.setUserDetails();
        } else if (panel.equals("Login")) {
            //user has been logged out
            userHandler.setUser(null);
        } else if (panel.equals("NonRecyclable")) {
            //call get user information(method)
            NonRecyclableWasteGUI NRW = (NonRecyclableWasteGUI) panels.get("NonRecyclable");
            NRW.setUserDetails();
        } else if (panel.equals("Recyclable")) {
            RecyclableWasteGUI RW = (RecyclableWasteGUI) panels.get("Recyclable");
            RW.setUserDetails();
        } else if (panel.equals("bikeBooking")) {
            bikeBookingGUI bikeBookGUI = (bikeBookingGUI) panels.get("bikeBooking");
            bikeBookGUI.setUserDetails();
        } else if (panel.equals("SusConQuestionnaire")) {
            SusConQuestionnaire scq = (SusConQuestionnaire) panels.get("SusConQuestionnaire");
            scq.setUserDetails();
        }
        else if (panel.equals("customerFeedback")){
            custFeedbackGUI cus = (custFeedbackGUI) panels.get("customerFeedback");
            cus.setUserDetails();
        }
        ui.showPanel(panel);
    }

    public void removeNavbar() {
        //called when user logs out to remove navigation
        ui.removeNavbar();
    }

    public User getUser() {
        //getter for passing user reference
        return userHandler.getUser();
    }

    public UserService getUserService() {
        //getter for passing user reference
        return userHandler;
    }

    public void saveUserChanges() {
        //calls the userservice object to save user information
        userHandler.saveChanges();
    }

    public void registrationComplete(int cfscore) {
        //on registration completion cfscore is attributed to user object
        userHandler.setCFScore(cfscore);
        //save new user information to file
        saveUserChanges();
        //create userportal reference to userportal panel and set user before panel change
        UserPortal userportal = (UserPortal) panels.get("UserPortal");
        userportal.setUser();
        //on successful login, panel changes to userportal panel, navbar made visible
        ui.displayNavbar();
        changePanel("UserPortal");
    }

    public void onRegisterButtonClicked(String username, String name, String password) {
        //this method runs once login panel detects user interaction with register button

        //ensures password is not empty and is not a series of whitespaces
        if (!username.isBlank() && !name.isBlank() && !password.isBlank()) {
            //creates reference in user service to current user
            boolean valid = userHandler.registerNewUser(name, username, password);

            //on successful login, panel changes to home panel, navbar made visible
            if (valid) {
                changePanel("NewUserRegistration");
            }
        } else {
            System.out.println("Please fill in all Registration fields to complete User Registration.");
        }
    }

    public void onLoginButtonClicked(String username, String password) {
        //this method runs once login panel detects user interaction with login button

        userHandler.loginExistingUser(username, password);

        if (userHandler.getUser() == null) {
            JOptionPane.showMessageDialog(null, "Username and/or password does not match any existing users. Please try again or Register.");
        } else {
            //on successful login, panel changes to home panel, navbar made visible 
            ui.displayNavbar();
            UserPortal up = (UserPortal) panels.get("UserPortal");
            up.setUser();
            changePanel("UserPortal");
        }

    }