/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

/**
 *
 * @author tri.le
 */
public class User {
    String sessionID;
    int userType;
    int userID;
    String userFirstName;

    public User(String sessionID, int userType, int userID, String userFirstName) {
        this.sessionID = sessionID;
        this.userType = userType;
        this.userID = userID;
        this.userFirstName = userFirstName;
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
