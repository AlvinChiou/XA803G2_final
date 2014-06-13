package com.drRecord.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;


import com.pet.model.PetVO;
import com.drRecord.model.*;

public class DrRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
//		String action = req.getParameter("action");
		String contentType = req.getContentType();
		
		MultipartRequest multi = null;
		String action = null;
		
		if(contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(req,getServletContext().getRealPath("drRecord/photo"),5*1024*1024,"UTF-8");
			action = multi.getParameter("action");
			req.setCharacterEncoding("UTF-8");
		}else{
			action = req.getParameter("action");
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("drRecNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer drRecNo = null;
				try {
					drRecNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���ʽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				DrRecordVO drRecVO = drRecSvc.getOneDrRecord(drRecNo);
				if (drRecVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("drRecVO", drRecVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/drRecord/listOneDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/select_page.jsp");
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
				Integer drRecNo = new Integer(req.getParameter("drRecNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				DrRecordVO drRecVO = drRecSvc.getOneDrRecord(drRecNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("drRecVO", drRecVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/drRecord/update_drRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/listAllDrRecord.jsp");
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
				Integer drRecNo = new Integer(req.getParameter("drRecNo").trim());
				
				
				Integer petNo = null ;
				try {
					petNo =new Integer(req.getParameter("petNo").trim());
				} catch (Exception e) {
					petNo = null;
					errorMsgs.add("�п�J�d���s��");
				}
				
				java.sql.Date drRecTime = null;
				try {
					drRecTime = java.sql.Date.valueOf(req.getParameter("drRecTime").trim());
				} catch (IllegalArgumentException e) {
					drRecTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String drRecPres = null;
				try {
					drRecPres = req.getParameter("drRecPres").trim();
				} catch (Exception e) {
					drRecPres = "";
					errorMsgs.add("123.");
				}
				
				Integer drNo = null;
				try {
					drNo =new Integer(req.getParameter("drNo").trim());
				} catch (Exception e) {
					drNo = null;
					errorMsgs.add("�п�J��ͽs��");
				}
				
				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				DrRecordVO vo2 = new DrRecordVO();
				vo2.setDrRecNo(drRecNo);
				vo2.setPetNo(petNo);
				vo2.setDrRecTime(drRecTime);
				vo2.setDrRecPres(drRecPres);
				vo2.setDrNo(drNo);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("drRecVO", vo2); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/update_drRecord_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				vo2 = drRecSvc.updateDrRecord(drRecNo , drRecTime, drRecPres,drNo,petNo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("drRecVO", vo2); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/drRecord/listOneDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/update_drRecord_input.jsp");
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
//				Integer actNo = new Integer(multi.getParameter("actNo").trim());
				
				Integer petNo = null;
				try {
					petNo = new Integer(multi.getParameter("petNo").trim());
				} catch (NumberFormatException e) {
					petNo = null;
					errorMsgs.add("�п�J�d���s��");
				}
				
				java.sql.Date drRecTime = null;
				try {
					drRecTime = java.sql.Date.valueOf(multi.getParameter("drRecTime").trim());
				} catch (IllegalArgumentException e) {
					drRecTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				byte[] actPic = null;
//				File actPicFile = multi.getFile("actPic");
//				FileInputStream fis = new FileInputStream(actPicFile);
//				int len = fis.available();
//				byte[] buffer = new byte[len];
//				fis.read(buffer);
//				try {
//					actPic = new bytes(req.getParameter("actPic").trim());
//				} catch (NumberFormatException e) {
//					actPic = null;
//					errorMsgs.add("11.");
//				}

				String drRecPres = null;
				try {
					drRecPres = new String(multi.getParameter("drRecPres").trim());
				} catch (NumberFormatException e) {
					drRecPres = null;
					errorMsgs.add("�п�J�B��A�Y�S���A�h��J'�L'");
				}
				
				Integer drNo = null;
				try {
					drNo = new Integer(multi.getParameter("drNo").trim());
				} catch (NumberFormatException e) {
					drNo = null;
					errorMsgs.add("�п�J�d���s��");
				}
				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				DrRecordVO vo3 = new DrRecordVO();
//				vo3.setActNo(actNo);
				vo3.setPetNo(petNo);
				vo3.setDrRecTime(drRecTime);
				vo3.setDrRecPres(drRecPres);
				vo3.setDrNo(drNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("drRecVO", vo3); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/addDrRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				DrRecordService drRecSvc = new DrRecordService();
				vo3 = drRecSvc.addDrRecord(drRecTime, drRecPres,drNo,petNo);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/backend/drRecord/listAllDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/addDrRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer drRecNo = new Integer(req.getParameter("drRecNo"));
				
				/***************************2.�}�l�R�����***************************************/
				DrRecordService drRecSvc = new DrRecordService();
				drRecSvc.deleteDrRecord(drRecNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/backend/drRecord/listAllDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/listAllDrRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
