package com.oopgroup3.e_exam_client.Threads;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.oopgroup3.e_exam_client.E_Exam_Client_GUI;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListResponse;
import com.oopgroup3.e_exam_client.MessagingClasses.MessageWithResponse;
import com.oopgroup3.e_exam_client.ExamListData;
import com.oopgroup3.e_exam_client.MessagingClasses.AbstractListDecoder;
import com.oopgroup3.e_exam_client.ServerResponseHandler.ResponseSharedData;
import com.oopgroup3.e_exam_client.User;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingWorker;
import static com.oopgroup3.e_exam_client.Utils.printDebug.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Kyle
 */

/**
 * wake up when user logins successfully
 * will be wake up from updateList method in ExamList class
 * @author tri.le
 */

public class ExamListUpdaterThread extends SwingWorker<String, Object>
{
    private ResponseSharedData responseSharedData;
    private ArrayList examNameList;
    private ArrayList examIDList;
    private DefaultListModel defaultListModel;
    private User user = User.getUserInstance();
    private MessageWithResponse responseMessage;
    private AbstractListDecoder abstractListDecoder;

    public ExamListUpdaterThread(ResponseSharedData responseSharedData, ArrayList<String> examNameList, ArrayList<Integer> examIDList, DefaultListModel defaultListModel)
    {
        this.responseSharedData = responseSharedData;
        this.examNameList = examNameList;
        this.examIDList = examIDList;
        this.defaultListModel = defaultListModel;
        abstractListDecoder = new AbstractListDecoder();
    }

    @Override
    protected String doInBackground() 
    {
        String[] parameters = new String[2];
        parameters[0] = String.valueOf(user.getUserID());
        parameters[1] = String.valueOf(user.getUserType());
        responseMessage = new MessageWithResponse(user.getSessionID(), "GetTeacherExamList", parameters);
        responseSharedData.produce(responseMessage);
        
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
        
        String msg = "";
        try 
        {
            msg = responseMessage.getReturnData();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        
        print("Json Returned meg: " + msg);
        
        TypeToken<AbstractListResponse<ExamListData>> typeToken = new TypeToken<AbstractListResponse<ExamListData>>(){};
        AbstractListResponse<ExamListData> responseClass = abstractListDecoder.parseAbstractListResponse(msg, typeToken);
                    
        System.out.println("Size of list: " + responseClass.getList().size());
        

        responseClass.getList().forEach(item -> {
           
            examNameList.add(item.getExamName());
            examIDList.add(item.getExamID());

        });
        
        return "Complete";
    }
    
    @Override
    public void done()
    {
        
        examNameList.forEach(examName -> {
            print("List item: " + examName);
            defaultListModel.addElement(examName);
        });
    }
    
}
