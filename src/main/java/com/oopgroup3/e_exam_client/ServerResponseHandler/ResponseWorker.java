package com.oopgroup3.e_exam_client.ServerResponseHandler;

import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;

/**
 * 
 * The response worker takes the received message from the server and a messagewithresponse data object
 * it then fills this objects blocking queue with the json data contained inside the message sent from the server.
 * @author Kyle
 */
public class ResponseWorker implements Runnable{

    private String status;
    private String jsonData;
    private MessageWithResponse responseData;

    public ResponseWorker(String status, String jsonData, MessageWithResponse responseData)
    {
        this.status = status;
        this.jsonData = jsonData;
        this.responseData = responseData;
    }

    @Override
    public void run() {
       
        if(status.equals("Success"))
        {
            System.out.println("JsonData: " + jsonData);
            responseData.putReturnData(jsonData);
        }
        
        if(status.equals("Failed"))
        {
            responseData.putReturnData("Failed");
        }
    }
    
}
