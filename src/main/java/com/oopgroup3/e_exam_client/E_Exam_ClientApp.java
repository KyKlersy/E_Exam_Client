/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kyle
 */
public class E_Exam_ClientApp 
{
    
    static final ThreadPoolExecutor EXECUTOR = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    private static E_Exam_Client_GUI GUI;
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
               GUI = new E_Exam_Client_GUI(EXECUTOR);
               GUI.setVisible(true);
            }
        });
        

        /* Binding class methods to button events here */
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                JButton btn = GUI.getLoginButton();
                btn.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        EXECUTOR.execute(new Runnable() {
                            @Override
                            public void run() {
                                String username = GUI.getUsernameTextField();
                                String password = GUI.getPasswordTextField();
                                
                                System.out.println("Username: " + username + " Password: " + password);
                            }
                        });
                    }
                });
                
            }   
        });
        
        
    }        
}
