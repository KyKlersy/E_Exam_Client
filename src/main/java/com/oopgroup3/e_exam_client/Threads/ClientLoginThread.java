
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.google.gson.Gson;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.User;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class ClientLoginThread extends SwingWorker<String, Object>{

    private final E_Exam_Client_GUI GUI;
    private final JTextField username;
    private final JPasswordField password;
    private final CardLayout cardLayoutManager;
    private final JPanel cardContainer;
    private User returnedUser;
    private User user;
    private ResponseSharedData responseSharedData;
    private final ExecutorService EXECUTOR;

    
    public ClientLoginThread(E_Exam_Client_GUI GUI, ExecutorService executor, ResponseSharedData responseSharedData)
    {
        this.GUI = GUI;
        this.username = GUI.getUsernameTextField();
        this.password = GUI.getPassword_txtField();
        this.cardLayoutManager = GUI.getCardLayoutManager();
        this.cardContainer = GUI.getCardContainer();   
        this.EXECUTOR = executor;
        this.responseSharedData = responseSharedData;
        
    }

    @Override
    protected String doInBackground() 
    {
        MessageWithResponse responseMessage;
        String methodName = "Login";
        String SessionID = "";
        String[] params = new String[2];
        params[0] = this.username.getText();
        params[1] = String.valueOf(this.password.getPassword());
        
        responseMessage = new MessageWithResponse(SessionID, methodName, params);
        responseSharedData.produce(responseMessage);
        
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            responseMessage.send(sendSocket);
        } 
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        String msg = "";

        System.out.println("Attempting to get user");
        try 
        {
            msg = responseMessage.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
        System.out.println("message future: " + msg);

        if(!msg.equals("Failed"))
        {
            Gson gson = new Gson();
            returnedUser = gson.fromJson(msg, User.class);
            user = User.getUserInstance();
            user.setSessionID(returnedUser.getSessionID());
            user.setUserID(returnedUser.getUserID());
            user.setUserType(returnedUser.getUserType());
            user.setUserFirstName(returnedUser.getUserFirstName());
        }
        
        return ("Username: " + user.getUserFirstName() + " UserID: " + user.getUserID()+ " UserType: "+ user.getUserType()+ " SessionID: " + user.getSessionID());
        
    }
    
    @Override
    protected void done()
    {
        try
        {
            
            if(user.isAuthenticated() && user.getUserType() == 1)
            {
                cardLayoutManager.show(cardContainer, "student");
            }
            else if(user.isAuthenticated() && user.getUserType() == 2)
            {
                /* User is of type teacher create a singleton instance of the exam creation manager. */
                ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
                efcm.setRootPanel(GUI.getExamCreationFormPanel());
                cardLayoutManager.show(cardContainer, "teacher");
            }
            else
            {
                /* Todo write and display error message informing user of failed login */
            }
            
            
        }
        catch (Exception ignore)
        {
            
        }
    }
    
}
