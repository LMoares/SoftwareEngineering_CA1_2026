/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankingapplication.Controller;

/**
 * Software Engineering Team Project - 2026
 * Interfacename Controllable.java
 * Date 03/03/2026
 * @author Lorenzo, Jean, Aaron, Moise
 */
public interface Controllable {
    //Required implementation, allows JPanel to be added to card list (the collection where JPanels are stored)
    //Each implemented class stores a reference to the UIC via UICListener
    
    //Required reference in implemented classes
    //UserInterfaceController Listener;
    
    void setListener(UserInterfaceController Listener);
    
    //Used to call Controller for updated user details
    //Requires that it runs after user is logged in, else will set null details
    
    //NOTE: removed to get goin
    //void setUserDetails();
}
