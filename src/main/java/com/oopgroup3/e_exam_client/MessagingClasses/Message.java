package com.oopgroup3.e_exam_client.MessagingClasses;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This is the base class that all other messages inherit from.
 * @author Kyle
 */
public class Message {
    
    private String SessionID;
    private String Method;
    private String[] parameters;
    private String jsonObject;

    public Message(String SessionID, String Method)
    {
        this.SessionID = SessionID;
        this.Method = Method;
    }
    
    public Message(String SessionID, String Method, String[] parameters) {
        this.SessionID = SessionID;
        this.Method = Method;
        this.parameters = parameters;
    }
    
    public Message(String SessionID, String Method, String[] parameters, String jsonObject)
    {
        this.SessionID = SessionID;
        this.Method = Method;
        this.parameters = parameters;
        this.jsonObject = jsonObject;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String Method) {
        this.Method = Method;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }
    
    
    public final void send(Socket sock)
    {
        try
        {
            /* make connection to server socket */
            
            DataOutputStream dataOutputStream = new DataOutputStream(sock.getOutputStream());
            
            Gson gson = new Gson();
            
            String jsonString = gson.toJson(this);
            
            dataOutputStream.writeBytes(jsonString);
            
            dataOutputStream.close();

            sock.close();

        }
        catch(IOException ioe)
        {
            System.err.println(ioe);
        }
    }
}
