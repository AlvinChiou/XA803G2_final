package com.actregister.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;


//import com.act.model.*;
import com.actregister.model.*;

public class ActRegisterServletFront extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();
		
//		String action = req.getParameter("action");
		
		MultipartRequest multi = null;
		String action = null;
		
		if( contentType != null && contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(req,getServletContext().getRealPath("front/actRegister/photo"),5*1024*1024,"Big5");
			action = multi.getParameter("action");
			req.setCharacterEncoding("UTF-8");
		}else{
			action = req.getParameter("action");
		}
		
		if ("getAll_For_Display_By_ActNo".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("actNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�|���s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front/actRegister/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
				Integer actNo = null;
				try {
					actNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByActNo(actNo);
				if (actRegVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("allActRegsByActNo", actRegVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/actRegister/listAllActRegistersByActNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Display_By_MemNo".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByMemNo(memNo);
				if (memNo == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("allActRegsByMemNo", actRegVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/actRegister/listAllActRegisterBymemNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Display_By_MemNo_For_Delete".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByMemNo(memNo);
				if (memNo == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("allActRegsByMemNo", actRegVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/actRegister/listAllActRegisterBymemNoForDelete.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("actRegNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʳ��W�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer actRegNo = null;
				try {
					actRegNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���ʳ��W�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				ActRegisterVO actRegVO = actRegSvc.getOneActRegister(actRegNo);
				if (actRegNo == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("actRegVO", actRegVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/actRegister/listOneActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer actRegNo = new Integer(req.getParameter("actRegNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				ActRegisterVO actRegVO = actRegSvc.getOneActRegister(actRegNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("actRegVO", actRegVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/actRegister/update_actRegister_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer actRegNo = new Integer(req.getParameter("actRegNo").trim());
				String actRegName = req.getParameter("actRegName").trim();
				
				java.sql.Date actRegDateInSqlDate = java.sql.Date.valueOf( ( req.getParameter("actRegDate").trim() ) );
				java.sql.Timestamp actRegDate = new java.sql.Timestamp( actRegDateInSqlDate.getTime() );
				
				java.sql.Time actRegTimeInSqlTime = java.sql.Time.valueOf(req.getParameter("actRegTime").trim());
				java.sql.Timestamp actRegTime= new java.sql.Timestamp(actRegTimeInSqlTime.getTime());
				
				String actRegPayState = null;
				try {
					actRegPayState = req.getParameter("actRegPayState").trim();
				} catch (Exception e) {
					actRegPayState = "";
					errorMsgs.add("�п�J���A.");
				}
				
				Integer memNo = 0;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
				} catch (Exception e) {
					memNo = 0;
					errorMsgs.add("�п�J�|���s��.");
				}
				Integer actNo = 0;
				try {
					actNo = new Integer(req.getParameter("actNo").trim());
				} catch (Exception e) {
					actNo = 0;
					errorMsgs.add("�п�J���ʽs��.");
				}
				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				ActRegisterVO vo2 = new ActRegisterVO();
				vo2.setActRegNo(actRegNo);
				vo2.setActRegName(actRegName);
				vo2.setActRegDate(actRegDate);
				vo2.setActRegTime(actRegTime);
				vo2.setActRegPayState(actRegPayState);
				vo2.setMemNo(memNo);
				vo2.setActNo(actNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actRegVO", vo2); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actRegister/update_actRegister_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				vo2 = actRegSvc.updateActRegister(actRegNo , actRegName, actRegDate, actRegTime , actRegPayState, memNo, actNo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("actRegVO", vo2); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actRegister/update_actRegister_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer actNo = new Integer(req.getParameter("actNo").trim());
				
				
				String actRegName = req.getParameter("actRegName").trim();
				java.sql.Date actRegDateInSqlDate = java.sql.Date.valueOf( ( req.getParameter("actRegDate").trim() ) );//Date���~���
				java.sql.Timestamp actRegDate = new java.sql.Timestamp( actRegDateInSqlDate.getTime() );
				
				java.sql.Time actRegTimeInSqlTime = java.sql.Time.valueOf(req.getParameter("actRegTime").trim()); //Time���ɤ���
				java.sql.Timestamp actRegTime= new java.sql.Timestamp(actRegTimeInSqlTime.getTime());
				

				String	actRegPayState = new String(req.getParameter("actRegPayState").trim());
				Integer	memNo = new Integer(req.getParameter("memNo").trim());
				Integer actNo = new Integer(req.getParameter("actNo").trim());
				
				/***************************2.�}�l�s�W���***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				ActRegisterVO vo3 = actRegSvc.addActRegister(actRegName, actRegDate, actRegTime , actRegPayState, memNo, actNo);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				RequestDispatcher successView1 = req.getRequestDispatcher("/front/actRegister/JavaMailProccess.jsp");
//				successView1.include(req, res);				
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��^�|�����W����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/addActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer actRegNo = new Integer(req.getParameter("actRegNo"));
				
				/***************************2.�}�l�R�����***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				actRegSvc.deleteActRegister(actRegNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_By_MemNo_And_ActNo".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));
				Integer memNo = new Integer(req.getParameter("memNo"));
				
				/***************************2.�}�l�R�����***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				actRegSvc.deleteByMemNoAndActNo(actNo , memNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
