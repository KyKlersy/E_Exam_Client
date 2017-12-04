/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.Threads.ExamListUpdaterThread;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Kyle
 */
public class ExamList
{
    private JList<String> examJList;
    private ArrayList<String> examNameList;
    private ArrayList<Integer> examIDList;
    private ResponseSharedData responseSharedData;
    private ExecutorService EXECUTOR;
    private DefaultListModel defaultListModel;
    
    
    public ExamList(JList<String> examJList, ResponseSharedData responseSharedData, ExecutorService EXECUTOR) 
    {
        this.examJList = examJList;
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
        defaultListModel = (DefaultListModel)examJList.getModel();
        defaultListModel.removeAllElements();
        
        examNameList = new ArrayList<>();
        examIDList = new ArrayList<>();

    }
    
    public void updateList()
    {
        examNameList.clear();
        examIDList.clear();
        defaultListModel.removeAllElements();
        
        EXECUTOR.execute(new ExamListUpdaterThread(responseSharedData, examNameList, examIDList, defaultListModel));
 
    }

    public int returnExamDatabaseID()
    {
        return examIDList.get(examJList.getSelectedIndex());
    }
    
    public void clear()
    {
        examNameList.clear();
        examIDList.clear();
    }
    
}
