/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.MessagingClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
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
