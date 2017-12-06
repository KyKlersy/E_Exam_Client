package com.oopgroup3.e_exam_client.ServerResponseHandler;

import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This is the data communication object that the threads use to pass data between
 * themselves and the ClientServerResponseThread. Only one of these is created
 * and shared between all threads that need to send and receive messages.
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
