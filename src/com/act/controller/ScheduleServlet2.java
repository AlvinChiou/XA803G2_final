package com.act.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.act.model.ActDAO;

public class ScheduleServlet2 extends HttpServlet{    
    
	Timer timer ; 
    int count = 0;        
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init(){        
        
    	Calendar now = new GregorianCalendar();
    	timer = new Timer();
        Calendar cal = new GregorianCalendar(2014,Calendar.JUNE,4 , 0, 0, 0);        
        TimerTask task = new TimerTask(){
                   
            public void run(){ 
            	
            	ActDAO actDAO = new ActDAO();
            	actDAO.updateStatus("E");
            	
                System.out.println("This is Task"+ count);
                System.out.println("�u�@�Ʃw���ɶ� = " + new Date(scheduledExecutionTime()));
                System.out.println("�u�@���檺�ɶ� = " + new Date() + "\n");                
                count++;
            }
        };
        timer.scheduleAtFixedRate(task, cal.getTime(), 60*60*1000); 
    }
}
