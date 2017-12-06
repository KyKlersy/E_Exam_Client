/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Kyle
 */
public class E_Exam 
{
    private final E_Exam_Client_GUI GUI;
    private final ResponseSharedData responseSharedData;
    private final ExecutorService EXECUTOR;
    private ExamList examList;
    private UserAssignedExamsList userAssignedExamsList;
    /*Add private member reference to your class for handling list below*/

    public E_Exam(E_Exam_Client_GUI GUI, ResponseSharedData responseSharedData, ExecutorService EXECUTOR) 
    {
        this.GUI = GUI;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
    }
    
    public void setUserType(UserType userType)
    {
        
        if(userType == UserType.Student)
        {
            examList = new ExamList(GUI.getAvailableStudentExams(), responseSharedData, EXECUTOR);
            examList.updateList();
            
            /*Construct your ExamGradesList class here*/
            
        }
        else if(userType == UserType.Teacher)
        {
            examList = new ExamList(GUI.getAvailableTeacherExams(), responseSharedData, EXECUTOR);
            examList.updateList();
            userAssignedExamsList = new UserAssignedExamsList(GUI.getAssignedStudents_jlist(), GUI.getStudentsComboBox(), responseSharedData, EXECUTOR, this);
        }       
    }
    
    public ExamList getExamList()
    {
        return this.examList;
    }
    
    public UserAssignedExamsList getUserAssignedExamsList()
    {
        return this.userAssignedExamsList;
    }
    
    public E_Exam_Client_GUI getGUI()
    {
        return this.GUI;
    }
    
    public int getSelectedExamID()
    {
        return examList.returnExamDatabaseID();
    }
    
    public final void clearList()
    {
        ExamFormCreationManager.getInstanceExamFormCreationManager().getRootPanel().removeAll();
        this.examList.clear();
        userAssignedExamsList.clearLists();
    }
    
    public void logout()
    {
        clearList();
        this.examList = null;
        this.userAssignedExamsList = null;
    }
}
