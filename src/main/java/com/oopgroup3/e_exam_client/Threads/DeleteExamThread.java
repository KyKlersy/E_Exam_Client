/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.print;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * wake up when teacher delete the exam
 * send msg to sever and then update examlist again
 * @author tri.le
 */
public class DeleteExamThread extends SwingWorker<String, Object>{
    private E_Exam_Client_GUI GUI;
    private ResponseSharedData responseSharedData;
    private E_Exam exam;
    private User user;
    private MessageWithResponse messageWithResponse;
    
    public DeleteExamThread(E_Exam_Client_GUI GUI,E_Exam exam, ResponseSharedData responseSharedData) {
        this.GUI = GUI;
        this.exam = exam;
        this.responseSharedData = responseSharedData;
        user = User.getUserInstance();
    }

    @Override
    protected String doInBackground() throws Exception {
        
        SwingUtilities.invokeLater(() -> {
            GUI.getDeleteExamBtn().setEnabled(false);
        });
        
        
        String methodName = "DeleteExam";
        String SessionID = "";
        String[] params = new String[2];
        params[0] = Integer.toString(user.getUserID());
        params[1] = Integer.toString(exam.getSelectedExamID());
        messageWithResponse = new MessageWithResponse(user.getSessionID(), methodName, params);
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
        
        return "Returned message" + msg;
    }
    
    @Override
    public void done()
    {
        
        GUI.getDeleteExamBtn().setEnabled(true);
        
        
    }
    
    
}
