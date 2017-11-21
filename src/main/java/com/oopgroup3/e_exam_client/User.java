package com.oopgroup3.e_exam_client;

/**
 * This class handles the responsibility of the user data returned when the 
 * user is authenticated.
 * @author tri.le
 */
public class User 
{
    private static User userInstance;
    private String sessionID;
    private int userType;
    private int userID;
    private String userFirstName;

    private User(){}
    
    public static synchronized User getUserInstance()
    {
        if(userInstance == null)
        {
            userInstance = new User();
        }
        
        return userInstance;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    
    public boolean isAuthenticated()
    {
        if(sessionID.equals(""))
        {
            return false;             
        }
        else
        {
            return true;
        }
    }
}
