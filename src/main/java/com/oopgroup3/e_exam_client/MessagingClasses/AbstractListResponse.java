package com.oopgroup3.e_exam_client.MessagingClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is part of the messaging classes it allows the server
 * to respond with an abstract list of type T where T is defined
 * when the message is being constructed.
 * 
 * This generic class allows a message to send back any kind of list.
 * @author Kyle
 */
public class AbstractListResponse <T>
{
    private List<T> list;
    
    public AbstractListResponse()
    {
        
    }
    
    public AbstractListResponse(ArrayList<T> arrayList)
    {
        constuctList(arrayList);
    }
    
    public final void constuctList(ArrayList<T> arrayList)
    {
        this.list = new ArrayList<>();
        this.list.addAll(arrayList);
    }
    
    public List<T> getList()
    {
        return this.list;
    }
    
}
