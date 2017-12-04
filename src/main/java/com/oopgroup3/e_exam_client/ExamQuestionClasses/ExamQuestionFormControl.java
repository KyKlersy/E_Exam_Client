package com.oopgroup3.e_exam_client.ExamQuestionClasses;

import com.oopgroup3.e_exam_client.Interfaces.BuildPanelInterface;
import com.oopgroup3.e_exam_client.Interfaces.ExamQuestionNumberInterface;
import com.oopgroup3.e_exam_client.Interfaces.RemovableFormInterface;
import com.oopgroup3.e_exam_client.Interfaces.SaveExamAnswersInterface;
import com.oopgroup3.e_exam_client.Interfaces.SaveExamFormInterface;
import javax.swing.JPanel;

/**
 *
 * Abstract base class used in the creation of the form controls for exam questions.
 * All sub-classes of this abstract must implement the interfaces BuildPanelInterface and
 * SaveExamFormInterface.
 * 
 * @author Kyle
 */
public abstract class ExamQuestionFormControl extends JPanel 
        implements BuildPanelInterface, SaveExamFormInterface, RemovableFormInterface, ExamQuestionNumberInterface, SaveExamAnswersInterface
{
    private int ExamQuestionID = -1;
    private int QuestionNumber;
    private int QuestionType;

    public ExamQuestionFormControl(){}
    
    public ExamQuestionFormControl(int QuestionNumber, int QuestionType, int ExamQuestionID) 
    {
        this.QuestionNumber = QuestionNumber;
        this.QuestionType = QuestionType;
        this.ExamQuestionID = ExamQuestionID;
    }

    public int getExamQuestionID() {
        return ExamQuestionID;
    }

    public void setExamQuestionID(int ExamQuestionID) {
        this.ExamQuestionID = ExamQuestionID;
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
    
    public abstract void updateQuestionNumber();
            
}
