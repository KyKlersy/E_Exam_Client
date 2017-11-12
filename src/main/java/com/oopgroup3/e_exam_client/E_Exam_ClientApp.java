/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author Kyle
 */
public class E_Exam_ClientApp 
{
    /* Thread pooling usage of EXECUTOR to run Runables / callables or java swingworkers */
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(8);
    
    /* Hold a reference to the GUI so that we can access its getters / setters */
    private static E_Exam_Client_GUI GUI;
    
    /* Message passing class for inter thread communication */
    private static ResponseSharedData responseSharedData = new ResponseSharedData();
    
    
    public static void main(String[] args) {
        
        User user = null;
        
        /***********************************************
        * Setup the GUI frame by placing it on the EDT *
        ************************************************/
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
               GUI = new E_Exam_Client_GUI();
               GUI.setVisible(true);
               
               GUI.addWindowListener(new WindowAdapter() 
               {
                    @Override
                    public void windowClosed(WindowEvent e) 
                    {
                        EXECUTOR.shutdownNow();
                        System.exit(0);
                    }
               });
            }
        });
        
        /*********************************************************************************/
        /* Start background thread for handling incomming messages from server to client */
        /*********************************************************************************/
        EXECUTOR.execute(new ClientServerResponseThread(EXECUTOR, responseSharedData));
        
        
        /* Binding thread event handlers to button events here */
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                //Getting Jbutton reference from gui and adding a button event listner
                JButton loginButton = GUI.getLoginButton();
                loginButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        //on button click event triggered, create and execute a new thread to handle the task.
                        //These can be swing workers / runnable / callables
                        EXECUTOR.execute(new ClientLoginThread(GUI.getUsernameTextField(), GUI.getPassword_txtField(), GUI.getCardLayoutManager(), GUI.getCardContainer() ,responseSharedData , user));   
                        
                    }
                });
                
                /**************************************************************************************************
                 *                                                                                                *
                 * Add your handlers for your buttons below inside this block for binding.                        *
                 *                                                                                                *
                 *                                                                                                *
                 **************************************************************************************************/

                
                
                
                
                
                
                /****************************************
                 * End of button handler binding block  *
                 ****************************************/ 
            }   
        });              
    }        
}
