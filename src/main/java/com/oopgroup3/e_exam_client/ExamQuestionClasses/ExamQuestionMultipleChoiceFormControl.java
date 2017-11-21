
package com.oopgroup3.e_exam_client.ExamQuestionClasses;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * This is a concrete implementation of the 4 option multiple choice form as seen
 * on Scantron exam questions. It provides the functionality for showing a 
 * block of text as the question, along with 4 sub blocks to show possible answers
 * and 4 radio buttons setup in a group so that only one can be chosen at a time.
 * 
 * @author Kyle
 */
public final class ExamQuestionMultipleChoiceFormControl extends ExamQuestionFormControl
{
    private JLabel questionNumLabel;
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
    private JButton removeFormButton;
    
    public ExamQuestionMultipleChoiceFormControl() {
        super();
               
        questionOne = new JTextArea(6,20);
        answerOne = new JTextArea(4,20);
        answerTwo = new JTextArea(4,20);
        answerThree = new JTextArea(4,20);
        answerFour = new JTextArea(4,20);
        buttonGroup = new ButtonGroup();
        aRadioButton = new JRadioButton("A");
        bRadioButton = new JRadioButton("B");
        cRadioButton = new JRadioButton("C");
        dRadioButton = new JRadioButton("D");
        questionNumLabel = new JLabel();
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
            removeFormButton = new JButton();
            removeFormButton.setName(UUID.randomUUID().toString());
            removeFormButton.setText("X");
            GridBagConstraints gbcRemoveFormButton = new GridBagConstraints( 2, 6, 1, 1, 0, 0, GridBagConstraints.CENTER,
                    GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0 );

            panel.add(removeFormButton, gbcRemoveFormButton);
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
        
        GridBagConstraints gbcQuestionNumberLabel = new GridBagConstraints();
        gbcQuestionNumberLabel.gridx = 0;
        gbcQuestionNumberLabel.gridy = 0;
        gbcQuestionNumberLabel.gridwidth = 2;
        gbcQuestionNumberLabel.fill = GridBagConstraints.BOTH;
        panel.add(questionNumLabel, gbcQuestionNumberLabel);
        
        /* Main Question at the top */
        repeatJScrollableTextAreaLayout(0, 1, 2, panel, questionOne, new JScrollPane());
        
        /* Answer text for choice A along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(0, 2, 1, panel, answerOne, new JScrollPane());
        repeatRadioButtonLayout( 0, 3, 1.0, 1.0, panel, aRadioButton);
        
        /* Answer text for choice B along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(1, 2, 1, panel, answerTwo, new JScrollPane());
        repeatRadioButtonLayout( 1, 3, 1.0, 1.0, panel, bRadioButton);
        
        /* Answer text for choice C along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(0, 4, 1, panel, answerThree, new JScrollPane());
        repeatRadioButtonLayout( 0, 5, 1.0, 1.0, panel, cRadioButton);
        
        /* Answer text for choice D along with its radio button choice below it */
        repeatJScrollableTextAreaLayout(1, 4, 1, panel, answerFour, new JScrollPane());
        repeatRadioButtonLayout( 1, 5, 1.0, 1.0, panel, dRadioButton);
        
        panel.setName(UUID.randomUUID().toString());
        Border margin = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        panel.setBorder(new CompoundBorder(border, margin));
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
    public void updateQuestionNumber() {
        questionNumLabel.setText("Question #" + super.getQuestionNumber());
    }
    
    

    @Override
    public JPanel buildExamPanel(ExamQuestion examQuestion) {
        return buildMultipleChoiceForm(false, examQuestion);
    }

    @Override
    public JPanel buildEditableExamPanel(ExamQuestion examQuestion) {
        
         return buildMultipleChoiceForm(true, examQuestion);
    }

    @Override
    public ExamQuestion saveForm() {
        return new ExamQuestion(getQuestionNumber(), getQuestionType(), questionOne.getText(), answerOne.getText(), answerTwo.getText(), answerThree.getText(), answerFour.getText());
    }

    @Override
    public JButton removeFormBtn()
    {
        return this.removeFormButton;
    }
    
    @Override
    public void setFormQuestionNumber(AtomicInteger atomicInteger) {
        super.setQuestionNumber(atomicInteger.get());
        questionNumLabel.setText("Question #" + Integer.toString(atomicInteger.get()));
    }
}
