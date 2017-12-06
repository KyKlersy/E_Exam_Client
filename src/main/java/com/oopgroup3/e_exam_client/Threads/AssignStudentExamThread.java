/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class AssignStudentExamThread extends SwingWorker<Void, Object>
{
    private E_Exam exam;
    private User user = User.getUserInstance();
    private ExecutorService EXECUTOR;
    private ResponseSharedData responseSharedData;
    private MessageWithResponse messageWithResponse;

    public AssignStudentExamThread(E_Exam exam, ResponseSharedData responseSharedData, ExecutorService EXECUTOR) {
        this.exam = exam;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
    }
    
    @Override
    protected Void doInBackground() throws Exception 
    {
        String[] parameters = new String[3];
        parameters[0] = String.valueOf(exam.getUserAssignedExamsList().getSelectedStudentID());
        parameters[1] = String.valueOf(user.getUserID());
        parameters[2] = String.valueOf(exam.getSelectedExamID());
        
        messageWithResponse = new MessageWithResponse(user.getSessionID(), "AssignStudentExam", parameters);
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
            msg = messageWithResponse.getReturnData();
            print("Returned message after inseting new examee. " + msg);
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        if(msg.equals("Success"))
        {
            exam.getUserAssignedExamsList().updateLists();
        }
        
        return null;
    }
    
}
