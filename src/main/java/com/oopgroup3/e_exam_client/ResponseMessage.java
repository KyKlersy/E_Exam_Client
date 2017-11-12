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
public class ResponseMessage {
    
    private String workerThreadName;
    private String jsonObject;
    private String status;

    public ResponseMessage(String status, String workerThreadName, String jsonObject)
    {
        this.workerThreadName = workerThreadName;
        this.jsonObject = jsonObject;
        this.status = status;
    }

    
    public String getWorkerThreadName() {
        return workerThreadName;
    }

    public void setWorkerThreadName(String workerThreadName) {
        this.workerThreadName = workerThreadName;
    }

    public String getStatus() {
        return status;
    }
    

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }
    
    

}
