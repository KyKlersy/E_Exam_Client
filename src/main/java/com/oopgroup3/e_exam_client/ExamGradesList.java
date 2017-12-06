/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.Threads.ExamGradesUpdaterThread;
import java.util.concurrent.ExecutorService;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author tri.le
 */
public class ExamGradesList {
     private JList<String> examGradesJList;
     private DefaultListModel defaultListModel;
     private ResponseSharedData responseSharedData;
     private ExecutorService EXECUTOR;
     private E_Exam_Client_GUI GUI;

    public ExamGradesList(JList<String> examGradesJList, ResponseSharedData responseSharedData, ExecutorService EXECUTOR, E_Exam_Client_GUI GUI) {
        this.responseSharedData = responseSharedData;
        this.EXECUTOR = EXECUTOR;
        this.GUI = GUI;
        this.examGradesJList = examGradesJList;
        defaultListModel = (DefaultListModel)examGradesJList.getModel();
        defaultListModel.removeAllElements();
    }
    public void updateList(){
        defaultListModel.removeAllElements();
        EXECUTOR.execute(new ExamGradesUpdaterThread(responseSharedData, defaultListModel, GUI));
    }
}
