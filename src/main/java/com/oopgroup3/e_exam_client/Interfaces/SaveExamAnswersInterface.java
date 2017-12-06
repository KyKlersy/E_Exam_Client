/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Interfaces;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamAnswer;

/**
 * This interface defines how an exam form should return its data fields
 * to be packaged and sent to the server.
 * @author Kyle
 */
public interface SaveExamAnswersInterface 
{
    public ExamAnswer saveAnswer();
}
