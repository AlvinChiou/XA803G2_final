package com.apt.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apt.model.*;
import com.shift.model.ShiftDAO;
import com.shift.model.ShiftVO;

import java.sql.Date;

public class AptServlet extends HttpServlet {

	/** start�Ȧs��code ***/
	public static final int QUEUE_SIZE = 5;
	public static final long ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

	RegDay regDays[] = new RegDay[20];

	@Override
	public void init() throws ServletException {

		/*** �H�U�B�J���إߥ���20��(�]�t����)��RegDay�}�C ****/

		createRegArrayFromDB(regDays);

		/** regDays[]�s�JSerletContext(Appliation)��,�H����ܭ������ **/
		ServletContext context = getServletContext();
		context.setAttribute("regDays", regDays);
		/***/

		// �L�X�Ӳ{�b�s�bram�̪�20��
		pirntRegArray();

	}

	/** end�Ȧs��code ***/
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("hi, i've got a request!");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("gotoApt".equals(action)) { // �q����ܱ�������� �M�����ܱ�������
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/apt/addApt.jsp");
			failureView.forward(req, res);
			return;// �{�����_
		}
		/*** <forAndroidfindByPK> ***/
		if ("getOne".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String aptNo = req.getParameter("aptNo");
				if (aptNo == null || (aptNo.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				try {
					// ���|�h�]�o�� �]�������s�����W�h�٨S�X��!
					Integer.parseInt(aptNo);
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� ***************************************/
				// ���ѯu�����ݶE �~��s�W����(�g�JDB�����ӱ���)
				AptService aptSvc = new AptService();
				AptVO aptVO = aptSvc.getOneEmp(aptNo);

				JSONObject jsnObj = AptJsonHandler.toJSONObject(aptVO);
				String outStr = jsnObj.toString();
				// �e�^response���Ȥ��
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.println(outStr);

				/*************************** 3.�s�W����,�ǳ�XXX ***********/

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				// errorMsgs.add(e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/apt/addApt.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
			return;
		}
		/** <forAndroid> ***/
		if ("mkAnApt".equals(action)) { // �Ӧ�Android���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				java.sql.Date aptDate = null;
				try {
					aptDate = java.sql.Date.valueOf(req.getParameter("aptDate")
							.trim());
				} catch (IllegalArgumentException e) {
					aptDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����J���Date����!!");
				}

				String aptPeriod = req.getParameter("aptPeriod").trim();
				if (aptPeriod == null || (aptPeriod.trim()).length() == 0
						|| aptPeriod.equals("0")) {
					errorMsgs.add("�п�ܮɬq");
				}

				Integer aptNoSlip = null;

				java.sql.Timestamp aptRegTime = null;

				// if petNo == 0, means client didnt chose any pet from client
				// page.�@
				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0
						|| petNo.equals("0")) {
					errorMsgs.add("�п���d���W��s");
				}
				AptVO aptVO = new AptVO();
				aptVO.setAptDate(aptDate);

				aptVO.setAptPeriod(aptPeriod);

				aptVO.setAptNoSlip(aptNoSlip);

				aptVO.setAptRegTime(aptRegTime);

				aptVO.setPetNo(petNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/addApt.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/

				/** start�Ȧs��code ***/
				int i;
				// ��O�����@��, �ñN�ӤѪ�index�s�Ji��
				t1: for (i = 0; i < 20; i++) {
					if (regDays[i] == null) { // �p�G�O�𮧤�A�h������U�@��
						continue t1;
					}
					if (aptDate.toString().equals(
							regDays[i].getDate().toString())) {
						break t1;
					}
				}

				if (i < 20) { // �p�G�u�����ӤѪ���
					for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {// ��O�����Ӯɬq

						if (aptPeriod.equals(regDays[i].getRegPeriods()[j]
								.getPeriod())) {
							RegPeriod thatRegPeriod = regDays[i]
									.getRegPeriods()[j];

							aptRegTime = new Timestamp( // set����ɶ�
									new GregorianCalendar(
											Integer.parseInt(aptDate.toString()
													.substring(0, 4)), // ��sql.Date.getYear()
											Integer.parseInt(aptDate.toString()
													.substring(5, 7)) - 1, // ��sql.Date.getMonth()
											Integer.parseInt(aptDate.toString()
													.substring(8)), // ��sql.Date.getDayOfMonth()
											Integer.parseInt(aptPeriod
													.substring(0, 2)), // ��
																		// getHour()
											(60 / QUEUE_SIZE * (thatRegPeriod
													.calculateElements())), // ��getMinute
											0).getTimeInMillis());
							aptNoSlip = thatRegPeriod.calculateElements() + 1; // ���͸��X�P,�ھڥ��ӫ��
							aptVO.setAptRegTime(aptRegTime);
							aptVO.setAptNoSlip(aptNoSlip);
							// �s�W�ܱ����}�C
							AptDAO dao = new AptDAO();
							// ���o�̷s�������s��,�H���b�����}�C���������s���PDB�P�B,�ثe�w�����I
							int aptNo = dao.getLastAptNo() + 1;
							aptVO.setAptNo(aptNo + "");
							boolean isSuccess = thatRegPeriod.enQueue(aptVO);
							if (isSuccess) {
								// ���ѯu�����ݶE �~��s�W����
								AptService aptSvc = new AptService();
								aptVO = aptSvc.addApt(aptDate, aptPeriod,
										aptNoSlip, aptRegTime, petNo);

							} else {
								errorMsgs.add("������C�w��!����A��!");
							}
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
								RequestDispatcher failureView = req
										.getRequestDispatcher("/backend/apt/addApt.jsp");
								failureView.forward(req, res);
								return;
							}
							break;
						} // end of if(�P�_�O���ӱ����ɬq)
					}
					// ���ѯu�����ݶE �~��s�W����(�g�JDB�����ӱ���)
					// AptService aptSvc = new AptService();
					// aptVO = aptSvc.addApt(aptDate, aptPeriod, aptNoSlip,
					// aptRegTime, petNo);// end of t2 : for

					JSONObject jsnObj = AptJsonHandler.toJSONObject(aptVO);
					String outStr = jsnObj.toString();
					// �e�^response���Ȥ��
					res.setContentType("text/html; charset=UTF-8");
					PrintWriter out = res.getWriter();
					out.println(outStr);

				}// end of if (i < 20)
				/** end�Ȧs��code ***/
				/*
				 * AptService aptSvc = new AptService(); aptVO =
				 * aptSvc.addApt(aptDate, aptPeriod, aptNoSlip, aptRegTime,
				 * petNo);
				 */

				/*************************** 3.�s�W����,�ǳ�XXX ***********/

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				// errorMsgs.add(e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/apt/addApt.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
			return;
		}
		/** </forAndroid> ***/

		if ("getAll".equals(action)) {
			/*************************** �}�l�d�߸�� ****************************************/
			AptDAO dao = new AptDAO();
			List<AptVO> list = dao.getAll();

			/*************************** �d�ߧ���,�ǳ����(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list); // ��Ʈw���X��list����,�s�Jsession
			// Send the Success view
			String url = "/backend/apt/listAllApt_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}

		if ("getOne_For_Display".equals(action)
				|| "getOne_For_DisplayInTheSamePage".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("aptNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String aptNo = null;
				try {
					// ���|�h�]�o�� �]�������s�����W�h�٨S�X��!
					aptNo = str;
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				AptDAO dao = new AptDAO();
				AptVO aptVO = dao.findByPrimaryKey(aptNo);
				if (aptVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				String url = null;
				req.setAttribute("aptVO", aptVO); // ��Ʈw���X��empVO����,�s�Jreq
				if (action.equals("getOne_For_DisplayInTheSamePage")) {
					url = "/backend/apt/showApt.jsp";
				} else if ("getOne_For_Display".equals(action)) {
					url = "/backend/apt/listOneApt.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��浹listOneApt.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/select_page.jsp");
				failureView.forward(req, res);
			}
			return;
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String aptNo = req.getParameter("aptNo");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AptService aptSvc = new AptService();
				AptVO aptVO = aptSvc.getOneEmp(aptNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("aptVO", aptVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/apt/update_apt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/listAllApt.jsp");
				failureView.forward(req, res);
			}
			return;
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			String requestURL = null;
			System.out.println(requestURL = req.getParameter("requestURL"));

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				java.sql.Date aptDate = null;
				String aptDateFromReq = req.getParameter("aptDate");
				try {
					if (aptDateFromReq == null) {
						aptDateFromReq = (String) (req.getSession()
								.getAttribute("aptDate"));
					}
					aptDate = java.sql.Date.valueOf(aptDateFromReq.trim());
				} catch (IllegalArgumentException e) {
					aptDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����J���Date����!!");
				}

				String aptPeriod = req.getParameter("aptPeriod");
				if (aptPeriod == null) {
					aptPeriod = (String) (req.getSession()
							.getAttribute("aptPeriod"));
				}
				if (aptPeriod == null || (aptPeriod.trim()).length() == 0
						|| aptPeriod.equals("0")) {
					errorMsgs.add("�п�ܮɬq");
				}

				Integer aptNoSlip = null;

				java.sql.Timestamp aptRegTime = null;

				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0
						|| petNo.equals("0")) {
					errorMsgs.add("�п���d���W��s");
				}
				AptVO aptVO = new AptVO();
				aptVO.setAptDate(aptDate);

				aptVO.setAptPeriod(aptPeriod);

				aptVO.setAptNoSlip(aptNoSlip);

				aptVO.setAptRegTime(aptRegTime);

				aptVO.setPetNo(petNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if( "backend".equals( req.getRequestURI().substring(9, 16) )){
					if ((req.getContextPath()+"/backend/apt/addApt2.jsp")
							.equals(requestURL)) {
						req.setAttribute("aptVO", aptVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt2.jsp");
						failureView.forward(req, res);
						return;
					} else {
						req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt.jsp");
						failureView.forward(req, res);
						return;
					}
					} else if ( "front".equals( req.getRequestURI().substring(9, 14) ) ) {
						
						if ( ( req.getContextPath() + "/front/apt/addApt2.jsp" )
								.equals(requestURL)) {
							req.setAttribute("aptVO", aptVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front/apt/addApt.jsp");
							failureView.forward(req, res);
							return;
						} else {
							req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front/apt/addApt.jsp");
							failureView.forward(req, res);
							return;
						}
					}
					
				}

				/*************************** 2.�}�l�s�W��� ***************************************/

				/** start�Ȧs��code ***/
				int i;
				t1: for (i = 0; i < 20; i++) { // ��O�����@��
					if (regDays[i] == null) { // �p�G�O�𮧤�A�h������U�@��
						continue t1;
					}
					if (aptDate.toString().equals(
							regDays[i].getDate().toString())) {
						break t1;
					}
				}

				if (i < 20) { // �p�G�u�����ӤѪ���
					for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {// ��O�����Ӯɬq
						System.out.println(regDays[i].getRegPeriods()[j]);

						if (regDays[i].getRegPeriods()[j] == null) {
							j = 2;
							continue;
						}
						if (aptPeriod.equals(regDays[i].getRegPeriods()[j]
								.getPeriod())) {
							RegPeriod thatRegPeriod = regDays[i]
									.getRegPeriods()[j];

							aptRegTime = new Timestamp( // set����ɶ�
									new GregorianCalendar(
											Integer.parseInt(aptDate.toString()
													.substring(0, 4)), // ��sql.Date.getYear()
											Integer.parseInt(aptDate.toString()
													.substring(5, 7)) - 1, // ��sql.Date.getMonth()
											Integer.parseInt(aptDate.toString()
													.substring(8)), // ��sql.Date.getDayOfMonth()
											Integer.parseInt(aptPeriod
													.substring(0, 2)), // ��
																		// getHour()
											(60 / QUEUE_SIZE * (thatRegPeriod
													.calculateElements())), // ��getMinute
											0).getTimeInMillis());
							aptNoSlip = thatRegPeriod.calculateElements() + 1; // ���͸��X�P,�ھڥ��ӫ��
							aptVO.setAptRegTime(aptRegTime);
							aptVO.setAptNoSlip(aptNoSlip);
							// �s�W�ܱ����}�C
							AptDAO dao = new AptDAO();
							// ���o�̷s�������s��,�H���b�����}�C���������s���PDB�P�B,�ثe�w�����I
							int aptNo = dao.getLastAptNo() + 1;
							aptVO.setAptNo(aptNo + "");
							boolean isSuccess = thatRegPeriod.enQueue(aptVO);
							if (isSuccess) {
								// ���ѯu�����ݶE �~��s�W����
								AptService aptSvc = new AptService();
								aptVO = aptSvc.addApt(aptDate, aptPeriod,
										aptNoSlip, aptRegTime, petNo);

							} else {  
								String shiftPeriod = isAM( aptPeriod ) ? "�W��" : "�U��";
								System.out.println( "shiftPeriod : " + shiftPeriod);
								req.setAttribute("shiftPeriod", shiftPeriod);
								errorMsgs.add("������C�w��!����A��!");
							}
							if (!errorMsgs.isEmpty()) {
								if(  "backend".equals( req.getRequestURI().substring(9, 16) ) ){
								if ((req.getContextPath()+"/backend/apt/addApt2.jsp")
										.equals(requestURL)) {
									req.setAttribute("aptVO", aptVO);
									RequestDispatcher failureView = req
											.getRequestDispatcher("/backend/apt/addApt.jsp");
									failureView.forward(req, res);
									return;
								} else {
									req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
									RequestDispatcher failureView = req
											.getRequestDispatcher("/backend/apt/addApt.jsp");
									failureView.forward(req, res);
									return;
								}
							} else if ( "front".equals( req.getRequestURI().substring(9, 14) ) ) {
								if (( req.getContextPath() + "/front/apt/addApt2.jsp" )
									.equals(requestURL)) {
								req.setAttribute("aptVO", aptVO);
								RequestDispatcher failureView = req
										.getRequestDispatcher("/front/apt/addApt.jsp");
								failureView.forward(req, res);
								return;
							} else {
								req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
								RequestDispatcher failureView = req
										.getRequestDispatcher("/front/apt/addApt.jsp");
								failureView.forward(req, res);
								return;
							}
								
							}
								
							}
							break;
						} // end of if(�P�_�O���ӱ����ɬq)
					}

				}// end of if (i < 20)

				// ServletContext context =
				// this.getServletConfig().getServletContext();
				// req.setAttribute("regDays", regDays);
				/** end�Ȧs��code ***/
				/*
				 * AptService aptSvc = new AptService(); aptVO =
				 * aptSvc.addApt(aptDate, aptPeriod, aptNoSlip, aptRegTime,
				 * petNo);
				 */

				/**/

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// String url = "/apt/listAllApt.jsp";
				// http://localhost:8081/XA803G2fixed1/backend/apt/showApt.jsp
				if ( "backend".equals( req.getRequestURI().substring(9, 16) )  ) {
				if ((req.getContextPath()+"/backend/apt/addApt2.jsp")
						.equals(requestURL)) {
					req.setAttribute("aptVO", aptVO);
					RequestDispatcher successView = req
							.getRequestDispatcher("/backend/apt/addApt3.jsp");
					successView.forward(req, res);
					return;
				} else {
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher successView = req
							.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);
				}
				} else if ( "front".equals( req.getRequestURI().substring(9, 14) )) {
					
					if (( req.getContextPath() + "/front/apt/addApt2.jsp" )
							.equals(requestURL)) {
						req.setAttribute("aptVO", aptVO);
						RequestDispatcher successView = req
								.getRequestDispatcher("/front/apt/addApt3.jsp");
						successView.forward(req, res);
						return;
					} else {
						String url = "/front/apt/addApt.jsp";
						RequestDispatcher successView = req
								.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
						successView.forward(req, res);
					}
				}

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				if( "backend".equals(req.getRequestURI().substring(9, 16) )) {
				 errorMsgs.add(e.getMessage());
				 RequestDispatcher failureView = req
				 .getRequestDispatcher("/backend/apt/addApt.jsp");
				 failureView.forward(req, res);
//				e.printStackTrace();
				} else if ( "front".equals(req.getRequestURI().substring(9, 14))) {
					 errorMsgs.add(e.getMessage());
					 RequestDispatcher failureView = req
					 .getRequestDispatcher("/front/apt/addApt.jsp");
					 failureView.forward(req, res);
//					e.printStackTrace();
				}
			}
			return;
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String aptNo = req.getParameter("aptNo").trim();
				if (aptNo.length() == 0) {
					errorMsgs.add("�����s������!");
				}
				java.sql.Date aptDate = null;
				try {
					aptDate = java.sql.Date.valueOf(req.getParameter("aptDate")
							.trim());
				} catch (IllegalArgumentException e) {
					aptDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("����J���Date����!!");
				}

				String aptPeriod = req.getParameter("aptPeriod").trim();
				try {
					Integer.parseInt(aptPeriod);
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�ܮɬq");
				}

//				Integer aptNoSlip = null;
//				try {
//					aptNoSlip = new Integer(req.getParameter("aptNoSlip")
//							.trim());
//				} catch (NumberFormatException e) {
//					aptNoSlip = 3;
//					errorMsgs.add("���X�P�n��J�Ʀr!");
//				}

//				java.sql.Timestamp aptRegTime = null;
//				try {
//					aptRegTime = java.sql.Timestamp.valueOf(req.getParameter(
//							"aptRegTime").trim());
//				} catch (IllegalArgumentException e) {
//					aptRegTime = new java.sql.Timestamp(
//							System.currentTimeMillis());
//					errorMsgs.add("����JTimestamp����!");
//				}

				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0) {
					errorMsgs.add("�d���s���O!?");
				}
				AptVO aptVO = new AptService().getOneEmp(aptNo);
				aptVO.setAptNo(aptNo);
				aptVO.setAptDate(aptDate);

