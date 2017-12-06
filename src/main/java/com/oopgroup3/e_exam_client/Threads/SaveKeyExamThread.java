/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamAnswer;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.SelectedExamChoices;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.print;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class SaveKeyExamThread extends SwingWorker<String, Object>{

    private final E_Exam_Client_GUI GUI;
    private final E_Exam exam;
    private final ResponseSharedData responseSharedData;
    private final SelectedExamChoices selectedExamChoices;
    private final User user = User.getUserInstance();
    private MessageWithResponse messageWithResponse ;
    
    public SaveKeyExamThread(E_Exam_Client_GUI GUI, E_Exam exam,ResponseSharedData responseSharedData)
    {
        this.GUI = GUI;
        this.exam = exam;
        this.responseSharedData = responseSharedData;
        selectedExamChoices = new SelectedExamChoices();
    }
    
    @Override
    protected String doInBackground() throws Exception 
    {
        try {
            

        String Method;
        String jsonObject;
        String[] params = new String[2];
        Gson gson = new Gson();

        //Message creation here to be sent to the server.
        AbstractListResponse<ExamAnswer> abstractListResponse = new AbstractListResponse<>();
        abstractListResponse.constuctList(selectedExamChoices.getAnswers());
        
        //Bloody requirement of java and type erasure... to properly seralize this into json
        //we have to setup a type case explicitly.
        Type type = new TypeToken<AbstractListResponse<ExamQuestion>>(){}.getType();
        //Create the list json string representing the abstractlistresponse class
        jsonObject = gson.toJson(abstractListResponse,type);
        
        if(user.getUserType() == 2)
        {
            Method = "KeyExam";
        }
        else
        {
            Method = "SubmitStudentExam";
        }
        params[0] = String.valueOf(user.getUserID());
        params[1] = String.valueOf(exam.getSelectedExamID());

        messageWithResponse = new MessageWithResponse(user.getSessionID(), Method, params, jsonObject);
        //This type of message also has a blocking method to wait for the response, to use this feature
        //response shared data is the blocking implementation that allows data to be recieved from
        //the dedicated ClientResponseThread inside the folder ServerResponses
        //When the return data is capatured in this thread it is placed inside the
        //messageWithResponse that was shared with the ClientResponse thread.
        responseSharedData.produce(messageWithResponse);
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    

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

        
        String msg = "";
        try 
        {
            //Here we attempt to get our returned data, ether we get it or we
            //block until the data is returned
            msg = messageWithResponse.getReturnData();
            print("Returned message" + msg);
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
            Thread.interrupted();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        

        if(Method.equals("KeyExam"))
        {
            GUI.switchCardView("teacher");
        }
        else
        {
            exam.getExamList().updateList();
            GUI.switchCardView("student");
        }
            
        
        
        selectedExamChoices.printAnswers();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
                
    }
    
    
    
}
