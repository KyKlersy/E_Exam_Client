/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.MessagingClasses;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Helper class for decoding abstract list response classes
 * Thanks to java and type erasure this known ahead of time type
 * must be passed to get a return object of the correct type.
 * 
 * And they said java is supposed to be a dynamic language hahaha.
 * @author Kyle
 */
public class AbstractListDecoder 
{

    public AbstractListDecoder(){ /*Empty Default Constructor */}
    
    /**
     * This method is used to parse an abstract list response class
     * it requires a type to be known ahead of time for the return type
     * the json string to decode and a in place type token for the Gson library
     * to correctly unpack the typed list inside.
     * @param <T>
     * @param jsonString
     * @param typeToken
     * @return
     */
    public <T> AbstractListResponse<T> parseAbstractListResponse(String jsonString, TypeToken typeToken)
    {
        return new GsonBuilder()
                .create()
                .fromJson(jsonString, typeToken.getType());
    }
}