				aptVO.setAptPeriod(aptPeriod);

//				aptVO.setAptNoSlip(aptNoSlip);

//				aptVO.setAptRegTime(aptRegTime);

				aptVO.setPetNo(petNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("aptVO", aptVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/update_apt_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				AptService aptSvc = new AptService();
				aptVO = aptSvc.updateApt(aptNo, aptDate, aptPeriod, aptVO.getAptNoSlip(),
						aptVO.getAptRegTime(), petNo);

//				pirntRegArray();

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("aptVO", aptVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/apt/listOneApt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/update_apt_input.jsp");
				failureView.forward(req, res);
				
			}
			return;
		}
		/*if ("deQueue".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				*//*************************** 1.�����ШD�Ѽ�, �B�[�W���~�B�z ***************************************//*

				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (regDays[whichDay].getRegPeriods()[whichPeriod].isEmpty()) {
					errorMsgs.add("���C�w�� �L�k�X���F!!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
					failureView.forward(req, res);
					return;
				}

				*//*************************** 2.�}�l�X���ާ@ ***************************************//*
				regDays[whichDay].getRegPeriods()[whichPeriod].deQueue();

				*//*************************** 3.�R������,�ǳ����(Send the Success view) ***********//*
				String url = "/backend/apt/showApt.jsp";
				fq	
			
			
				fq2
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				// res.sendRedirect(url);

				*//*************************** ��L�i�઺���~�B�z **********************************//*
			} catch (Exception e) {
				
				 * errorMsgs.add("�R����ƥ���:"+e.getMessage()); RequestDispatcher
				 * failureView = req
				 * .getRequestDispatcher("/apt/listAllApt.jsp");
				 * failureView.forward(req, res);
				 
				e.printStackTrace();
			}
			return;
		}*/
		
		if("deQueue".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//*************************** 1.�����ШD�Ѽ�, �B�[�W���~�B�z ***************************************//*

				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (regDays[whichDay].getRegPeriods()[whichPeriod].isEmpty()) {
					errorMsgs.add("���C�w�� �L�k�X���F!!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
					failureView.forward(req, res);
					return;
				}

				//*************************** 2.�}�l�X���ާ@ ***************************************//*
				regDays[whichDay].getRegPeriods()[whichPeriod].deQueue();

				//*************************** 3.�R������,�ǳ����(Send the Success view) ***********//*
			//	String url = "/backend/apt/showApt.jsp";
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.flush();
				out.close();
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
				// res.sendRedirect(url);

				//*************************** ��L�i�઺���~�B�z **********************************//*
			} catch (Exception e) {
				
//				  errorMsgs.add("�R����ƥ���:"+e.getMessage()); RequestDispatcher
//				  failureView = req
//				  .getRequestDispatcher("/apt/listAllApt.jsp");
//				  failureView.forward(req, res);
//				 
				e.printStackTrace();
			}
			return;
		}

//		if ("swap".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.�����ШD�Ѽ�, �B�[�W���~�B�z ***************************************/
//
//				String[] selectedElements = req
//						.getParameterValues("selectedElements");
//				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
//				int whichPeriod = Integer.parseInt(req
//						.getParameter("whichPeriod"));
//				if (selectedElements == null || selectedElements.length != 2) {
//					errorMsgs.add("�п���!!");
//				}
//				if (!errorMsgs.isEmpty()) {
//					// String url = req.getContextPath() + "/apt/showApt.jsp";
//					String url = "/backend/apt/showApt.jsp";
//					RequestDispatcher successView = req
//							.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//					successView.forward(req, res);
//					return;
//				}
//				/*************************** 2.�}�l�洫�ާ@ ***************************************/
//
//				regDays[whichDay].getRegPeriods()[whichPeriod].swap(
//						Integer.parseInt(selectedElements[0]),
//						Integer.parseInt(selectedElements[1]));
//
//				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//				// String url = req.getContextPath() + "/apt/showApt.jsp";
//				String url = "/backend/apt/showApt.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				// res.sendRedirect(url);
//
//				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				/*
//				 * errorMsgs.add("�R����ƥ���:"+e.getMessage()); RequestDispatcher
//				 * failureView = req
//				 * .getRequestDispatcher("/apt/listAllApt.jsp");
//				 * failureView.forward(req, res);
//				 */
//				e.printStackTrace();
//			}
//			return;
//		}
		
		if ("swap".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ�, �B�[�W���~�B�z ***************************************/

				String selectedElementsStr = req
						.getParameter("selectedElements");
				String[] selectedElements = selectedElementsStr.split(",");
				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (selectedElements == null || selectedElements.length != 2) {
					errorMsgs.add("�п���!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failedView = req
							.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
					failedView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�洫�ާ@ ***************************************/

				regDays[whichDay].getRegPeriods()[whichPeriod].swap(
						Integer.parseInt(selectedElements[0]),
						Integer.parseInt(selectedElements[1]));

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				// String url = req.getContextPath() + "/apt/showApt.jsp";
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.flush();
				out.close();
				// res.sendRedirect(url);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				/*
				 * errorMsgs.add("�R����ƥ���:"+e.getMessage()); RequestDispatcher
				 * failureView = req
				 * .getRequestDispatcher("/apt/listAllApt.jsp");
				 * failureView.forward(req, res);
				 */
				e.printStackTrace();
			}
			return;
		}

		if ("listApts_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�N��J����ରMap **********************************/
				// �ĥ�Map<String,String[]> getParameterMap()����k
				// �`�N:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session
						.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req
							.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
					System.out.println(map);
				}

				/*************************** 2.�}�l�ƦX�d�� ***************************************/
				AptService aptSvc = new AptService();
				List<AptVO> list = aptSvc.getAll(map);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				if( "backend".equals( req.getRequestURI().substring(9, 16) ) ){
				req.setAttribute("listApts_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req
						.getRequestDispatcher("/backend/apt/listApts_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				} else if ( "front".equals( req.getRequestURI().substring(12, 17) ) ) {      //����O�o��^.subString(9, 14)
					req.setAttribute("listApts_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
					RequestDispatcher successView = req
							.getRequestDispatcher("/front/apt/listApts_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
				}
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				// errorMsgs.add(e.getMessage());
				// RequestDispatcher failureView = req
				// .getRequestDispatcher("/select_page.jsp");
				// failureView.forward(req, res);
				e.printStackTrace();
			}
		}

	}

	public void pirntRegArray() {
		System.out.println("�}�l�L�����_���F!");
		for (int k = 0; k < 20; k++) {
			if (regDays[k] != null) {
				System.out.println("RegDays:" + regDays[k].getDate());
				for (int j = 0; j < regDays[k].getRegPeriods().length; j++) {
					if (regDays[k].getRegPeriods()[j] != null) {
						System.out.println("RegPeriod : "
								+ regDays[k].getRegPeriods()[j].toString());
						for (int z = 0; z < regDays[k].getRegPeriods()[j]
								.getRegQueue().length; z++) {
							if (regDays[k].getRegPeriods()[j].getRegQueue()[z] != null) {
								System.out.println("�ӱ����}�C��� : "
										+ regDays[k].getDate()
										+ "�ɬq : "
										+ regDays[k].getRegPeriods()[j]
												.getPeriod()
										+ "�����s�� : "
										+ regDays[k].getRegPeriods()[j]
												.getRegQueue()[z].getAptNo());
							}

						}
					}
				}
			}
		}
	}

	public static void createRegArrayFromDB(RegDay[] regDays) {
		/*** �H�U�B�J���إߥ���20��(�]�t����)��RegDay�}�C ****/
		ShiftDAO shiftDao = new ShiftDAO();
		List<ShiftVO> shiftList = shiftDao.getFuture20All();
		// �Q��treeSet�ӱƧ���o����20�Ѫ��Z����(from DB,�B�]�t����)�A�BSet�|�N�@�˪�����X�֬��@��!!
		Set<Date> treeSet = new TreeSet<Date>();
		for (ShiftVO shiftVO : shiftList) {
			Date date = shiftVO.getShiftDate();
			treeSet.add(date);
		}
		// �A�NtreeSet�̪�Date���� ��J�@��Date�}�C:workDates[]
		Date[] dates = new Date[0];
		// System.out.println("treeSet(workDates)" + treeSet);
		Date[] workDates = treeSet.toArray(dates);
		// System.out.println(workDates);
		for (int j = 0; j < workDates.length; j++) {
			System.out.println("workDates[]" + workDates[j]);

		}
		;

		for (int i = 0; i < 20; i++) { // �إߥ��ӤG�Q��(�]�t����)(�B�]�t�𮧤�->�|�d��)��RegDay����}�C
			java.sql.Date someDay = new java.sql.Date(
					new java.util.Date().getTime() + i
							* ONE_DAY_IN_MILLISECONDS);
			// �p�G�ӤѤ��𮧪���(�q��ͯZ��ӧP�_), ���Ӷ}�P�@�ӸӤѪ�RegDay����s�JregDays�}�C
			// �p�G�Ӥѥ𮧪���, ������regDays[i]�O�d����
			for (int j = 0; j < workDates.length; j++) {
				if (someDay.toString().equals(workDates[j].toString())) {
					regDays[i] = new RegDay(someDay);
					break;
				}
			}
		}
		
		

		/** �N�w����������(DB�̪�) ��JRegDay��Period���������C **/
		int i = 0;
		AptDAO aptDAO = new AptDAO();
		List<AptVO> aptVOList = aptDAO.getFuture20All();
		/**����! �q���0�I�}�l!*/
		java.sql.Date today = new java.sql.Date ( new java.util.Date().getTime() );
		today = java.sql.Date.valueOf( today.toString() );
		for (AptVO aptVO : aptVOList) {
			System.out.println( aptVO.getAptDate().toString());
			String date = new String(aptVO.getAptDate().toString());
			Date aptDate = java.sql.Date.valueOf( date );
			String aptPeriod = aptVO.getAptPeriod();
//			Integer aptNoSlip = aptVO.getAptNoSlip();
			
			
		int index = (int)(( aptDate.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS );
//			int index = (int) ( ( aptDate.getTime() - today.getTime() ) / ONE_DAY_IN_MILLISECONDS);
//			System.out.println( "aptDate.getTime() - today.getTime():" + (aptDate.getTime() - today.getTime()));
//			System.out.println( "index : " + index);
		
			RegDay regDay = regDays[index];
			System.out.println( "i" +  (++i) );
			if (  regDay != null ){                /// Yo! �s�[��if
			RegPeriod regPeriod = regDay.getRegPeriods()[regDay.returnPeriodInIndex(aptPeriod)];
//			System.out.println( "regPeriod" + regPeriod );
			
				regPeriod.enQueue(aptVO);
			}   								  /// Yo! �s�[��if
		}

	}
	
	public boolean isAM( String aptPeriod ) {
		Integer aptPeriodInt = Integer.valueOf(aptPeriod);
		Integer noonInt = 1200;
		if ( noonInt >= aptPeriodInt ){
			return true;
		} else {
			return false;

		}
		
		
	}
	

}
