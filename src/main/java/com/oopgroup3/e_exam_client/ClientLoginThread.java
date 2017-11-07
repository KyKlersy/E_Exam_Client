
package com.oopgroup3.e_exam_client;

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
    public ClientLoginThread(JTextField usernameField, JPasswordField passwordField)
    {
        this.username = usernameField;
        this.password = passwordField;
    }

    @Override
    protected String doInBackground() 
    {
        return ("Username: " + username.getText() + " Password: " + String.valueOf(password.getPassword()));
        
    }
    
    @Override
    protected void done()
    {
        try
        {
            
            System.out.println(get());
            
        }
        catch (Exception ignore)
        {
            
        }
    }
    
}
