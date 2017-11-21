/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionFormControl;
import com.oopgroup3.e_exam_client.Interfaces.SaveExamFormInterface;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithJsonObject;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.awt.Component;
import java.io.IOException;
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
    private Component[] components;
    private ArrayList<ExamQuestion> examQuestions;
    private ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
    
    public SaveExamCreationThread() 
    {
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
                
                sefi = (SaveExamFormInterface)eqfc;
                examQuestions.add(sefi.saveForm());
            }
            
            //Get user singleton instance.
            user = User.getUserInstance();
            MessageWithJsonObject mwjo = new MessageWithJsonObject(user.getSessionID(), "CreateExam", examQuestions);
            
            try 
            {
                Socket sendSocket = new Socket("127.0.0.1",64023);    
                mwjo.send(sendSocket);
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
        
        return null;
    }
    
}
