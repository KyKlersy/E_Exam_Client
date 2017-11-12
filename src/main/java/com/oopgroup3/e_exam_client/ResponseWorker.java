/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

/**
 *
 * @author Kyle
 */
public class ResponseWorker implements Runnable{

    private String status;
    private String loginData;
    private ResponseSharedData responseSharedData;

    public ResponseWorker(String status, String loginData, ResponseSharedData responseSharedData)
    {
        this.status = status;
        this.loginData = loginData;
        this.responseSharedData = responseSharedData;
    }

    @Override
    public void run() {
       
        if(status.equals("Success"))
        {
            responseSharedData.produce(loginData);
        }
        
        if(status.equals("Failed"))
        {
            responseSharedData.produce("Failed");
        }
    }
    
}
