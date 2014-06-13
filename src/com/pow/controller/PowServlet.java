package com.pow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;

import com.pow.model.PowService;
import com.pow.model.PowVO;

public class PowServlet extends HttpServlet {

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
		
		if("getOne_For_Display".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.getOnePow(empno);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list); // ��Ʈw���X��powVO����,�s�Jreq
				//req.setAttribute("empno", empno);
				
				String url = "/backend/pow/listOnePow.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // ���\��� listOnePow.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			}catch (Exception e){
				throw new ServletException(e);
			}
		}
		
		if("listPow_Byfuncname".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer funcno = new Integer(req.getParameter("funcno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.listPow_Byfuncname(funcno);
				if(list.isEmpty()){                                 //list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend//select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("list", list);
				
				String url = "/backend//pow/listPow_Byfuncname.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			}catch (Exception e){
				throw new ServletException(e);
			}
		}
		
		if("getOne_For_Update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer empno = new Integer (req.getParameter("empno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.getOnePow(empno);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("list", list);
				String url = "/backend/pow/update_pow_input.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			}catch (Exception e){
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/pow/listAllPow.jsp�j�Ρi/pow/listOnePow.jsp�j�Ρi/pow/listPow_Byfuncname.jsp�j
			
//			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				PowVO powVO = new PowVO();
				Integer empno = new Integer(req.getParameter("empno"));
				
				String[] funcnoFile = req.getParameterValues("funcno");
				if(funcnoFile == null || funcnoFile.length == 0){
					errorMsgs.add("�v���ܤ֭n���d��");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("powVO", powVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pow/update_pow_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				PowService powSvc = new PowService();
				powSvc.deletePow(empno);
				
				int[] data = new int[funcnoFile.length];
				Integer funcno = null;
				
				for(int i=0;i<funcnoFile.length;i++){
					int temp = Integer.parseInt(funcnoFile[i]);
//					data[i] += temp;
					funcno = temp;
				
				powVO.setEmpno(empno);
				powVO.setFuncno(funcno);
				
				powVO = powSvc.addPow(empno, funcno);
				}
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("powVO", powVO);
				String url = requestURL;
				
				//�A���JSession��list�@��
				HttpSession session= req.getSession();
				List<PowVO> list = powSvc.getOnePow( (Integer)session.getAttribute("empNo") );                   //�N�v���s�JSESSION
				session.setAttribute("list", list);       
				
				
				
				if(requestURL.equals("listAllPow.jsp")){
					req.setAttribute("listOnePow", powSvc.getOnePow(empno));
					url = requestURL;
				}else if(requestURL.equals("/backend/pow/listOnePow.jsp")){
					req.setAttribute("listOnePow", powSvc.getOnePow(empno));
					url = requestURL;
				}else if(requestURL.equals("/backend/listPow_Byfuncname.jsp")){
					req.setAttribute("listPow_Byfuncname", powSvc.listPow_Byfuncname(funcno));
					url = requestURL;
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
//			}catch (Exception e){
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/mem/update_mem_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}

}
