/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.ExamGrade;
import com.oopgroup3.e_exam_client.ExamListData;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListDecoder;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

/**
 *
 * @author tri.le
 */
public class ExamGradesUpdaterThread extends SwingWorker<String, Object>{
    private ResponseSharedData responseSharedData;
    private MessageWithResponse responseMessage;
    private User user = User.getUserInstance();
    private DefaultListModel defaultListModel;
    private AbstractListDecoder abstractListDecoder;
    private E_Exam_Client_GUI GUI;

    public ExamGradesUpdaterThread(ResponseSharedData responseSharedData, DefaultListModel defaultListModel, E_Exam_Client_GUI GUI) {
        this.responseSharedData = responseSharedData;
        this.defaultListModel = defaultListModel;
        this.GUI = GUI;
        abstractListDecoder = new AbstractListDecoder();
    }

    @Override
    protected String doInBackground() throws Exception {
        String[] parameters = new String[2];
        parameters[0] = String.valueOf(user.getUserID());
        parameters[1] = String.valueOf(user.getUserType());
        responseMessage = new MessageWithResponse(user.getSessionID(), "GetExamGrades", parameters);
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
        
        TypeToken<AbstractListResponse<ExamGrade>> typeToken = new TypeToken<AbstractListResponse<ExamGrade>>(){};
        AbstractListResponse<ExamGrade> responseClass = abstractListDecoder.parseAbstractListResponse(msg, typeToken);
        
        
        System.out.println("Size of list: " + responseClass.getList().size());
        
        
        responseClass.getList().forEach(item -> {
           
            defaultListModel.addElement(item.toString());

        });
        
        
        return "complete";
    }
    
    @Override
    protected void done(){
        //GUI.switchCardView("studentGrades");
    }
    
}
