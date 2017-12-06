/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

/**
 *
 * @author Kyle
 */

/**
 * enum defines user type: teacher and student
 * @author tri.le
 */
public enum UserType 
{
    Student(1),
    Teacher(2);
    
    private final int userTypeID;

    private UserType(int userTypeID) 
    {
        this.userTypeID = userTypeID;
    }
    
    public int getUserType()
    {
        return this.userTypeID;
    }
    
    public UserType setUserType(int userTypeID)
        throws IllegalArgumentException
    {
        for(UserType userType : values())
        {
            if(userTypeID == userType.getUserType())
            {
                return userType;
            }
        }
        
        throw new IllegalArgumentException("Enum Type Doesnt Exist for: " + String.valueOf(userTypeID));
    }
}
