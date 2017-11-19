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


public final class ExamQuestionMultipleChoiceFormControl extends ExamQuestionFormControl implements BuildPanelInterface{

    protected JPanel rootPanel;
    private JTextArea questionOne;
    private JTextArea answerOne;
    private JTextArea answerTwo;
    private JTextArea answerThree;
    private JTextArea answerFour;
    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JRadioButton aRadioButton;
    private JRadioButton bRadioButton;
    private JRadioButton cRadioButton;
    private JRadioButton dRadioButton;
    
    public ExamQuestionMultipleChoiceFormControl(int QuestionNumber, int QuestionType, JPanel rootPanel) {
        super(QuestionNumber, QuestionType);
        
        this.rootPanel = rootPanel;
        
        questionOne = new JTextArea();
        answerOne = new JTextArea();
        answerTwo = new JTextArea();
        answerThree = new JTextArea();
        answerFour = new JTextArea();
        buttonGroup = new ButtonGroup();
        aRadioButton = new JRadioButton("A");
        bRadioButton = new JRadioButton("B");
        cRadioButton = new JRadioButton("C");
        dRadioButton = new JRadioButton("D");
        
    }
    
    protected final JPanel buildMultipleChoiceForm(boolean Editable, ExamQuestion examQuestion)
    {
        panel = new JPanel(new GridBagLayout());
        questionOne.setEditable(false);
        answerOne.setEditable(false);
        answerTwo.setEditable(false);
        answerThree.setEditable(false);
        answerFour.setEditable(false);
        
        if(Editable)
        {
            questionOne.setEditable(true);
            answerOne.setEditable(true);
            answerTwo.setEditable(true);
            answerThree.setEditable(true);
            answerFour.setEditable(true);
        }

        if(examQuestion != null)
        {
            
            questionOne.setText(examQuestion.getQuestion());
            answerOne.setText(examQuestion.getQuestion_1());
            answerTwo.setText(examQuestion.getQuestion_2());
            answerThree.setText(examQuestion.getQuestion_3());
            answerFour.setText(examQuestion.getQuestion_4());
        }
        else
        {
            questionOne.setText("");
            answerOne.setText("");
            answerTwo.setText("");
            answerThree.setText("");
            answerFour.setText("");
        }    
        
        buttonGroup.add(aRadioButton);
        buttonGroup.add(bRadioButton);
        buttonGroup.add(cRadioButton);
        buttonGroup.add(dRadioButton);
        
        /* Main Question at the top */
        repeatJScrollableTextAreaLayout(0, 0, 2, panel, questionOne, new JScrollPane());
        
        /* Answer text for choice A along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(0, 1, 1, panel, answerOne, new JScrollPane());
        repeatRadioButtonLayout( 0, 2, 1.0, 1.0, panel, aRadioButton);
        
        /* Answer text for choice B along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(1, 1, 1, panel, answerTwo, new JScrollPane());
        repeatRadioButtonLayout( 1, 2, 1.0, 1.0, panel, bRadioButton);
        
        /* Answer text for choice C along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(0, 3, 1, panel, answerThree, new JScrollPane());
        repeatRadioButtonLayout( 0, 4, 1.0, 1.0, panel, cRadioButton);
        
        /* Answer text for choice D along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(1, 3, 1, panel, answerFour, new JScrollPane());
        repeatRadioButtonLayout( 1, 4, 1.0, 1.0, panel, dRadioButton);
        
        return panel;
    }
    
    
    private void repeatJScrollableTextAreaLayout(int gridX, int gridY, int gridWidth, JPanel panel, JTextArea textArea ,JScrollPane jsp)
    {
        textArea.setLineWrap(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        jsp.setViewportView(textArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(jsp, gbc);
    }
    
    private void repeatRadioButtonLayout(int gridX, int gridY, double weightX, double weightY, JPanel panel, JRadioButton radioButton)
    {
        GridBagConstraints gbcRadioButton = new GridBagConstraints();
        gbcRadioButton.gridx = gridX;
        gbcRadioButton.gridy = gridY;
        gbcRadioButton.weightx = weightX;
        gbcRadioButton.weighty = weightY;
        gbcRadioButton.fill = GridBagConstraints.BOTH;
        
        panel.add(radioButton, gbcRadioButton);

    }

    @Override
    public JPanel buildExamPanel(ExamQuestion examQuestion) {
        return buildMultipleChoiceForm(false, examQuestion);
    }

    @Override
    public JPanel buildEditableExamPanel() {
        
         return buildMultipleChoiceForm(true, null);
    }
    
}
