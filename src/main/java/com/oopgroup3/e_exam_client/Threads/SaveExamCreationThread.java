/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionFormControl;
import com.oopgroup3.e_exam_client.Interfaces.SaveExamFormInterface;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class SaveExamCreationThread extends SwingWorker<Void, Object>{

    private User user;
    private String MethodCall;
    private E_Exam exam;
    private ResponseSharedData responseSharedData;
    private Component[] components;
    private ArrayList<ExamQuestion> examQuestions;
    private MessageWithResponse messageWithResponse ;
    private ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
    
    public SaveExamCreationThread(String MethodName, E_Exam exam, ResponseSharedData responseSharedData) 
    {
        this.MethodCall = MethodName;
        this.exam = exam;
        this.responseSharedData = responseSharedData;
        components = efcm.getRootPanel().getComponents();
        examQuestions = new ArrayList<>();
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        try 
        {

            JPanel examFormPanel;
            ExamQuestionFormControl eqfc;
            SaveExamFormInterface sefi;
            
            for(Component component : components)
            {
                examFormPanel = (JPanel) component;
                print("Panel key: " + examFormPanel.getName());
                eqfc = efcm.getExamQuestionFormControl(examFormPanel.getName());
                print("Question number: " + eqfc.getQuestionNumber());
                sefi = (SaveExamFormInterface)eqfc;
                examQuestions.add(sefi.saveForm());
            }
            
            //Get user singleton instance.
            user = User.getUserInstance();
            
            String jsonObject;
            Gson gson = new Gson();
            
            AbstractListResponse<ExamQuestion> abstractListResponse = new AbstractListResponse<>();
            abstractListResponse.constuctList(examQuestions);
            Type type = new TypeToken<AbstractListResponse<ExamQuestion>>(){}.getType();
            jsonObject = gson.toJson(abstractListResponse,type);
            
            String[] parameters = new String[2];
            parameters[0] = Integer.toString(user.getUserID());
            parameters[1] = exam.getGUI().getCreateExamNameTextField().getText();
            
            //Message message = new Message(user.getSessionID(), MethodCall, parameters, jsonObject);
            messageWithResponse = new MessageWithResponse(user.getSessionID(), MethodCall, parameters, jsonObject);
            responseSharedData.produce(messageWithResponse);
            try 
            {
                Socket sendSocket = new Socket("127.0.0.1",64023);    
                //message.send(sendSocket);
                messageWithResponse.send(sendSocket);
                
            } 
            catch (UnknownHostException e)
            {
             e.printStackTrace();
            }
            catch(IOException ioe)
            {
             ioe.printStackTrace();
            } 
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        String msg = "";
        try 
        {
            msg = messageWithResponse.getReturnData();
            print("Returned message" + msg);
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if(msg.equals("Success"))
        {
            exam.getExamList().updateList();
        }
        
        return null;
    }
    
    @Override
    public void done()
    {
        exam.getGUI().switchCardView("teacher"); 
    }
    
}
