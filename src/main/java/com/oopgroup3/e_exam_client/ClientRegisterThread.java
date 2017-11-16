/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author tri.le
 */
public class ClientRegisterThread extends SwingWorker<String, Object>{
    private final JTextField username;
    private final JPasswordField password;
    private final JPasswordField confirmPassword;
    private final CardLayout cardLayoutManager;
    private final JPanel cardContainer;
    private final JComboBox<String> comboBox;
    private User user = null;
    private ResponseSharedData responseSharedData;

    public ClientRegisterThread(JTextField usernameField, JPasswordField passwordField, JPasswordField confirmPassword, JComboBox<String> comboBox, 
            CardLayout cardLayoutManager, JPanel cardContainer, ResponseSharedData responseSharedData, User user)
    {
        this.username = usernameField;
        this.password = passwordField;
        this.confirmPassword = confirmPassword;
        this.comboBox = comboBox;
        this.cardLayoutManager = cardLayoutManager;
        this.cardContainer = cardContainer;
        this.responseSharedData = responseSharedData;
        this.user = user;
    }

    @Override
    protected String doInBackground() 
    {
        return ("Username: " + username.getText() + "Password: " + String.valueOf(password.getPassword()) + 
                "Confirm Password: " + String.valueOf(confirmPassword.getPassword()));
        
    }
    
    @Override
    protected void done()
    {
        try
        {
            
            System.out.println(get());
            
        }
        catch (Exception ignore)
        {
            
        }
    }
}
