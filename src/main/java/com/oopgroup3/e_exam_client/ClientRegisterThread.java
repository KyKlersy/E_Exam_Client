/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.google.gson.Gson;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
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
        Message message;
        String methodName = "Register";
        String SessionID = "";
        String[] params = new String[3];
        params[0] = this.username.getText();
        params[1] = String.valueOf(this.password.getPassword());
        params[2] = String.valueOf(this.comboBox.getSelectedIndex() + 1);
        
        message = new Message(SessionID, methodName, params);
        
        
        //sending message
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            message.send(sendSocket);
        } 
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        String msg = "";

        System.out.println("Attempting to get user");
        try 
        {
            msg = responseSharedData.consume();
        } catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
        System.out.println("message future: " + msg);

        if(!msg.equals("Failed"))
        {
            Gson gson = new Gson();
            user = gson.fromJson(msg, User.class);
        }
        
        if (user.getUserType() == 1){
            cardLayoutManager.show(cardContainer, "student");
            System.out.println("Show student panel");
        }
        else{
            cardLayoutManager.show(cardContainer, "teacher");
            System.out.println("Show teacher panel");
        }
        
        
        return ("Username: " + username.getText() + "Password: " + String.valueOf(password.getPassword()) + 
                "Confirm Password: " + String.valueOf(confirmPassword.getPassword()) + "User: " + user.userType);
        
        
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
