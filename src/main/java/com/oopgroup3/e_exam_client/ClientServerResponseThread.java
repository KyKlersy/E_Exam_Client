/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


/**
 *
 * @author Kyle
 */
public class ClientServerResponseThread implements Runnable{

    private ExecutorService EXECUTOR;
    private ResponseSharedData responseSharedData;

    public ClientServerResponseThread(ExecutorService EXECUTOR, ResponseSharedData responseSharedData) 
    {
        this.EXECUTOR = EXECUTOR;
        this.responseSharedData = responseSharedData;
    }

    @Override
    public void run() {
              
        try
        {
            ServerSocket receiveSocket = new ServerSocket(64018);
            Gson gson = new Gson();
            
            while(true)
            {
                Socket receive = receiveSocket.accept();
                
                /* To do add the code here to spawn another runnable for updating the class data from the json message returned */
               
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receive.getInputStream()));

                    String jsonString = bufferedReader.readLine(); 
                    ResponseMessage responseMessage = gson.fromJson(jsonString, ResponseMessage.class);
                    
                    if(responseMessage.getWorkerThreadName().equals("LoginResponseWorker"))
                    {
                        EXECUTOR.submit(new ResponseWorker(responseMessage.getStatus(), responseMessage.getJsonObject(), responseSharedData));
                    }
                    receive.close();

                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }  
                
                    



            }     
        } 
        catch (IOException ex)
        {

            
        }
    }
}
