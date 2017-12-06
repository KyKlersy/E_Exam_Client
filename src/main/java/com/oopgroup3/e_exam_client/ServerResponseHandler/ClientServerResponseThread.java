package com.oopgroup3.e_exam_client.ServerResponseHandler;

import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.ResponseMessage;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;


/**
 *
 * This Thread is dedicated to handling all responses received from the server
 * it then hands off the received information to a response worker.
 * @author Kyle
 */
public class ClientServerResponseThread implements Runnable{

    private ExecutorService EXECUTOR;
    private ResponseSharedData responseSharedData;
    private MessageWithResponse messageWithResponse;

    
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
            
            while(!Thread.interrupted())
            {
                Socket receive = receiveSocket.accept();
                
                try 
                {
                    messageWithResponse = responseSharedData.consume();
                    print("Consumed message to handle: ");
                } 
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                    Thread.interrupted();
                }
                
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receive.getInputStream()));

                    String jsonString = bufferedReader.readLine(); 
                    ResponseMessage responseMessage = gson.fromJson(jsonString, ResponseMessage.class);

                    EXECUTOR.submit(new ResponseWorker(responseMessage.getStatus(), responseMessage.getJsonObject(), messageWithResponse));
                    
                    receive.close();

                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }  
            }     
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();          
        }
    }
}
