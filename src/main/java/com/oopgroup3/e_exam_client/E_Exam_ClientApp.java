/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionFormControl;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionMultipleChoiceFormControl;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionTrueFalseControl;
import com.oopgroup3.e_exam_client.Threads.ClientRegisterThread;
import com.oopgroup3.e_exam_client.Threads.ClientLoginThread;
import com.oopgroup3.e_exam_client.Threads.SaveExamCreationThread;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ClientServerResponseThread;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.Threads.DeleteExamThread;
import com.oopgroup3.e_exam_client.Threads.LoadExamThread;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import javax.swing.JList;

/**
 *
 * @author Kyle
 */
public class E_Exam_ClientApp 
{
    /* Thread pooling usage of EXECUTOR to run Runables / callables or java swingworkers */
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(8);
    
    /* Hold a reference to the GUI so that we can access its getters / setters */
    private static E_Exam_Client_GUI GUI;
    
    /* Message passing class for inter thread communication */
    private static ResponseSharedData responseSharedData = new ResponseSharedData();
    
    private static E_Exam Exam;
        
    public static void main(String[] args) {
         
        
        /***********************************************
        * Setup the GUI frame by placing it on the EDT *
        ************************************************/
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
               GUI = new E_Exam_Client_GUI();
               Exam = new E_Exam(GUI, responseSharedData, EXECUTOR);
               
               GUI.setVisible(true);
               
               GUI.addWindowListener(new WindowAdapter() 
               {
                    @Override
                    public void windowClosed(WindowEvent e) 
                    {
                        EXECUTOR.shutdownNow();
                        System.exit(0);
                    }
               });
            }
        });
        
        /*********************************************************************************/
        /* Start background thread for handling incomming messages from server to client */
        /*********************************************************************************/
        EXECUTOR.execute(new ClientServerResponseThread(EXECUTOR, responseSharedData));
        
        
        /* Binding thread event handlers to button events here */
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                /* Login Panel button event for handling user authenthication */
                JButton loginButton = GUI.getLoginButton();
                //Getting Jbutton reference from gui and adding a button event listner
                loginButton.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        //on button click event triggered, create and execute a new thread to handle the task.
                        //These can be swing workers / runnable / callables
                        EXECUTOR.execute(new ClientLoginThread(GUI, Exam, EXECUTOR, responseSharedData));   
                          
                    }
                });
                
                JButton cancelLoginButton = GUI.getCancel_login_btn();
                cancelLoginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        GUI.getUsername_txtField().setText("");
                        GUI.getPassword_txtField().setText("");
                        
                    }
                });
                
                /* Register Panel button event for handling registration of new user */
                JButton registerButton = GUI.getRegister_btn();
                registerButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        //on button click event triggered, create and execute a new thread to handle the task.
                        //These can be swing workers / runnable / callables
                        if (!String.valueOf(GUI.getRegisterPassword_txtField().getPassword()).equals(String.valueOf(GUI.getRegisterPasswordConfirm_txtField().getPassword()))){
                            GUI.getRegister_incorrectPass_label().setVisible(true);
                        }
                        else {
                            GUI.getRegister_incorrectPass_label().setText("");
                            GUI.getRegister_incorrectPass_label().setVisible(false);
                            EXECUTOR.execute(new ClientRegisterThread(GUI ,responseSharedData));   
                        }
                    }
                });    
                
                JButton registerCancelButton = GUI.getRegister_cancel_btn();
                registerCancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        
                        GUI.getRegisterUsername_txtField().setText("");
                        GUI.getRegisterPassword_txtField().setText("");
                        GUI.getRegisterPasswordConfirm_txtField().setText("");
                        GUI.switchCardView("login");
        
                    }
                });
                  
                
                /*******************************************************************  
                *   Teacher control panel button event for switching to the        *
                *   creating exams view                                            *
                ********************************************************************/                
                JButton createExam = GUI.getCreateExamBtn();
                createExam.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        print(User.getUserInstance().getUserFirstName());
                        ExamFormCreationManager.getInstanceExamFormCreationManager().setRootPanel(GUI.getExamCreationFormPanel());
                        GUI.switchCardView("createExam");
                    }
                });
                
                /*******************************************************************
                * Teacher control panel button event for saving a created exam     *
                * into the database on the server.                                 *
                ********************************************************************/
                JButton createExamForm = GUI.getCreateExamFormBtn();
                createExamForm.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        if(!GUI.getCreateExamNameTextField().getText().equals(""))
                        {    
                            EXECUTOR.execute(new SaveExamCreationThread("CreateExam", Exam, responseSharedData));
                        }
                        else
                        {
                            print("Exam name invalid");
                        }
                    }
                });
                
                
                /*******************************************************************
                * Teacher control panel button event for deleting a created exam     *
                * into the database on the server.                                 *
                ********************************************************************/
                JButton deleteExamBtn = GUI.getDeleteExamBtn();
                deleteExamBtn.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        EXECUTOR.execute(new DeleteExamThread(Exam, responseSharedData));
                    }
                });
                
                
                
                
                JButton addTrueFalseForm = GUI.getTrueFalseFormAddBtn();
                addTrueFalseForm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ExamQuestionFormControl eqfc = new ExamQuestionTrueFalseControl();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().addExamQuestion(eqfc, null, true);  
                    }
                });
                
                JButton addMultipleChoiceForm = GUI.getMultipleChoiceFormAddBtn();
                addMultipleChoiceForm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        ExamQuestionFormControl eqfc = new ExamQuestionMultipleChoiceFormControl();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().addExamQuestion(eqfc, null,true);
                    }
                });
                
                JButton createExamFormBackButton = GUI.getExamCreationBackBtn();
                createExamFormBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        GUI.getCreateExamNameTextField().setText("");
                        ExamFormCreationManager.getInstanceExamFormCreationManager().getRootPanel().removeAll();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().resetAtomicInts();
                        GUI.switchCardView("teacher");
                    }
                });
                
                /*  Teacher control panel button event for editing exams */    
                JButton editExamForm = GUI.getEditExamBtn();
                editExamForm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        ExamFormCreationManager.getInstanceExamFormCreationManager().setRootPanel(GUI.getExamFormEditor_panel());
                        print("Selected Exam ID: " + Exam.getSelectedExamID());
                        if(Exam.getSelectedExamID() > -1)
                        {
                            EXECUTOR.execute(new LoadExamThread(Exam.getSelectedExamID(), true, responseSharedData));
                            GUI.switchCardView("editExam");
                        }
                    }
                });
                
                JButton updateEditExam = GUI.getEditExamFormBtn();
                updateEditExam.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {

                        EXECUTOR.execute(new SaveExamCreationThread("EditExam", Exam, responseSharedData));
    
                    }
                });
                
                JButton addTrueFalseFormEdit = GUI.getTrueFalseFormAddEditBtn();
                addTrueFalseFormEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        ExamQuestionFormControl eqfc = new ExamQuestionTrueFalseControl();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().addExamQuestion(eqfc, null, true);  
                    }
                });
                
                JButton addMultipleChoiceFormEdit = GUI.getMultipleChoiceFormAddEditBtn();
                addMultipleChoiceFormEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        ExamQuestionFormControl eqfc = new ExamQuestionMultipleChoiceFormControl();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().addExamQuestion(eqfc, null,true);
                    }
                });

                
                JButton editFormBackButton = GUI.getEditReturnToTeacherPanelBtn();
                editFormBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        ExamFormCreationManager.getInstanceExamFormCreationManager().getRootPanel().removeAll();
                        ExamFormCreationManager.getInstanceExamFormCreationManager().resetAtomicInts();
                        GUI.switchCardView("teacher");
                    }
                });

                /*
                    Student Panel Controls
                */
                JButton studentTakeExam = GUI.getStudentTakeExamBtn();
                studentTakeExam.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ExamFormCreationManager.getInstanceExamFormCreationManager().setRootPanel(GUI.getExamFormContainer());
                        print("Selected Exam ID: " + Exam.getSelectedExamID());
                        
                        EXECUTOR.execute(new LoadExamThread(Exam.getSelectedExamID(), true, responseSharedData));
                        GUI.switchCardView("exam");
                        
                    }
                });
                
                
                /*************************************************************** 
                * Teacher control panel button event for saving an edited exam *
                * into the database on the server.                             *  
                ****************************************************************/
                
                
                
                



                
                
                
                /****************************************
                 * End of button handler binding block  *
                 ****************************************/ 
            };   
        });              
    }  
}
