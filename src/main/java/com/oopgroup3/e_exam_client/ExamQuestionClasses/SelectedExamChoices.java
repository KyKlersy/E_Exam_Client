/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.ExamQuestionClasses;

import com.oopgroup3.e_exam_client.Interfaces.SaveExamFormInterface;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Kyle
 */
public class SelectedExamChoices 
{
    
    private final ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
    private Component[] components;
    private ArrayList<ExamAnswer> examAnswers;
    
    public SelectedExamChoices() 
    {
        components = efcm.getRootPanel().getComponents();
        examAnswers = new ArrayList<>();
        
        JPanel examFormPanel;
        ExamQuestionFormControl eqfc;
        for(Component component : components)
        {
            //Cast component back to jpanel
            examFormPanel = (JPanel) component;
            print("Panel key: " + examFormPanel.getName());
            /*
                Get the class that is tied to the panel by its key which is the unique
                panel name added to the hashmap inside exam form creation manager when
                a form is created.
            */
            eqfc = efcm.getExamQuestionFormControl(examFormPanel.getName());
            print("Question number: " + eqfc.getQuestionNumber());

            //store the returned data class into the exam answer list.
            examAnswers.add(eqfc.saveAnswer());
        }
        
        printAnswers();
    }
    
    public ArrayList<ExamAnswer> getAnswers()
    {
        return this.examAnswers;
    }
    
    public final void printAnswers()
    {
        print("Slected Answers:");
        examAnswers.forEach(p -> {print(p.toString());});
    }    
}
