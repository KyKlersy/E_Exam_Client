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

/**
 * class to hold exam components: userType, examType, examID
 * @author tri.le
 */
public class E_Exam 
{
    private final E_Exam_Client_GUI GUI;
    private final ResponseSharedData responseSharedData;
    private final ExecutorService EXECUTOR;
    private ExamList examList;
    private UserAssignedExamsList userAssignedExamsList;
    /*Add private member reference to your class for handling list below*/
    private ExamGradesList examGradesList;

    public E_Exam(E_Exam_Client_GUI GUI, ResponseSharedData responseSharedData, ExecutorService EXECUTOR) 
    {
        this.GUI = GUI;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
    }
    
    /**
     * set user type after user click login button
     * load examList for both student and teacher
     * @param userType 
     */
    public void setUserType(UserType userType)
    {
        
        if(userType == UserType.Student)
        {
            examList = new ExamList(GUI.getAvailableStudentExams(), responseSharedData, EXECUTOR);
            examList.updateList();
            
            /*Construct your ExamGradesList class here*/
            examGradesList = new ExamGradesList(GUI.getExamGradeJList(), responseSharedData, EXECUTOR, GUI);
            examGradesList.updateList();
        }
        else if(userType == UserType.Teacher)
        {
            examList = new ExamList(GUI.getAvailableTeacherExams(), responseSharedData, EXECUTOR);
            examList.updateList();
            userAssignedExamsList = new UserAssignedExamsList(GUI.getAssignedStudents_jlist(), GUI.getStudentsComboBox(), responseSharedData, EXECUTOR, this);
        }       
    }

    public ExamGradesList getExamGradesList() {
        return examGradesList;
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
