package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestionFormControl;
import com.oopgroup3.e_exam_client.Interfaces.SaveExamFormInterface;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * This thread handles packing and saving of exams
 * through the use of the exam form creation manager class the root panel
 * that all current exam forms reside in is retrieved and using reflection
 * on line 47 a list of all components is generated.
 * 
 * This list is used to extract the exam question form controller
 * using the panel name which when generated is stored in a hash map inside 
 * exam form creation manager the abstract parent can polymorphically process
 * both the exam true false and exam multiple choice forms as they inherit from
 * exam question form control. Exam question form control has interfaces
 * that both child classes implement allowing the returned concrete type
 * to be cast to the interface save exam form interface.
 * 
 * Calling this save method returns a new exam data object with all the
 * fields inside the form control.
 * These are then added to a list to be packaged into a Json method and sent
 * to the server for processing.
 * 
 * @author Kyle
 */
public class SaveExamCreationThread extends SwingWorker<Void, Object>{

    private User user;
    private String MethodCall;
    private E_Exam exam;
    private int examID = -1;
    private ResponseSharedData responseSharedData;
    private Component[] components;
    private ArrayList<ExamQuestion> examQuestions;
    private MessageWithResponse messageWithResponse ;
    private ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager(); //Fetching singleton instance
    
    public SaveExamCreationThread(String MethodName, E_Exam exam, ResponseSharedData responseSharedData) 
    {
        this.MethodCall = MethodName;
        this.exam = exam;
        this.responseSharedData = responseSharedData;
        /* 
            Component reflection here for fetching all JPanels tied to the current panel root
            this list holds the keys needed to request the concrete form class mapped to the panel.
            See Line 83 for this request.
        */
        components = efcm.getRootPanel().getComponents();
        examQuestions = new ArrayList<>();
                
        if(MethodCall.equals("EditExam"))
        {
            examID = exam.getSelectedExamID();
        }
    }

    
    
    @Override
    protected Void doInBackground() throws Exception {
        try 
        {

            JPanel examFormPanel;
            ExamQuestionFormControl eqfc;
            SaveExamFormInterface sefi;
            
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
                
                /*
                    Cast the returned examformcontrol to the interface and call
                    its save method.
                */ 
                sefi = (SaveExamFormInterface)eqfc;
                //store the returned data class into the exam list.
                examQuestions.add(sefi.saveForm());
            }
            
            //Get user singleton instance.
            user = User.getUserInstance();
            
            String jsonObject;
            Gson gson = new Gson();
            
            //Message creation here to be sent to the server.
            AbstractListResponse<ExamQuestion> abstractListResponse = new AbstractListResponse<>();
            abstractListResponse.constuctList(examQuestions);
            //Bloody requirement of java and type erasure... to properly seralize this into json
            //we have to setup a type case explicitly.
            Type type = new TypeToken<AbstractListResponse<ExamQuestion>>(){}.getType();
            //Create the list json string representing the abstractlistresponse class
            jsonObject = gson.toJson(abstractListResponse,type);
            
            //Message parameters user id and exam name from exam name textbox
            String[] parameters = new String[3];
            parameters[0] = Integer.toString(user.getUserID());
            parameters[1] = Integer.toString(examID);
            parameters[2] = exam.getGUI().getCreateExamNameTextField().getText();
            
            /*
                This is a double packed json message, the outer message houses 
                the users session info, the method to call on the server
                and the inner json packed string.
            */            
  
            messageWithResponse = new MessageWithResponse(user.getSessionID(), MethodCall, parameters, jsonObject);
            //This type of message also has a blocking method to wait for the response, to use this feature
            //response shared data is the blocking implementation that allows data to be recieved from
            //the dedicated ClientResponseThread inside the folder ServerResponses
            //When the return data is capatured in this thread it is placed inside the
            //messageWithResponse that was shared with the ClientResponse thread.
            responseSharedData.produce(messageWithResponse);
            try 
            {
                Socket sendSocket = new Socket("127.0.0.1",64023);    

                messageWithResponse.send(sendSocket);
                
            } 
            catch (UnknownHostException e)
            {
             e.printStackTrace();
            }
            catch(IOException ioe)
            {
             ioe.printStackTrace();
            } 
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        String msg = "";
        try 
        {
            //Here we attempt to get our returned data, ether we get it or we
            //block until the data is returned
            msg = messageWithResponse.getReturnData();
            print("Returned message" + msg);
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //In this simple case a success message is sent back telling
        //this thread it is now ok to call the updatelist method.
        //Which will refetch the data and update the list.
        if(msg.equals("Success"))
        {
            efcm.resetAtomicInts();
            efcm.getRootPanel().removeAll();
            exam.getExamList().updateList();
            
        }
        
        return null;
    }
    
    @Override
    public void done()
    {
        exam.getGUI().switchCardView("teacher"); 
    }
    
}
