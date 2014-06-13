package com.emp.controller;

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

import com.emp.model.EmployeeService;
import com.emp.model.EmployeeVO;
import com.oreilly.servlet.MultipartRequest;

public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();
		res.setContentType("text/html; charset=Big5");
		MultipartRequest multi = null;
		String action = null;

		if (contentType != null &&contentType.startsWith("multipart/form-data")) {
			multi = new MultipartRequest(req, getServletContext().getRealPath("emp/photo"), 5 * 1024 * 1024, "UTF-8");
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

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("empNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer empNo = null;
				try {
					empNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);
				if (employeeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��employeeVO����,�s�Jreq
				String url = "/backend/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer empNo = new Integer(req.getParameter("empNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��employeeVO����,�s�Jreq
				String url = "/backend/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = multi.getParameter("requestURL");

//			try {
				/**************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				Integer empNo = new Integer(multi.getParameter("empNo").trim());
				String empName = multi.getParameter("empName").trim();

				java.sql.Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(multi.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String empTel = multi.getParameter("empTel").trim();
				String empSex = multi.getParameter("empSex").trim();
				
				String empPos = null;
				try{
					empPos = multi.getParameter("empPos").trim();
				}catch(Exception e){
					empPos = "";
					errorMsgs.add("�п��¾��");
				}

				Double empSalary = null;
				try {
					empSalary = new Double(multi.getParameter("empSalary")
							.trim());
				} catch (NumberFormatException e) {
					empSalary = 0.0;
					errorMsgs.add("�~��ж�Ʀr.");
				}

				java.sql.Date empArrDate = null;
				try {
					empArrDate = java.sql.Date.valueOf(multi.getParameter("empArrDate").trim());
				} catch (IllegalArgumentException e) {
					empArrDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				java.sql.Date empOff = null;
				try {
					empOff = java.sql.Date.valueOf(multi.getParameter("empOff")
							.trim());
				} catch (IllegalArgumentException e) {
					empOff = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String empID = multi.getParameter("empID").trim();
				String empAdd = multi.getParameter("empAdd").trim();

				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);

				byte[] buffer = null;
				File empPicFile = multi.getFile("empPic");
				if (empPicFile == null) {
					buffer = employeeVO.getEmpPic();
				} else {
					FileInputStream fis = new FileInputStream(empPicFile);
					int len = fis.available();
					buffer = new byte[len];
					fis.read(buffer);
				}

				String empPassword = multi.getParameter("empPassword").trim();
				String empEmail = multi.getParameter("empEmail").trim();
				
				
				// EmployeeVO employeeVO = new EmployeeVO();

				employeeVO.setEmpName(empName);
				employeeVO.setEmpBirth(empBirth);
				employeeVO.setEmpTel(empTel);
				employeeVO.setEmpSex(empSex);
				employeeVO.setEmpPos(empPos);
				employeeVO.setEmpSalary(empSalary);
				employeeVO.setEmpArrDate(empArrDate);
				employeeVO.setEmpOff(empOff);
				employeeVO.setEmpID(empID);
				employeeVO.setEmpAdd(empAdd);
				employeeVO.setEmpPic(buffer);
				employeeVO.setEmpPassword(empPassword);
				employeeVO.setEmpEmail(empEmail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** * 2.�}�l�ק���*****************************************/
				// EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.updateEmp(empNo, empName, empBirth, empTel,
						empSex, empPos, empSalary, empArrDate, empOff, empID,
						empAdd, buffer, empPassword, empEmail);

				/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����employeeVO����,�s�Jreq
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/**************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			
				String empName = multi.getParameter("empName").trim();

				java.sql.Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(multi.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String empTel = null;
				try{
					empTel = multi.getParameter("empTel").trim();
				}catch(Exception e){
					empTel = "";
					errorMsgs.add("�п�J�q��");
				}
				String empSex = multi.getParameter("empSex").trim();
				String empPos = null;
				try{
					empPos = multi.getParameter("empPos").trim();
				}catch(Exception e){
					empPos = "";
					errorMsgs.add("�п��¾��");
				}

				Double empSalary = null;
				try {
					empSalary = new Double(multi.getParameter("empSalary")
							.trim());
				} catch (NumberFormatException e) {
					empSalary = 0.0;
					errorMsgs.add("�~���ж�Ʀr.");
				}

				java.sql.Date empArrDate = null;
				try {
					empArrDate = java.sql.Date.valueOf(multi.getParameter(
							"empArrDate").trim());
				} catch (IllegalArgumentException e) {
					empArrDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J��¾���!");
				}

				java.sql.Date empOff = null;
				try {
					empOff = java.sql.Date.valueOf(multi.getParameter("empOff")
							.trim());
				} catch (IllegalArgumentException e) {
					empOff = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���}���!");
				}

				String empID = multi.getParameter("empID").trim();
				String empAdd = multi.getParameter("empAdd").trim();

				File empPicFile = multi.getFile("empPic");
				byte[] buffer = null;
				if(empPicFile == null || empPicFile.length()==0){
					errorMsgs.add("�Ϥ����i���ť�");
				}else{
				FileInputStream fis = new FileInputStream(empPicFile);
				int len = fis.available();
				buffer = new byte[len];
				fis.read(buffer);
				fis.close();
				}
				
				int i, z;
				StringBuilder sb = new StringBuilder();
				
				for(i = 0; i < 8; i++){
					z = (int)((Math.random()*7)%3);
					
					if(z == 1){
						sb.append((int) ((Math.random() * 10) + 48));
					}else if (z == 2) { // ��j�g�^��
				        sb.append((char) (((Math.random() * 26) + 65)));
				    }else {             // ��p�g�^��
				        sb.append(((char) ((Math.random() * 26) + 97)));
				    }
				}
					
				String empPassword = sb.toString();
				String empEmail = multi.getParameter("empEmail").trim();
				if(empEmail == null || empEmail.length() == 0){
					errorMsgs.add("�п�JEMAIL");
				}
				
				// Blob empPicBlob = req.get("empPic");
				// byte[] empPic =empPicBlob.getBytes(1,
				// (int)empPicBlob.length());
				// byte[] empPic = byte[] req.getParameter("empPic");

				EmployeeVO employeeVO = new EmployeeVO();

				employeeVO.setEmpName(empName);
				employeeVO.setEmpBirth(empBirth);
				employeeVO.setEmpTel(empTel);
				employeeVO.setEmpSex(empSex);
				employeeVO.setEmpPos(empPos);
				employeeVO.setEmpSalary(empSalary);
				employeeVO.setEmpArrDate(empArrDate);
				employeeVO.setEmpOff(empOff);
				employeeVO.setEmpID(empID);
				employeeVO.setEmpAdd(empAdd);
				employeeVO.setEmpPic(buffer);
				employeeVO.setEmpPassword(empPassword);
				employeeVO.setEmpEmail(empEmail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.addEmp(empName, empBirth, empTel, empSex,
						empPos, empSalary, empArrDate, empOff, empID, empAdd,
						buffer, empPassword, empEmail);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("employeeVO", employeeVO);
				String url2 = "/backend/emp/JavaMailProccess.jsp";
				RequestDispatcher sendMail = req.getRequestDispatcher(url2);
				sendMail.include(req, res);
				
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			
//			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer empNo = new Integer(req.getParameter("empNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);
				empSvc.deleteEmp(empNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = requestURL + "?whichPage" + whichPage ;
				req.setAttribute("listOneEmp", empSvc.getOneEmp(empNo));
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
		}
	}
}
