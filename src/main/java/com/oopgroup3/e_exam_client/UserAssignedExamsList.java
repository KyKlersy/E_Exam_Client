/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
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
    
    private ArrayList<String> studentNameList;
    private ArrayList<Integer> studentUserIDList;
    
    private ResponseSharedData responseSharedData;
    private ExecutorService EXECUTOR;
   

    public UserAssignedExamsList(JList<String> assignedExamStudentList, JComboBox<String> availiableStudentsComboBox, ResponseSharedData responseSharedData, ExecutorService EXECUTOR) {
        this.assignedExamStudentList = assignedExamStudentList;
        this.availiableStudentsComboBox = availiableStudentsComboBox;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
    }
    
    
    public void updateLists()
    {
        defaultListModel.removeAllElements();
        
    }
    
    
    
    
    
}
