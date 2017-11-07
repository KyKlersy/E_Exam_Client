/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Kyle
 */
public class Message {
    
    private String SessionID;
    private String Method;
    private String[] parameters;

    public Message(String SessionID, String Method, String[] parameters) {
        this.SessionID = SessionID;
        this.Method = Method;
        this.parameters = parameters;
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
