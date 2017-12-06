/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Interfaces;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Used to set an exams form question number based on the value of the atmoic
 * integer inside the exam form control manager class.
 * @author Kyle
 */
public interface ExamQuestionNumberInterface {
    
    public void setFormQuestionNumber(AtomicInteger atomicInteger);
}
