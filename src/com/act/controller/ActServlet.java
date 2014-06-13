package com.act.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.act.model.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class ActServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// String action = req.getParameter("action");
		String contentType = req.getContentType();

		MultipartRequest multi = null;
		String action = null;

		if (contentType != null && contentType.startsWith("multipart/form-data")) {
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"front/act/photo"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
			req.setCharacterEncoding("UTF-8");
		} else {
			action = req.getParameter("action");
		}

		if ("getAll_Acts_By_Date_For_startTime".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("actStartDate");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʤ��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String actStartDate = null;
				try {
					actStartDate =str.substring(8,10)+"-"+Integer.parseInt(str.substring(5,7))+"�� "+"-"+str.substring(2,4);
					System.out.println("***"+actStartDate);
				} catch (Exception e) {
					errorMsgs.add("���ʤ�������T");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/

				HttpSession sn = req.getSession();
				sn.setAttribute("list", list);
				
				req.setAttribute("actStartDate", str);
				
				TreeSet<Integer> occupied = getOccupiedHour(list);
				System.out.println("occupied1="+occupied);
				req.setAttribute("occupied", occupied);
				
				TreeSet<Integer> actived = getActiveHour(occupied);
				System.out.println("actived1="+actived);
				req.setAttribute("actived", actived);
				
				String url = "/front/act/addAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front/act/select_page.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_Date_For_endTime".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str =  multi.getParameter("startHour");
//				System.out.println("startHour="+ str);
				int startHour = Integer.parseInt(str.substring(1,3));
				System.out.println("startHour="+ startHour);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				String actStartDate = multi.getParameter("actStartDate");
				req.setAttribute("actStartDate", actStartDate);
				/*************************** 2.�}�l�d�߸�� *****************************************/
//				ActService actSvc = new ActService();
//				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
//				if (list == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front/act/select_page.jsp");
//					failureView.forward(req, res);
//					return;// �{�����_
//				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				HttpSession sn = req.getSession();
				List<ActVO> list = (List<ActVO>) sn.getAttribute("list");
				
				TreeSet<Integer> occupied = getOccupiedHour(list);
				System.out.println("occupied2="+occupied);
				req.setAttribute("occupied", occupied);
				
				TreeSet<Integer> actived = getActiveHour(occupied);
				req.setAttribute("actived", actived);
				System.out.println("actived2=" + actived);
				
				TreeSet<Integer> endTime = getActiveEndHour(actived , startHour );
				System.out.print("endTime2=" + endTime);
				req.setAttribute("endTime", endTime);
				
				req.setAttribute("startHour", str);
				
				String url = "/front/act/addAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_Date".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("actStartDate");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʤ��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String actStartDate = null;
				try {
					actStartDate =str.substring(8,10)+"-"+Integer.parseInt(str.substring(5,7))+"�� "+"-"+str.substring(2,4);
					System.out.println(actStartDate);
				} catch (Exception e) {
					errorMsgs.add("���ʤ�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
//				TreeSet<Integer> ts = getOccupiedHour(list);
//				System.out.println(ts);
//				req.setAttribute("occupied", ts);
				
				req.setAttribute("listAllActsByDate", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/act/listAllActsByDate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_MemNo".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʤ��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer memNo = null;
				try {
					memNo =new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("���ʤ�������T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
//				TreeSet<Integer> ts = getOccupiedHour(list);
//				System.out.println(ts);
//				req.setAttribute("occupied", ts);
				
				req.setAttribute("listAllActsByMemNo", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/act/listAllActsByMemNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("actNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer actNo = null;
				try {
					actNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���ʽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);
				if (actVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("actVO", actVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/act/listOneAct.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
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
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("actVO1", actVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/act/update_act_inputORI.jsp";
				System.out.println("URL:" + url);
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
		
		if ("getOne_For_Update_back".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);
//System.out.println("actVO:" + actVO.getActNo());
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("fromListActsByUpdate", actVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/act/update_act_input.jsp";
//				System.out.println("URL:" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_ActRegister".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("actVO1", actVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/actRegister/addActRegister.jsp";
				System.out.println("URL:" + url);
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
			// System.out.print(multi.getParameter("actNo"));
			String requestURL = multi.getParameter("requestURL");
			try {
				Integer actNo = null;
				String actName = null;
				String actContent = null;
				java.sql.Timestamp actStartTime = null;
				java.sql.Timestamp actEndTime = null;
				String actEquipment = null;
				Double actDeposit = null;
				Double actHostFee = null;
				Double actRegFee = null;
				String actStatus = null;
				Integer memNo = null;
				Integer empNo = null;

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				actNo = new Integer(multi.getParameter("actNo").trim());
				actName = multi.getParameter("actName").trim();
				actContent = multi.getParameter("actContent").trim();

//				SimpleDateFormat smpDateFormat=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				
				try {
//					actStartTime = java.sql.Timestamp.valueOf(multi.getParameter("actStartTime").trim());
					String startDate = multi.getParameter("startDate").trim();
					String startHour = multi.getParameter("startHour").trim();
					String actStart = startDate + " " +startHour ;
					actStartTime = java.sql.Timestamp.valueOf(actStart);
				} 
				catch (IllegalArgumentException e) {
					actStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				
				try {
//					actEndTime = java.sql.Timestamp.valueOf(multi.getParameter("actEndTime").trim());
					String endDate = multi.getParameter("endDate").trim();
					String endHour = multi.getParameter("endHour").trim();
					String actEnd = endDate + " " +endHour ;
					actEndTime = java.sql.Timestamp.valueOf(actEnd);
				} catch (IllegalArgumentException e) {
					actEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				
				byte[] buffer = null;
				File file = multi.getFile("actPic");
				if (file == null) {
					buffer = actVO.getActPic();
				} else {
					FileInputStream fis = new FileInputStream(file);
					int len = fis.available();
					buffer = new byte[len];
					if (len != 0) {
						fis.read(buffer);
					}
				}

				try {
					actEquipment = multi.getParameter("actEquipment").trim();
				} catch (Exception e) {
					actEquipment = "";
					errorMsgs.add("�п�J�����W�١A�Y�S���A�h��J'�L'");
				}

				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("�ж�J����O��");
				}

				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = 0.0;
					errorMsgs.add("�ж�J�D��H�O��");
				}

				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = 0.0;
					errorMsgs.add("�ж�J���W�O��");
				}
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "N";
					errorMsgs.add("�ж�J���ʪ��A");
				}
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("�ж�J�|���s��");
				}
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("�ж�J���W�O��");
				}

				// Integer deptno = new
				// Integer(req.getParameter("deptno").trim());

				ActVO vo2 = new ActVO();
				vo2.setActNo(actNo);
				vo2.setActName(actName);
				vo2.setActContent(actContent);
				vo2.setActStartTime(actStartTime);
				vo2.setActEndTime(actEndTime);
				vo2.setActPic(buffer);
				vo2.setActEquipment(actEquipment);
				vo2.setActDeposit(actDeposit);
				vo2.setActHostFee(actHostFee);
				vo2.setActRegFee(actRegFee);
				vo2.setActStatus(actStatus);
				vo2.setMemNo(memNo);
				vo2.setEmpNo(empNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO1", vo2); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				// ActService actSvc = new ActService();
				vo2 = actSvc.updateAct(actNo, actName, actContent,
						actStartTime, actEndTime, buffer, actEquipment,
						actDeposit, actHostFee, actRegFee, actStatus, memNo,
						empNo);

				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				req.setAttribute("listAllActsByMemNo", list); // ��Ʈw���X��empVO����,�s�Jreq
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("actVO1", vo2); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				
				
			//	String url = requestURL;
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} 
			catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front/act/update_act_input.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("updateForBack".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			System.out.println("hi, i'm in updateForBack");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			// System.out.print(multi.getParameter("actNo"));
			String requestURL = multi.getParameter("requestURL");
			try {
				Integer actNo = null;
				String actName = null;
				String actContent = null;
				java.sql.Timestamp actStartTime = null;
				java.sql.Timestamp actEndTime = null;
				String actEquipment = null;
				Double actDeposit = null;
				Double actHostFee = null;
				Double actRegFee = null;
				String actStatus = null;
				Integer memNo = null;
				Integer empNo = null;

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				actNo = new Integer(multi.getParameter("actNo").trim());
				actName = multi.getParameter("actName").trim();
				actContent = multi.getParameter("actContent").trim();

//				SimpleDateFormat smpDateFormat=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				System.out.println();
				try {
					actStartTime = java.sql.Timestamp.valueOf(multi.getParameter("actStartTime").trim());
//					String startDate = multi.getParameter("startDate").trim();
//					String startHour = multi.getParameter("startHour").trim();
//					String actStart = startDate + " " +startHour ;
//					actStartTime = java.sql.Timestamp.valueOf(actStart);
				} 
				catch (IllegalArgumentException e) {
					actStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				
				try {
					actEndTime = java.sql.Timestamp.valueOf(multi.getParameter("actEndTime").trim());
//					String endDate = multi.getParameter("endDate").trim();
//					String endHour = multi.getParameter("endHour").trim();
//					String actEnd = endDate + " " +endHour ;
//					actEndTime = java.sql.Timestamp.valueOf(actEnd);
				} catch (IllegalArgumentException e) {
					actEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				
				byte[] buffer = null;
				File file = multi.getFile("actPic");
				if (file == null) {
					buffer = actVO.getActPic();
				} else {
					FileInputStream fis = new FileInputStream(file);
					int len = fis.available();
					buffer = new byte[len];
					if (len != 0) {
						fis.read(buffer);
					}
				}

				try {
					actEquipment = multi.getParameter("actEquipment").trim();
				} catch (Exception e) {
					actEquipment = "";
					errorMsgs.add("�п�J�����W�١A�Y�S���A�h��J'�L'");
				}

				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("�ж�J����O��");
				}

				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = 0.0;
					errorMsgs.add("�ж�J�D��H�O��");
				}

				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = 0.0;
					errorMsgs.add("�ж�J���W�O��");
				}
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "N";
					errorMsgs.add("�ж�J���ʪ��A");
				}
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("�ж�J�|���s��");
				}
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("�ж�J���W�O��");
				}

				// Integer deptno = new
				// Integer(req.getParameter("deptno").trim());

				ActVO vo2 = new ActVO();
				vo2.setActNo(actNo);
				vo2.setActName(actName);
				vo2.setActContent(actContent);
				vo2.setActStartTime(actStartTime);
				vo2.setActEndTime(actEndTime);
				vo2.setActPic(buffer);
				vo2.setActEquipment(actEquipment);
				vo2.setActDeposit(actDeposit);
				vo2.setActHostFee(actHostFee);
				vo2.setActRegFee(actRegFee);
				vo2.setActStatus(actStatus);
				vo2.setMemNo(memNo);
				vo2.setEmpNo(empNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fromBackUpdateInput", vo2); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				// ActService actSvc = new ActService();
				vo2 = actSvc.updateAct(actNo, actName, actContent,
						actStartTime, actEndTime, buffer, actEquipment,
						actDeposit, actHostFee, actRegFee, actStatus, memNo,
						empNo);

				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				req.setAttribute("listAllActsByMemNo", list); // from listAll
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("fromBackUpdateInput", vo2); // �qupdate_act_input�ǹL�Ӫ���T�A��e�^�h
				
				ActService actSvc1 = new ActService();
				List<ActVO> listAgain  = actSvc.getAll((Map<String, String[]>)req.getSession().getAttribute("map"));
//				System.out.println("List:" + listAgain);
				req.setAttribute("listActs_ByCompositeQuery", listAgain); // ����@�����By�Ĥ@���d�ߴN��bsession��map...�@�_�ǰe��U��(�]���Ĥ@�� Query�ǰe��req..�u�|�e��/backend/act/listActsByCompositeQuery.jsp	)
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���/backend/act/listActsByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} 
			catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/act/update_act_input.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				// Integer(multi.getParameter("actNo").trim());
				String actName = multi.getParameter("actName").trim();
				String actContent = multi.getParameter("actContent").trim();

				
				java.sql.Timestamp actStartTime = null;
				try {
//					actStartTime = java.sql.Timestamp.valueOf(multi.getParameter("actStartTime").trim());
					String startDate = multi.getParameter("actStartDate").trim();
					String startHour = multi.getParameter("startHour").trim();
					String actStart = startDate + " " +startHour ;
					System.out.println(actStart);
					actStartTime = java.sql.Timestamp.valueOf(actStart);
				} 
				catch (IllegalArgumentException e) {
					actStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				java.sql.Timestamp actEndTime = null;
				try {
//					actEndTime = java.sql.Timestamp.valueOf(multi.getParameter("actEndTime").trim());
					String endDate = multi.getParameter("actStartDate").trim();
					String endHour = multi.getParameter("endHour").trim();
					String actEnd = endDate + " " +endHour ;
					actEndTime = java.sql.Timestamp.valueOf(actEnd);
				} catch (IllegalArgumentException e) {
					actEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				byte[] buffer = null;
				try{
				File actPicFile = multi.getFile("actPic");
				FileInputStream fis = new FileInputStream(actPicFile);
				int len = fis.available();
				buffer = new byte[len];
				fis.read(buffer);
				}catch(Exception e){
					buffer = null;
					errorMsgs.add("�ФW�ǷR�d�Ϥ�!!");
				}

				String actEquipment = null;
				try {
					actEquipment = new String(multi.getParameter("actEquipment").trim());
				} catch (NumberFormatException e) {
					actEquipment = null;
					errorMsgs.add("�п�J�����W�١A�Y�S���A�h��J'�L'");
				}

				Double actDeposit = null;
				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("�O�νж�Ʀr.");
				}

				Double actHostFee = null;
				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = null;
					errorMsgs.add("�O�νж�Ʀr.");
				}

				Double actRegFee = null;
				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = null;
					errorMsgs.add("�O�νж�Ʀr.");
				}
				String actStatus = "";
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "";
					errorMsgs.add("�п�J���ʪ��A");
				}
				Integer memNo = 0000;
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("�п�J�|���s��");
				}
				Integer empNo = 0000;
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("�п�J¾���s��");
				}

				// Integer deptno = new
				// Integer(req.getParameter("deptno").trim());

				ActVO vo3 = new ActVO();
				// vo3.setActNo(actNo);
				vo3.setActName(actName);
				vo3.setActContent(actContent);
				vo3.setActStartTime(actStartTime);
				vo3.setActEndTime(actEndTime);
				vo3.setActPic(buffer);
				vo3.setActEquipment(actEquipment);
				vo3.setActDeposit(actDeposit);
				vo3.setActHostFee(actHostFee);
				vo3.setActRegFee(actRegFee);
				vo3.setActStatus(actStatus);
				vo3.setMemNo(memNo);
				vo3.setEmpNo(empNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ActVO", vo3); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ActService actSvc = new ActService();
				vo3 = actSvc.addAct(actName, actContent, actStartTime,
						actEndTime, buffer, actEquipment, actDeposit,
						actHostFee, actRegFee, actStatus, memNo, empNo);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/act/addAct.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("delete_back".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				ActService actSvc = new ActService();
				actSvc.deleteAct(actNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/listAllAct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listActs_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.�}�l�ƦX�d��***************************************/
				ActService actSvc = new ActService();
				List<ActVO> list  = actSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listActs_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front/act/listActs_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listActs_ByCompositeQueryForBack".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				/***************************2.�}�l�ƦX�d��***************************************/
				ActService actSvc = new ActService();
				List<ActVO> list  = actSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listActs_ByCompositeQuery", list); // from select to listQuery
				RequestDispatcher successView = req.getRequestDispatcher("/backend/act/listActsByCompositeQuery.jsp"); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/act/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	}
	
	public TreeSet<Integer> getOccupiedHour(List<ActVO> list) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		int startInt;
		int endInt;
		Timestamp StartTime;
		Timestamp EndTime;

		for (int j = 0; j < list.size(); j++) {

			StartTime = list.get(j).getActStartTime();
			System.out.println(StartTime.toString());
			startInt = Integer.parseInt(StartTime.toString().substring(11, 13));

			EndTime = list.get(j).getActEndTime();
			endInt = Integer.parseInt(EndTime.toString().substring(11, 13));

			for (int i = startInt; i <= endInt; i++) {
				set.add(i);
			}
		}
		return set;
	}
	
	public TreeSet<Integer> getActiveHour(TreeSet<Integer> set) {
		TreeSet<Integer> activeSet = new TreeSet<Integer>();
		
		for(int i = 10 ; i <23 ; i++){
			
				if( !set.contains(i)){
					activeSet.add(i);
				}
			
		}
		return activeSet;
	}
	
	public TreeSet<Integer> getActiveEndHour(TreeSet<Integer> set , int startHour) {
		TreeSet<Integer> activeEndSet = new TreeSet<Integer>();
			
		for(int i = startHour; i<23 ; i++){
					if(set.contains(i+1)){
						activeEndSet.add(i+1);
					}else{
						break;
					}
				}
		return activeEndSet;
	}
	
}


