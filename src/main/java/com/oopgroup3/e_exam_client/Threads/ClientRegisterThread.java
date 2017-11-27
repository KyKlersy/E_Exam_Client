/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.google.gson.Gson;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
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
    private E_Exam_Client_GUI GUI;
    private final JTextField username;
    private final JPasswordField password;
    private final JPasswordField confirmPassword;
    private final CardLayout cardLayoutManager;
    private final JPanel cardContainer;
    private final JComboBox<String> comboBox;
    private User returnedUser;
    private User user;
    private ResponseSharedData responseSharedData;

    public ClientRegisterThread(E_Exam_Client_GUI GUI, ResponseSharedData responseSharedData)
    {
        this.GUI = GUI;
        this.username = GUI.getRegisterUsername_txtField();
        this.password = GUI.getRegisterPassword_txtField();
        this.confirmPassword = GUI.getRegisterPasswordConfirm_txtField();
        this.comboBox = GUI.getUser_type_comboBox();
        this.cardLayoutManager = GUI.getCardLayoutManager();
        this.cardContainer = GUI.getCardContainer();
        this.responseSharedData = responseSharedData;
    }

    @Override
    protected String doInBackground() 
    {
        MessageWithResponse responseMessage;
        String methodName = "Register";
        String SessionID = "";
        String[] params = new String[3];
        params[0] = this.username.getText();
        params[1] = String.valueOf(this.password.getPassword());
        params[2] = String.valueOf(this.comboBox.getSelectedIndex() + 1);
        
        responseMessage = new MessageWithResponse(SessionID, methodName, params);
        responseSharedData.produce(responseMessage);
        
        //sending message
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            responseMessage.send(sendSocket);
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

        print("Attempting to get user");
        try 
        {
            msg = responseMessage.getReturnData();
        } catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
        print("message future: " + msg);

        if(!msg.equals("Failed"))
        {
            Gson gson = new Gson();
            returnedUser = gson.fromJson(msg, User.class);
            user = User.getUserInstance();
            user.setSessionID(returnedUser.getSessionID());
            user.setUserID(returnedUser.getUserID());
            user.setUserType(returnedUser.getUserType());
            user.setUserFirstName(returnedUser.getUserFirstName());
        }
        
        if (returnedUser.getUserType() == 1){
            cardLayoutManager.show(cardContainer, "student");
            System.out.println("Show student panel");
        }
        else{
            cardLayoutManager.show(cardContainer, "teacher");
            System.out.println("Show teacher panel");
        }
        
        
        return ("Username: " + username.getText() + "Password: " + String.valueOf(password.getPassword()) + 
                "Confirm Password: " + String.valueOf(confirmPassword.getPassword()) + "User: " + returnedUser.getUserType());
        
        
    }
    
    @Override
    protected void done()
    {
        GUI.getRegisterUsername_txtField().setText("");
        GUI.getRegisterPassword_txtField().setText("");
        GUI.getRegisterPasswordConfirm_txtField().setText("");
    }
}
