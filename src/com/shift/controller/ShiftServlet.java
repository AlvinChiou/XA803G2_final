package com.shift.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.apt.controller.AptServlet;
import com.apt.controller.RegDay;
import com.shift.model.*;

public class ShiftServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("shiftNo").trim();
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String shiftNo = str;
				try {
					Integer.parseInt(shiftNo);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ShiftService shiftSvc = new ShiftService();
				ShiftVO shiftVO = shiftSvc.getOneShift(shiftNo);
				if (shiftVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("shiftVO", shiftVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/shift/listOneShift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shift/select_page.jsp");
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
				String shiftNo = req.getParameter("shiftNo");
				if ( shiftNo == null) {
				    java.sql.Date shiftDate = java.sql.Date.valueOf( req.getParameter("shiftDate") );
				    req.setCharacterEncoding("Big5");
				    String shiftPeriod = req.getParameter("shiftPeriod");
				    String shiftPeriod2 = null;
				    if( shiftPeriod != null ){
				    	shiftPeriod2 = new String( shiftPeriod.getBytes("ISO-8859-1"), "UTF-8" );
				    }					
					ShiftDAO dao =  new ShiftDAO();
					shiftNo = dao.getShiftNo(shiftDate, shiftPeriod2);
				}
				
				/***************************2.�}�l�d�߸��****************************************/
				ShiftService shiftSvc = new ShiftService();
				ShiftVO shiftVO = shiftSvc.getOneShift(shiftNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("shiftVO", shiftVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/shift/update_shift_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/listAllShift.jsp");
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
				String drNo = req.getParameter("drNo").trim();				
				String shiftPeriod = req.getParameter("shiftPeriod").trim();
				String shiftNo  = req.getParameter("shiftNo");

				java.sql.Date shiftDate = null;
				try {
					shiftDate = java.sql.Date.valueOf(req.getParameter("shiftDate").trim());
				} catch (IllegalArgumentException e) {
					shiftDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				Integer shiftMaximum = null;
				try {
					shiftMaximum = 5;
				} catch (NumberFormatException e) {
					shiftMaximum = 0;
					errorMsgs.add("�~���ж�Ʀr.");
				}


				ShiftVO shiftVO = new ShiftVO();
				shiftVO.setShiftDate(shiftDate);;
				shiftVO.setShiftPeriod(shiftPeriod);
				shiftVO.setShiftMaximum(shiftMaximum);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shiftVO", shiftVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/shift/update_shift_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ShiftService shiftSvc = new ShiftService();
				shiftVO = shiftSvc.updateShift(shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("shiftVO", shiftVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/shift-Knifeman.jsp";
				//�ק��]��s�O����̪������}�C
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/update_shift_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String shiftPeriod = req.getParameter("shiftPeriod").trim();
				String drNo = req.getParameter("drNo").trim();
				
				java.sql.Date shiftDate = null;
				try {
					shiftDate = java.sql.Date.valueOf(req.getParameter("shiftDate").trim());
				} catch (IllegalArgumentException e) {
					shiftDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				int shiftMaximum = 0;
				try {
					shiftMaximum = 5;
				} catch (NumberFormatException e) {
					shiftMaximum = 0;
					errorMsgs.add("�~���ж�Ʀr.");
				}
	
				

				ShiftVO shiftVO = new ShiftVO();
				shiftVO.setShiftDate(shiftDate);;
				shiftVO.setShiftPeriod(shiftPeriod);
				shiftVO.setShiftMaximum(shiftMaximum);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shiftVO", shiftVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/shift/addShift.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ShiftService shiftSvc = new ShiftService();
				shiftVO = shiftSvc.addShift(shiftDate, shiftMaximum, shiftPeriod, drNo);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				//�s�W��]��s�O����̪������}�C
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));
				
				String url = "/backend/shift-Knifeman.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/addShift.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date shiftDate = null;
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String shiftNo = req.getParameter("shiftNo");
				if ( shiftNo == null ) {
					    shiftDate = java.sql.Date.valueOf( req.getParameter("shiftDate") );
					    req.setCharacterEncoding("Big5");
					    String shiftPeriod = req.getParameter("shiftPeriod");
					    String shiftPeriod2 = null;
					    if( shiftPeriod != null ){
					    	shiftPeriod2 = new String( shiftPeriod.getBytes("ISO-8859-1"), "UTF-8" );
					    }					
						ShiftDAO dao =  new ShiftDAO();
						shiftNo = dao.getShiftNo(shiftDate, shiftPeriod2);
				}
				/***************************2.�}�l�R�����***************************************/
				ShiftService shiftSvc = new ShiftService();
				shiftSvc.deleteShift(shiftNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));

				String url = "/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + shiftDate;
				//String url = "/shift/listAllShift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/listAllShift.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
