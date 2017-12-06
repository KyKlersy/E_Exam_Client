package com.oopgroup3.e_exam_client.Interfaces;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import javax.swing.JPanel;

/**
 * Used by the two classes in examquestionclasses package
 * Implementing this interface allows the exam form control manager
 * to be able to process and build an exam form.
 * @author Kyle
 */
public interface BuildPanelInterface {
    
    public JPanel buildExamPanel(ExamQuestion examQuestion);
    
    public JPanel buildEditableExamPanel(ExamQuestion examQuestion);
               
}
