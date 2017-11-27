package com.oopgroup3.e_exam_client.ExamQuestionClasses;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


/**
 * This is a concrete implementation of a true false styled question on an exam.
 * It allows a block of text for the question along with two radio buttons
 * for the true or false options. These are tied together in a button group
 * so that only a single choice can be made.
 * @author Kyle
 */
public class ExamQuestionTrueFalseControl extends ExamQuestionFormControl 
{
    private JLabel questionNumLabel;
    private JTextArea questionOne;
    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JButton removeFormButton;
    
    public ExamQuestionTrueFalseControl() {
        
        super();
               
        questionOne = new JTextArea(5,20);
        buttonGroup = new ButtonGroup();
        trueRadioButton = new JRadioButton("True");
        falseRadioButton = new JRadioButton("False");
        questionNumLabel = new JLabel();
        
    }
    
    private JPanel buildTrueFalseForm(boolean Editable, ExamQuestion examQuestion)
    {
        
        panel = new JPanel(new GridBagLayout());
        questionOne.setEditable(false);
        if(Editable)
        {
            questionOne.setEditable(true);
            
            removeFormButton = new JButton();
            removeFormButton.setName(UUID.randomUUID().toString());
            removeFormButton.setText("X");
            GridBagConstraints gbcRemoveFormButton = new GridBagConstraints( 2, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                    GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0 );

            panel.add(removeFormButton, gbcRemoveFormButton);
        }
        
        if(examQuestion != null)
        {
            questionOne.setText(examQuestion.getQuestion());
            
        }
        else
        {
            questionOne.setText("");
            
        }

        GridBagConstraints gbcQuestionNumberLabel = new GridBagConstraints();
        gbcQuestionNumberLabel.gridx = 0;
        gbcQuestionNumberLabel.gridy = 0;
        gbcQuestionNumberLabel.gridwidth = 2;
        gbcQuestionNumberLabel.fill = GridBagConstraints.BOTH;
        panel.add(questionNumLabel, gbcQuestionNumberLabel);
        
        questionOne.setLineWrap(true);
        GridBagConstraints gbcTextArea = new GridBagConstraints();
        gbcTextArea.gridx = 1;
        gbcTextArea.gridy = 1;
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
        gbcTrueRadioBtn.gridx = 1;
        gbcTrueRadioBtn.gridy = 2;
        gbcTrueRadioBtn.weightx = 1.0;
        gbcTrueRadioBtn.weighty = 1.0;
        gbcTrueRadioBtn.fill = GridBagConstraints.BOTH;
        panel.add(trueRadioButton, gbcTrueRadioBtn);
        
        GridBagConstraints gbcFalseRadioBtn = new GridBagConstraints();
        gbcFalseRadioBtn.gridx = 2;
        gbcFalseRadioBtn.gridy = 2;
        gbcFalseRadioBtn.weightx = 1.0;
        gbcFalseRadioBtn.weighty = 1.0;
        gbcFalseRadioBtn.fill = GridBagConstraints.BOTH;
        panel.add(falseRadioButton, gbcFalseRadioBtn);
        
        panel.setName(UUID.randomUUID().toString());
        Border margin = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panel.setBorder(new CompoundBorder(border, margin));       
        panel.setPreferredSize(new Dimension(500, 300));
        return panel;
    }

    @Override
    public void updateQuestionNumber() {

        questionNumLabel.setText("Question #" + super.getQuestionNumber());
        
    }
   
    @Override
    public JPanel buildExamPanel(ExamQuestion examQuestion) {
        
        return buildTrueFalseForm(false, examQuestion);
    }

    @Override
    public JPanel buildEditableExamPanel(ExamQuestion examQuestion) {
        return buildTrueFalseForm(true, examQuestion);
    }

    @Override
    public ExamQuestion saveForm() 
    {  
        return new ExamQuestion(getQuestionNumber(), 1, questionOne.getText(), "", "", "", "");
    }

    @Override
    public JButton removeFormBtn() {
        return this.removeFormButton;
    }

    @Override
    public void setFormQuestionNumber(AtomicInteger atomicInteger) {
        super.setQuestionNumber(atomicInteger.get());
        questionNumLabel.setText("Question #" + Integer.toString(atomicInteger.get()));
    }

}

