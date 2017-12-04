/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.MessagingClasses;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author Kyle
 */
public class AbstractListDecoder 
{

    public AbstractListDecoder(){ /*Empty Default Constructor */}
    
    public <T> AbstractListResponse<T> parseAbstractListResponse(String jsonString, TypeToken typeToken)
    {
        return new GsonBuilder()
                .create()
                .fromJson(jsonString, typeToken.getType());
    }
}
