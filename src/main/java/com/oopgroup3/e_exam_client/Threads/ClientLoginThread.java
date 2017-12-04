
package com.oopgroup3.e_exam_client.Threads;

import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamFormCreationManager;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.google.gson.Gson;
import com.oopgroup3.e_exam_client.E_Exam;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.User;
import com.oopgroup3.e_exam_client.UserData;
import com.oopgroup3.e_exam_client.UserType;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kyle
 */
public class ClientLoginThread extends SwingWorker<String, Object>{

    private final E_Exam_Client_GUI GUI;
    private final E_Exam exam;
    private final JTextField username;
    private final JPasswordField password;
    private final CardLayout cardLayoutManager;
    private final JPanel cardContainer;
    private UserData returnedUser;
    private User user;
    private ResponseSharedData responseSharedData;
    private final ExecutorService EXECUTOR;
    private UserType userType;
    
    public ClientLoginThread(E_Exam_Client_GUI GUI, E_Exam exam ,ExecutorService executor, ResponseSharedData responseSharedData)
    {
        this.GUI = GUI;
        this.exam = exam;
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
        SwingUtilities.invokeLater(() -> {
            GUI.getLoginButton().setEnabled(false);
        });
        
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

        print("Attempting to get user");
        try 
        {
            msg = responseMessage.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
        print("message future: " + msg);

        if(!msg.equals("Failed"))
        {
            Gson gson = new Gson();
            returnedUser = gson.fromJson(msg, UserData.class);
            user = User.getUserInstance();
            user.setSessionID(returnedUser.getSessionID());
            user.setUserID(returnedUser.getUserID());
            user.setUserType(returnedUser.getUserType());
            user.setUserFirstName(returnedUser.getUserFirstName());
            
            for(UserType ut : UserType.values())
            {
                if(ut.getUserType() == user.getUserType())
                {
                    userType = ut;
                }
            }
            
            GUI.getUsername_txtField().setText("");
            GUI.getPassword_txtField().setText("");
        }
        
        return ("Username: " + user.getUserFirstName() + " UserID: " + user.getUserID()+ " UserType: "+ user.getUserType()+ " SessionID: " + user.getSessionID());
        
    }
    
    @Override
    protected void done()
    {
        print("UserType: " + userType);
        try
        {
            
            if(user.isAuthenticated() && (userType == UserType.Student))
            {
                
                ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
                efcm.setRootPanel(GUI.getExamCreationFormPanel());
                try 
                {
                    exam.setUserType(UserType.Student);
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
                
                
                cardLayoutManager.show(cardContainer, "student");
            }
            else if(user.isAuthenticated() && (userType == UserType.Teacher))
            {
                print("User auth'd is of type teacher");
                /* User is of type teacher create a singleton instance of the exam creation manager. */
                ExamFormCreationManager efcm = ExamFormCreationManager.getInstanceExamFormCreationManager();
                efcm.setRootPanel(GUI.getExamCreationFormPanel());
                try 
                {
                    exam.setUserType(UserType.Teacher);
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
                
                
                
                cardLayoutManager.show(cardContainer, "teacher");
            }
            else
            {
                /* Todo write and display error message informing user of failed login */
            }
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        GUI.getLoginButton().setEnabled(true);
    }
    
}
