/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.ExamListData;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListDecoder;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import com.oopgroup3.e_exam_client.UserAssignedExamsList;
import com.oopgroup3.e_exam_client.UserData;
import static com.oopgroup3.e_exam_client.Utils.printDebug.print;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class UserAssignedExamsListUpdater extends SwingWorker<String, Object> {


    private E_Exam exam;
    private User user = User.getUserInstance();    
    private ResponseSharedData responseSharedData;
    private MessageWithResponse responseMessage;
    private AbstractListDecoder abstractListDecoder;
    
    
    public UserAssignedExamsListUpdater( E_Exam exam, ResponseSharedData responseSharedData ) 
    {
       this.exam = exam;
       this.responseSharedData = responseSharedData;
    }

    @Override
    protected String doInBackground() throws Exception 
    {
        String[] parameters = new String[2];
        parameters[0] = String.valueOf(user.getUserID());
        parameters[1] = String.valueOf(exam.getSelectedExamID());
        responseMessage = new MessageWithResponse(user.getSessionID(), "GetAssignableStudents", parameters);
        responseSharedData.produce(responseMessage);
        
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            responseMessage.send(sendSocket);
        } 
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        String msg = "";
        try 
        {
            msg = responseMessage.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        print("Json Returned meg: " + msg);
        TypeToken<AbstractListResponse<UserData>> typeToken = new TypeToken<AbstractListResponse<UserData>>(){};
        AbstractListResponse<UserData> responseClass = abstractListDecoder.parseAbstractListResponse(msg, typeToken);
        
        print("Size of response: " + responseClass.getList().size());
        
        return null;
    }
    
    
    
}
