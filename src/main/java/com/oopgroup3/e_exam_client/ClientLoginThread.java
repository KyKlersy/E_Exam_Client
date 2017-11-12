
package com.oopgroup3.e_exam_client;

import com.google.gson.Gson;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Kyle
 */
public class ClientLoginThread extends SwingWorker<String, Object>{

    private final JTextField username;
    private final JPasswordField password;
    private final CardLayout cardLayoutManager;
    private final JPanel cardContainer;
    private User user = null;
    private ResponseSharedData responseSharedData;

    
    public ClientLoginThread(JTextField usernameField, JPasswordField passwordField, CardLayout cardLayoutManager, JPanel cardContainer, ResponseSharedData responseSharedData, User user)
    {
        this.username = usernameField;
        this.password = passwordField;
        this.responseSharedData = responseSharedData;
        this.cardLayoutManager = cardLayoutManager;
        this.cardContainer = cardContainer;
        this.user = user;
    }

    @Override
    protected String doInBackground() 
    {
        Message message;
        String methodName = "Login";
        String SessionID = "";
        String[] params = new String[2];
        params[0] = this.username.getText();
        params[1] = String.valueOf(this.password.getPassword());
        
        message = new Message(SessionID, methodName, params);
        
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            message.send(sendSocket);
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
            msg = responseSharedData.consume();
        } catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        
        
        System.out.println("message future: " + msg);

        if(!msg.equals("Failed"))
        {
            Gson gson = new Gson();
            user = gson.fromJson(msg, User.class);
        }
        
        
        /* Ignore this code, future code for handling data retreival and population of 2nd / 3rd panels depending on user login type.
        String ExamData;
        try 
        {
            ExamData = responseSharedData.consume();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
*/
        
        return ("Username: " + user.getUserFirstName() + " UserID: " + user.getUserID()+ " UserType: "+ user.getUserType()+ " SessionID: " + user.getSessionID());
        
    }
    
    @Override
    protected void done()
    {
        try
        {
            
            if(user.isAuthenticated() && user.userType == 1)
            {
                cardLayoutManager.show(cardContainer, "student");
            }
            else if(user.isAuthenticated() && user.userType == 2)
            {
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
