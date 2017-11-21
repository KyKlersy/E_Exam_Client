/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Interfaces;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import javax.swing.JPanel;

/**
 *
 * @author Kyle
 */
public interface BuildPanelInterface {
    
    public JPanel buildExamPanel(ExamQuestion examQuestion);
    
    public JPanel buildEditableExamPanel(ExamQuestion examQuestion);
               
}
