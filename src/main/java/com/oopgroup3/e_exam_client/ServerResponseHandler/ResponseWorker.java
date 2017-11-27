/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.ServerResponseHandler;

import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;

/**
 *
 * @author Kyle
 */
public class ResponseWorker implements Runnable{

    private String status;
    private String loginData;
    private MessageWithResponse responseData;

    public ResponseWorker(String status, String loginData, MessageWithResponse responseData)
    {
        this.status = status;
        this.loginData = loginData;
        this.responseData = responseData;
    }

    @Override
    public void run() {
       
        if(status.equals("Success"))
        {
            System.out.println("JsonData: " + loginData);
            responseData.putReturnData(loginData);
        }
        
        if(status.equals("Failed"))
        {
            responseData.putReturnData("Failed");
        }
    }
    
}
