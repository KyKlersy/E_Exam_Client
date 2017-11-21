package com.oopgroup3.e_exam_client.ExamQuestionClasses;

/**
 *
 * Data class used to represent the exam question,
 * each exam question consists of the question number, its question type
 * 1 being a true/false form, 2 being a 4 option multiple choice form.
 * 
 * @author carlo
 * 
 */
public class ExamQuestion 
{
    
    private int QuestionNumber;
    private int QuestionType;
    private String Question = "";
    private String Question_1 = "";
    private String Question_2 = "";
    private String Question_3 = "";
    private String Question_4 = "";
    
    public ExamQuestion(){} //provide default constructor.
    
    /*The constructor for this class should also 
    be written such that passing all 6 fields during 
    construction takes the information passed and 
    stores it in the internal fields of the class. */
    public ExamQuestion(int QuestionNumber, int QuestionType, String Question, String Question_1, String Question_2, String Question_3, String Question_4)
    {
        setQuestionNumber(QuestionNumber);
        setQuestionType(QuestionType);
        setQuestion(Question);
        setQuestion_1(Question_1);
        setQuestion_2(Question_2);
        setQuestion_3(Question_3);
        setQuestion_4(Question_4);
    }

    public final void setQuestionNumber(int QuestionNumber)
    {
        this.QuestionNumber=QuestionNumber;
    }
    public int getQuestionNumber()
    {
        return this.QuestionNumber;
    }

    public final void setQuestionType(int QuestionType)
    {    
        this.QuestionType=QuestionType;
    }

    public int getQuestionType()
    {    
        return this.QuestionType;
    }
    
    public final void setQuestion(String Question)
    {
        this.Question = Question;
    }
    
    public String getQuestion()
    {
        return this.Question;
    }
    
    public final void setQuestion_1(String Question_1)
    {
        this.Question_1= Question_1;
    }

    public String getQuestion_1()
    {
        return this.Question_1;
    }

    public final void setQuestion_2(String Question_2)
    {
        this.Question_2= Question_2;
    }

    public String getQuestion_2()
    {
        return this.Question_2;
    }

    public final void setQuestion_3(String Question_3)
    {
        this.Question_3= Question_3;
    }

    public String getQuestion_3()
    {
        return this.Question_3;
    }

    public final void setQuestion_4(String Question_4)
    {
        this.Question_4= Question_4;
    }

    public String getQuestion_4()
    {
        return this.Question_4;
    }
}
