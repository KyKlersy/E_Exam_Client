/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.AssignedExamData;
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
import javax.swing.DefaultComboBoxModel;
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
    private MessageWithResponse responseMessageStudents;
    private MessageWithResponse responseMessageAssigned;
    private AbstractListDecoder abstractListDecoder;

    
    
    public UserAssignedExamsListUpdater( E_Exam exam, ResponseSharedData responseSharedData ) 
    {
       this.exam = exam;
       this.responseSharedData = responseSharedData;
    }

    @Override
    protected String doInBackground() throws Exception 
    {
        try {

            
        String[] parameters = new String[2];
        parameters[0] = String.valueOf(user.getUserID());
        parameters[1] = String.valueOf(exam.getSelectedExamID());
        responseMessageStudents = new MessageWithResponse(user.getSessionID(), "GetAssignableStudents", parameters);
        responseSharedData.produce(responseMessageStudents);
        responseMessageAssigned = new MessageWithResponse(user.getSessionID(), "GetAssignedStudents");
        responseSharedData.produce(responseMessageAssigned);
        abstractListDecoder = new AbstractListDecoder();
        
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            responseMessageStudents.send(sendSocket);
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
            msg = responseMessageStudents.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        print("Json Returned meg: " + msg);
        
        TypeToken<AbstractListResponse<UserData>> typeToken = new TypeToken<AbstractListResponse<UserData>>(){};
        AbstractListResponse<UserData> responseClass = abstractListDecoder.parseAbstractListResponse(msg, typeToken);
        
        print("Size of response: " + responseClass.getList().size());
        
        String assignedMsg = "";
        try 
        {
            assignedMsg = responseMessageAssigned.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        print("Json Returned meg: " + assignedMsg);
        TypeToken<AbstractListResponse<AssignedExamData>> typeToken2 = new TypeToken<AbstractListResponse<AssignedExamData>>(){};
        AbstractListResponse<AssignedExamData> responseClass2 = abstractListDecoder.parseAbstractListResponse(assignedMsg, typeToken2);
        
        ArrayList<Integer> studentIDList = exam.getUserAssignedExamsList().getStudentIDArrayList();
        
        DefaultComboBoxModel<String> dcbm = exam.getUserAssignedExamsList().getAssignableStudentsComboBoxModel();
        responseClass.getList().forEach(item -> {
            print("Firstname: " + item.getUserFirstName());
            dcbm.addElement(item.getUserFirstName());
            studentIDList.add(item.getUserID());
            
        });
        
        DefaultListModel<String> dflm = exam.getUserAssignedExamsList().getStudentsAssignedExamsListModel();
        responseClass2.getList().forEach(item ->{
            print(item.toString());
            dflm.addElement(item.toString());
        });

            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }    

    @Override
    protected void done() 
    {
        
        exam.getGUI().getTeacherAssignExams_panel().repaint();
        
    }
    
    
}
