package com.func.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.func.model.FuncService;
import com.func.model.FuncVO;

public class FuncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Stroe this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("funcno");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("�п�J�v���\��s��");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer funcno = null;
				try{
					funcno = new Integer(str);
				}catch (Exception e){
					errorMsgs.add("�v���\��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/	
				FuncService funcSvc = new FuncService();
				FuncVO funcVO = funcSvc.getOneFunc(funcno);
				if(funcVO == null){
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("funcVO", funcVO);
				String url = "/func/listOneFunc.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
//			}catch(Exception e){
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
			
		}
	}

}
