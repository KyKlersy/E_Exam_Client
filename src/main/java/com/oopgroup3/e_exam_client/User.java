package com.oopgroup3.e_exam_client;

/**
 * This class handles the responsibility of the user data returned when the 
 * user is authenticated.
 * @author tri.le
 */
public class User 
{
    private static User userInstance;
    private final UserData userData = new UserData();
    
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
        return userData.getSessionID();
    }

    public void setSessionID(String sessionID) {
        userData.setSessionID(sessionID);
    }

    public int getUserType() {
        return userData.getUserType();
    }

    public void setUserType(int userType) {
        userData.setUserType(userType);
    }

    public int getUserID() {
        return userData.getUserID();
    }

    public void setUserID(int userID) {
        userData.setUserID(userID);
    }

    public String getUserFirstName() {
        return userData.getUserFirstName();
    }

    public void setUserFirstName(String userFirstName) {
        userData.setUserFirstName(userFirstName);
    }
    
    public boolean isAuthenticated()
    {
        if(userData.getSessionID().equals(""))
        {
            return false;             
        }
        else
        {
            return true;
        }
    }
}
