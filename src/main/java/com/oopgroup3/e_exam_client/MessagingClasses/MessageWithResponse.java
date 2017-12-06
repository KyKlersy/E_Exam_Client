package com.oopgroup3.e_exam_client.MessagingClasses;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This is a special message that allows the thread that receives it to block on it
 * until the returnedData portion is filled.
 * @author Kyle
 */
public class MessageWithResponse extends Message{
    
    private final BlockingQueue<String> returnedData;
    
    public MessageWithResponse(String SessionID, String Method, String[] parameters, String jsonObject) {
        super(SessionID, Method, parameters, jsonObject);
        returnedData = new ArrayBlockingQueue<>(1);
    }
    
    public MessageWithResponse(String SessionID, String Method, String[] parameters) {
        super(SessionID, Method, parameters);
        returnedData = new ArrayBlockingQueue<>(1);
    }
    
    public MessageWithResponse(String SessionID, String Method) {
        super(SessionID, Method);
        returnedData = new ArrayBlockingQueue<>(1);
    }
    
    
    
    public String getReturnData()
            throws InterruptedException
    {
        return returnedData.take();
    }
    
    public void putReturnData(String jsonString)
    {
        returnedData.offer(jsonString);
    }
    
}
