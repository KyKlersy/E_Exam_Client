/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.Threads.UserAssignedExamsListUpdater;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author Kyle
 */
public class UserAssignedExamsList 
{

    private JList<String> assignedExamStudentList; 
    private DefaultListModel defaultListModel;
    
    private JComboBox<String> availiableStudentsComboBox;
    private DefaultComboBoxModel<String> defaultComboBoxModel;
    
    private ArrayList<Integer> studentUserIDList;
    
    private ResponseSharedData responseSharedData;
    private ExecutorService EXECUTOR;
    private E_Exam exam;

    public UserAssignedExamsList(JList<String> assignedExamStudentList, JComboBox<String> availiableStudentsComboBox, ResponseSharedData responseSharedData, ExecutorService EXECUTOR, E_Exam exam) {
        this.assignedExamStudentList = assignedExamStudentList;
        this.availiableStudentsComboBox = availiableStudentsComboBox;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
        this.exam = exam;
        defaultListModel = (DefaultListModel)assignedExamStudentList.getModel();
        defaultComboBoxModel = (DefaultComboBoxModel<String>)availiableStudentsComboBox.getModel();
        studentUserIDList = new ArrayList<>();
    }
    

    
    
    public void updateLists()
    {
        defaultListModel.removeAllElements();
        defaultComboBoxModel.removeAllElements();
        studentUserIDList.clear();
        
        EXECUTOR.execute(new UserAssignedExamsListUpdater(exam, responseSharedData));
        
    }
        
    public DefaultComboBoxModel<String> getAssignableStudentsComboBoxModel()
    {
        return this.defaultComboBoxModel;
    }
    
    public DefaultListModel<String> getStudentsAssignedExamsListModel()
    {
        return this.defaultListModel;
    }
    
    public int getSelectedStudentID()
    {
        return studentUserIDList.get(availiableStudentsComboBox.getSelectedIndex());
    }
    
    public ArrayList<Integer> getStudentIDArrayList()
    {
        return this.studentUserIDList;
    }
    
    public void clearLists()
    {
        defaultListModel.removeAllElements();
        defaultComboBoxModel.removeAllElements();
        studentUserIDList.clear();
    }
    
}
