/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.ExamLoader;
import com.oopgroup3.e_exam_client.ExamQuestionClasses.ExamQuestion;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import static com.oopgroup3.e_exam_client.Utils.printDebug.print;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingWorker;

/**
 *
 * @author tri.le
 */
public class LoadExamThread extends SwingWorker<String, Object>
{
    private final User user;
    private final int examID;
    private boolean editable;
    private final ResponseSharedData responseSharedData;
    private ExamLoader examLoader;
    
    public LoadExamThread(int examID, boolean editable, ResponseSharedData responseSharedData) {
        this.examID = examID;
        this.editable = editable;
        this.responseSharedData = responseSharedData;
        this.user = User.getUserInstance();
    }

    
    @Override
    protected String doInBackground() throws Exception 
    {
        try {
            

        String methodName = "GetExam";
        String SessionID = "";
        String[] params = new String[2];
        params[0] = Integer.toString(user.getUserID());
        params[1] = Integer.toString(this.examID);
        
        MessageWithResponse responseMessage;
        responseMessage = new MessageWithResponse(SessionID, methodName, params);
        responseSharedData.produce(responseMessage);
        
        //sending message
        try 
        {
            Socket sendSocket = new Socket("127.0.0.1",64023);    
            responseMessage.send(sendSocket);
        } 
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        //pull data back
        String msg = "";

        print("Attempting to get response");
        try 
        {
            msg = responseMessage.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        if(!msg.equals("Failed"))
        {
            print(msg);
            TypeToken<AbstractListResponse<ExamQuestion>> typeToken = new TypeToken<AbstractListResponse<ExamQuestion>>(){};
            AbstractListResponse<ExamQuestion> responseClass = parseAbstractListResponse(msg, typeToken);
            
            print("Response size: " + responseClass.getList().size());
            
            try 
            {
                examLoader = new ExamLoader(responseClass.getList());
                if(user.getUserType() == 1)
                {
                    examLoader.loadExam();

                }
                else if(user.getUserType() == 2 )
                {
                    if(editable)
                    {   
                        print("Loading editable exam");
                        examLoader.loadEditableExam();
                    }
                    else
                    {
                        print("Loading uneditable exam");
                        examLoader.loadExam();
                    }
                        

                }
                
            } catch (Exception e) 
            {
                e.printStackTrace();
            }

        }
                      } catch (Exception e) {
                          e.printStackTrace();
        }
        return null;
    }
    
    public <T> AbstractListResponse<T> parseAbstractListResponse(String jsonString, TypeToken typeToken)
    {
        return new GsonBuilder()
                .create()
                .fromJson(jsonString, typeToken.getType());
    }
    
}
