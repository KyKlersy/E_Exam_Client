/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.MessagingClasses;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle
 */
public class MessageWithJsonObject extends Message{
    
    private List<ExamQuestion> examData = new ArrayList<>();
    
    public MessageWithJsonObject(String SessionID, String Method,ArrayList<ExamQuestion> examQuestions)
    {
        super(SessionID, Method);
        List<ExamQuestion> examQuestionAsList = examQuestions;
        examData.addAll(examQuestionAsList);

    }
    
    public void printExamQuestionList()
    {
        for(ExamQuestion examQuestion : examData)
        {
            System.out.println("Exam Question: " + examQuestion.getQuestion());
        }
    }
            
    public List<ExamQuestion> getExamQuestions()
    {
        return examData;
    }
}
