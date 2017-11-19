/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import javax.swing.JPanel;

/**
 *
 * @author Kyle
 */
public abstract class ExamQuestionFormControl extends JPanel{
    
    private int QuestionNumber;
    private int QuestionType;

    public ExamQuestionFormControl(int QuestionNumber, int QuestionType) {
        this.QuestionNumber = QuestionNumber;
        this.QuestionType = QuestionType;
    }

    public int getQuestionNumber() {
        return QuestionNumber;
    }

    public void setQuestionNumber(int QuestionNumber) {
        this.QuestionNumber = QuestionNumber;
    }

    public int getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(int QuestionType) {
        this.QuestionType = QuestionType;
    }
    
    
    
}
