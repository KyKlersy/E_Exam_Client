/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

/**
 *
 * @author Kyle
 */


/**
 * representing for exam list data
 * @author tri.le
 */
public class ExamListData 
{
    private String ExamName;
    private int  ExamID;

    public ExamListData(String ExamName, int ExamID) {
        this.ExamName = ExamName;
        this.ExamID = ExamID;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String ExamName) {
        this.ExamName = ExamName;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int ExamID) {
        this.ExamID = ExamID;
    }
}
