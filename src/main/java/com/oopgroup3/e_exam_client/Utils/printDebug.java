/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Utils;

/**
 *
 * @author Kyle
 */
public class printDebug 
{
    static final boolean DEBUG = true;
    
    public static void print(Object obj)
    {
        if(DEBUG)
        { 
            System.out.println(obj.toString());     
        }
    }
    
    public static Boolean getDebugFlag()
    {
        return DEBUG;
    }
}
