package com.oopgroup3.e_exam_client.Utils;

/**
 * Print utility class with global off switch.
 * @author Kyle
 */
public class printDebug 
{
    static final boolean DEBUG = false;
    
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
