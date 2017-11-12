/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Kyle
 */
public class ResponseSharedData {
    
    private static BlockingQueue<String> jsonString;

    public ResponseSharedData() 
    {
        jsonString = new LinkedBlockingQueue<>(10);
    }

    public void produce(String message)
    {
        jsonString.add(message);     
    }
    
    synchronized public String consume() throws InterruptedException
    {
        return jsonString.take();
      
    }  
    
}
