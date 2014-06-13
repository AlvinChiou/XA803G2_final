package com.dr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.e;

import com.doctor.model.DoctorService;
import com.doctor.model.DoctorVO;
import com.oreilly.servlet.MultipartRequest;

public class DrServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();

		MultipartRequest multi = null;
		String action = null;

		if (contentType.startsWith("multipart/form-data")) {
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"backend/dr/photo"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}

		// System.out.println("contentType=" + contentType);
		// System.out.println("action=" + action);

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("drNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J��v�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer drNo = null;
				try {
					drNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��v�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);
				if (doctorVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("doctorVO", doctorVO); // ��Ʈw���X��doctorloyeeVO����,�s�Jreq
				String url = "/backend/dr/listOneDr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer drNo = new Integer(req.getParameter("drNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("doctorVO", doctorVO); // ��Ʈw���X��doctorVO����,�s�Jreq
				String url = "/backend/dr/update_dr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_dr_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_dr_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = multi.getParameter("requestURL");

			try {
				/**************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				Integer drNo = new Integer(multi.getParameter("drNo").trim());
				String drName = multi.getParameter("drName").trim();
				String drExp = multi.getParameter("drExp").trim();
				String drSex = multi.getParameter("drSex").trim();
				
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);

				byte[] buffer = null;
				File drPicFile = multi.getFile("drPic");
				if (drPicFile == null) {
					buffer = doctorVO.getDrPic();
				} else {
					FileInputStream fis = new FileInputStream(drPicFile);
					int len = fis.available();
					buffer = new byte[len];
					fis.read(buffer);
				}
				
				java.sql.Date drBirth = null;
				try {
					drBirth = java.sql.Date.valueOf(multi.getParameter(
							"drBirth").trim());
				} catch (IllegalArgumentException e) {
					drBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String drAdd = multi.getParameter("drAdd").trim();
				String drTel = multi.getParameter("drTel").trim();
				

				doctorVO.setDrName(drName);
				doctorVO.setDrExp(drExp);
				doctorVO.setDrSex(drSex);
				doctorVO.setDrPic(buffer);
				doctorVO.setDrBirth(drBirth);
				doctorVO.setDrAdd(drAdd);
				doctorVO.setDrTel(drTel);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("doctorVO", doctorVO); // �t����J�榡���~��drVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/dr/update_dr_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** * 2.�}�l�ק���*****************************************/
				
				doctorVO = drSvc.updateDr(drNo, drName, drExp, drSex, buffer, drBirth, drAdd, drTel);

				/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/**************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dr/update_dr_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			
				String drName = multi.getParameter("drName").trim();
				String drExp = multi.getParameter("drExp").trim();
				String drSex = multi.getParameter("drSex").trim();
				
				File drPicFile = multi.getFile("drPic");
				byte[] buffer = null;
				if(drPicFile == null || drPicFile.length()==0){
					errorMsgs.add("�Ϥ����i���ť�");
				}else{
				FileInputStream fis = new FileInputStream(drPicFile);
				int len = fis.available();
				buffer = new byte[len];
				fis.read(buffer);
				fis.close();
				}
				
				
				java.sql.Date drBirth = null;
				try {
					drBirth = java.sql.Date.valueOf(multi.getParameter("drBirth").trim());
				} catch (IllegalArgumentException e) {
					drBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String drAdd = multi.getParameter("drAdd").trim();
				String drTel = multi.getParameter("drTel").trim();
								
				DoctorVO doctorVO = new DoctorVO();

				doctorVO.setDrName(drName);
				doctorVO.setDrExp(drExp);
				doctorVO.setDrSex(drSex);
				doctorVO.setDrPic(buffer);
				doctorVO.setDrBirth(drBirth);
				doctorVO.setDrAdd(drAdd);
				doctorVO.setDrTel(drTel);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("doctorVO", doctorVO); // �t����J�榡���~��doctorVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/dr/addDr.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				
				DoctorService drSvc = new DoctorService();
			
				doctorVO = drSvc.addDr(drName, drExp, drSex, buffer, drBirth, drAdd, drTel);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/backend/dr/listAllDr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dr/addDr.jsp");
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
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer drNo = new Integer(req.getParameter("drNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);
				drSvc.deleteDr(drNo);
				
				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL ;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
