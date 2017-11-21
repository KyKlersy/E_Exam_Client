/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.ServerResponseHandler;

import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Kyle
 */
public class ResponseSharedData {
    
    private static BlockingQueue<MessageWithResponse> response;

    public ResponseSharedData() 
    {
        response = new LinkedBlockingQueue<>(10);
    }

    public void produce(MessageWithResponse message)
    {
        response.add(message);     
    }
    
    synchronized public MessageWithResponse consume() 
            throws InterruptedException
    {
        return response.take();   
    }  
    
}
