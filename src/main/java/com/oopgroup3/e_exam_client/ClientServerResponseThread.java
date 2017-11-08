/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Kyle
 */
public class ClientServerResponseThread implements Runnable{

    @Override
    public void run() {
        
        
        try
        {
            ServerSocket receiveSocket = new ServerSocket(64018);
            
            while(true)
            {
                Socket receive = receiveSocket.accept();
                
                /* To do add the code here to spawn another runnable for updating the class data from the json message returned */
                
                receive.close();
                
            }     
        } 
        catch (IOException ex)
        {

            
        }
    }
}
