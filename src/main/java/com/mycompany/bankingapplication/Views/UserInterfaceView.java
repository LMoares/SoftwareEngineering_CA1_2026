/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankingapplication.Views;

import com.mycompany.bankingapplication.Controller.UserInterfaceController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Software Engineering Team Project - 2026
 * Classname UserInterfaceView.java
 * Date 03/03/2026
 * @author Lorenzo, Jean, Aaron, Moise
 */
public class UserInterfaceView extends JFrame {
    private JPanel cards;
    private CardLayout layout;
    private NavMenu navMenu;
    private UserInterfaceController UIC;
    
    public UserInterfaceView(UserInterfaceController UIC) {
        super("Banking Application");
        this.UIC = UIC;
        //window contraints
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Setup for JPanel collection
        cards = new JPanel(new CardLayout());
        layout = (CardLayout) (cards.getLayout());
        
        //NavMenu setup
        navMenu = new NavMenu();
        navMenu.setListener(UIC);
    }
    
    public void addCards(JPanel panel, String key) {
        cards.add(panel, key);
    }
    
    public void generateCards() {
        add(cards, BorderLayout.CENTER);
    }
    
    public void displayMenu() {
        add(navMenu, BorderLayout.WEST);
    }
    
    public void removeMenu() {
        remove(navMenu);
    }
    
    public void showCard(String key) {
        layout.show(cards, key);
    }
}
