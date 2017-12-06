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
public class AssignedExamData 
{
    private String examName;
    private String username;

    public AssignedExamData(String examName, String username) {
        this.examName = examName;
        this.username = username;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    @Override
    public String toString()
    {
        return ("Username: " + getUsername() + " Exam Name: " + getExamName());
    }
}
