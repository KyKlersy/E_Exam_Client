package com.oopgroup3.e_exam_client.ExamQuestionClasses;

import com.oopgroup3.e_exam_client.Interfaces.BuildPanelInterface;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * Singleton class that is in charge of maintaining a map of all JPanels
 * name by UUID set when the panel is created by ether one of ExamQuestionFormControls children. 
 * It also holds the JPanel root that all dynamic exam form controls are added to.
 * This mapping along with storing the root JPanel allows classes that use this
 * singleton to be able to access the form contents.
 * 
 * This can be seen by looking at the SaveExamCreationThread inside
 * the Threads folder of this project.
 * 
 * @author Kyle
 */
public class ExamFormCreationManager 
{
    private static ExamFormCreationManager instance;
    private final Map<String, ExamQuestionFormControl> examHashMap; 
    private final ConcurrentHashMap<String, JPanel> removeButtonHashMap;
    private JPanel rootPanel = null;
    private AtomicInteger atomicIntegerQuestionNumber;
    
    private ExamFormCreationManager()
    {
        examHashMap = Collections.synchronizedMap(new LinkedHashMap<>());
        removeButtonHashMap = new ConcurrentHashMap<>();
        atomicIntegerQuestionNumber = new AtomicInteger(0);
    }
    
    public static synchronized ExamFormCreationManager getInstanceExamFormCreationManager()
    {
        if(instance == null)
        {
            instance = new ExamFormCreationManager();
        }
        return instance;
    }
    
    public void setRootPanel(JPanel rootPanel)
    {
        this.rootPanel = rootPanel;
    }
    
    public JPanel getRootPanel()
    {
        return rootPanel;
    }

    public void addExamQuestion(ExamQuestionFormControl examQuestionForm, ExamQuestion examQuestion, boolean editable)
    {
        if(rootPanel != null)
        {
            atomicIntegerQuestionNumber.getAndIncrement();
            examQuestionForm.setFormQuestionNumber(atomicIntegerQuestionNumber);
            BuildPanelInterface bpi = examQuestionForm;
            JPanel newFormPAnel;
            if(editable)
            {
                newFormPAnel = bpi.buildEditableExamPanel(examQuestion);
            }
            else
            {
                newFormPAnel = bpi.buildExamPanel(examQuestion);
            }
            
            examHashMap.put(newFormPAnel.getName(), examQuestionForm);
            
            JButton removeButton = examQuestionForm.removeFormBtn();
            removeButtonHashMap.put(removeButton.getName(), newFormPAnel);
            removeButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try 
                            {
                                removeFormControl((JButton)ae.getSource());
                            } catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                                
                        }
                    });  
                    
                    rootPanel.repaint();
                }     
            });
            
            rootPanel.add(newFormPAnel);
            rootPanel.revalidate();
        }
    }
    
    public ExamQuestionFormControl getExamQuestionFormControl(String panelName)
    {  
        return examHashMap.get(panelName);
    }
    
    private void removeFormControl(JButton button)
    {
        synchronized(this)
        {
            JPanel panel = removeButtonHashMap.get(button.getName());
            ExamQuestionFormControl panelEQFC;
            
            rootPanel.remove(panel);

            removeButtonHashMap.remove(button.getName());
            examHashMap.remove(panel.getName());
            atomicIntegerQuestionNumber.getAndDecrement();

            Component components[] = rootPanel.getComponents();
            int questionNumber = 1;
            for(Component component : components)
            {
                panelEQFC = examHashMap.get(component.getName());
                print(panelEQFC.getQuestionNumber());
                panelEQFC.setQuestionNumber(questionNumber);
                panelEQFC.updateQuestionNumber();
                questionNumber++;        
            }
            
        }
    }
       
    
    
    public void debugPrintKeys()
    {

        examHashMap.entrySet().forEach(entry -> 
        {
            print("Key: " + entry.getKey());
        });
        
    }
            
}
