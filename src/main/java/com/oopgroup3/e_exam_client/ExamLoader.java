/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;


import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionFormControl;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionMultipleChoiceFormControl;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionTrueFalseControl;
import java.util.ArrayList;
import java.util.List;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;

/**
 *
 * @author plati_000
 */
public class ExamLoader 
{
    private List<ExamQuestion> examList = new ArrayList<ExamQuestion>();
    private ExamFormCreationManager efcm;
    private ExamQuestionFormControl examFormControl;
    
    public ExamLoader(List<ExamQuestion> examList)
    {
        this.examList.addAll(examList);
        efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
        
    }
    
    public void loadEditableExam()
    {
        loadExamForm(true);
    }
    
    public void loadExam()
    {
        loadExamForm(false);
    }
        
    private void loadExamForm(boolean editable)
    {
        print("examList size: " + examList.size());
        for(int i = 0; i < examList.size(); i++)
        {
            System.out.println("Question: " +examList.get(i).getQuestion());
            if(examList.get(i).getQuestionType() == 1)
            {
                examFormControl = new ExamQuestionTrueFalseControl();
                efcm.addExamQuestion(examFormControl, examList.get(i), editable);
            }
            else if(examList.get(i).getQuestionType() == 2)
            {
                examFormControl = new ExamQuestionMultipleChoiceFormControl();
                efcm.addExamQuestion(examFormControl, examList.get(i), editable);
            }
        }
    }
}
