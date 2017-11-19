/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.Interfaces.BuildPanelInterface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


/**
 *
 * @author Kyle
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


/**
 *
 * @author Kyle
 */
public class ExamQuestionTrueFalseControl extends ExamQuestionFormControl implements BuildPanelInterface{
    
    protected JPanel rootPanel;
    private JTextArea questionOne;
    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    
    
    public ExamQuestionTrueFalseControl(int QuestionNumber, int QuestionType, JPanel rootPanel) {
        
        super(QuestionNumber, QuestionType);
        
        this.rootPanel = rootPanel;
        
        questionOne = new JTextArea(5,20);
        buttonGroup = new ButtonGroup();
        trueRadioButton = new JRadioButton("True");
        falseRadioButton = new JRadioButton("False");
                
    }
    
    private JPanel buildTrueFalseForm(boolean Editable, ExamQuestion examQuestion)
    {
        
        panel = new JPanel(new GridBagLayout());
        questionOne.setEditable(false);
        if(Editable)
        {
            questionOne.setEditable(true);
        }
        
        if(examQuestion != null)
        {
            questionOne.setFocusable(false);
            questionOne.setText(examQuestion.getQuestion());
        }
        else
        {
            questionOne.setText("");
        }

        questionOne.setLineWrap(true);
        GridBagConstraints gbcTextArea = new GridBagConstraints();
        gbcTextArea.gridx = 0;
        gbcTextArea.gridy = 0;
        gbcTextArea.gridwidth = 2;
        gbcTextArea.fill = GridBagConstraints.BOTH;
        gbcTextArea.weightx = 1.0;
        gbcTextArea.weighty = 1.0;
        gbcTextArea.anchor = GridBagConstraints.CENTER;
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(questionOne);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(jScrollPane, gbcTextArea);
        
        buttonGroup.add(trueRadioButton);
        buttonGroup.add(falseRadioButton);
        
        GridBagConstraints gbcTrueRadioBtn = new GridBagConstraints();
        gbcTrueRadioBtn.gridx = 0;
        gbcTrueRadioBtn.gridy = 1;
        gbcTrueRadioBtn.weightx = 1.5;
        gbcTrueRadioBtn.weighty = 1.5;
        gbcTrueRadioBtn.fill = GridBagConstraints.BOTH;
        panel.add(trueRadioButton, gbcTrueRadioBtn);
        
        GridBagConstraints gbcFalseRadioBtn = new GridBagConstraints();
        gbcFalseRadioBtn.gridx = 1;
        gbcFalseRadioBtn.gridy = 1;
        gbcFalseRadioBtn.weightx = 1.5;
        gbcFalseRadioBtn.weighty = 1.5;
        gbcFalseRadioBtn.fill = GridBagConstraints.BOTH;
        panel.add(falseRadioButton, gbcFalseRadioBtn);
        
        panel.setFocusable(Editable);
        return panel;
    }

    @Override
    public JPanel buildExamPanel(ExamQuestion examQuestion) {
        
        return buildTrueFalseForm(false, examQuestion);
    }

    @Override
    public JPanel buildEditableExamPanel() {
        return buildTrueFalseForm(true, null);
    }
    
}

