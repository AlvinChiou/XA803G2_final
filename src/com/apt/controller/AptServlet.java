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

	/** start暫存的code ***/
	public static final int QUEUE_SIZE = 5;
	public static final long ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

	RegDay regDays[] = new RegDay[20];

	@Override
	public void init() throws ServletException {

		/*** 以下步驟為建立未來20天(包含今天)的RegDay陣列 ****/

		createRegArrayFromDB(regDays);

		/** regDays[]存入SerletContext(Appliation)裡,以供顯示頁面顯示 **/
		ServletContext context = getServletContext();
		context.setAttribute("regDays", regDays);
		/***/

		// 印出來現在存在ram裡的20天
		pirntRegArray();

	}

	/** end暫存的code ***/
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("hi, i've got a request!");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("gotoApt".equals(action)) { // 從日曆選擇欲掛號日期 然後跳轉至掛號頁面
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/apt/addApt.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		/*** <forAndroidfindByPK> ***/
		if ("getOne".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String aptNo = req.getParameter("aptNo");
				if (aptNo == null || (aptNo.trim()).length() == 0) {
					errorMsgs.add("請輸入掛號編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				try {
					// 不會去跑這行 因為掛號編號的規則還沒出來!
					Integer.parseInt(aptNo);
				} catch (Exception e) {
					errorMsgs.add("掛號編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 ***************************************/
				// 那天真的有看診 才能新增掛號(寫入DB紀錄該掛號)
				AptService aptSvc = new AptService();
				AptVO aptVO = aptSvc.getOneEmp(aptNo);

				JSONObject jsnObj = AptJsonHandler.toJSONObject(aptVO);
				String outStr = jsnObj.toString();
				// 送回response給客戶端
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.println(outStr);

				/*************************** 3.新增完成,準備XXX ***********/

				/*************************** 其他可能的錯誤處理 **********************************/
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
		if ("mkAnApt".equals(action)) { // 來自Android的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				java.sql.Date aptDate = null;
				try {
					aptDate = java.sql.Date.valueOf(req.getParameter("aptDate")
							.trim());
				} catch (IllegalArgumentException e) {
					aptDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("應輸入日期Date類型!!");
				}

				String aptPeriod = req.getParameter("aptPeriod").trim();
				if (aptPeriod == null || (aptPeriod.trim()).length() == 0
						|| aptPeriod.equals("0")) {
					errorMsgs.add("請選擇時段");
				}

				Integer aptNoSlip = null;

				java.sql.Timestamp aptRegTime = null;

				// if petNo == 0, means client didnt chose any pet from client
				// page.　
				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0
						|| petNo.equals("0")) {
					errorMsgs.add("請選擇寵物名稱s");
				}
				AptVO aptVO = new AptVO();
				aptVO.setAptDate(aptDate);

				aptVO.setAptPeriod(aptPeriod);

				aptVO.setAptNoSlip(aptNoSlip);

				aptVO.setAptRegTime(aptRegTime);

				aptVO.setPetNo(petNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/apt/addApt.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				/** start暫存的code ***/
				int i;
				// 找是掛哪一天, 並將該天的index存入i中
				t1: for (i = 0; i < 20; i++) {
					if (regDays[i] == null) { // 如果是休息日，則直接找下一天
						continue t1;
					}
					if (aptDate.toString().equals(
							regDays[i].getDate().toString())) {
						break t1;
					}
				}

				if (i < 20) { // 如果真的找到該天的話
					for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {// 找是掛哪個時段

						if (aptPeriod.equals(regDays[i].getRegPeriods()[j]
								.getPeriod())) {
							RegPeriod thatRegPeriod = regDays[i]
									.getRegPeriods()[j];

							aptRegTime = new Timestamp( // set應到時間
									new GregorianCalendar(
											Integer.parseInt(aptDate.toString()
													.substring(0, 4)), // 似sql.Date.getYear()
											Integer.parseInt(aptDate.toString()
													.substring(5, 7)) - 1, // 似sql.Date.getMonth()
											Integer.parseInt(aptDate.toString()
													.substring(8)), // 似sql.Date.getDayOfMonth()
											Integer.parseInt(aptPeriod
													.substring(0, 2)), // 似
																		// getHour()
											(60 / QUEUE_SIZE * (thatRegPeriod
													.calculateElements())), // 似getMinute
											0).getTimeInMillis());
							aptNoSlip = thatRegPeriod.calculateElements() + 1; // 產生號碼牌,根據先來後到
							aptVO.setAptRegTime(aptRegTime);
							aptVO.setAptNoSlip(aptNoSlip);
							// 新增至掛號陣列
							AptDAO dao = new AptDAO();
							// 取得最新的掛號編號,以讓在掛號陣列中的掛號編號與DB同步,目前已知缺點
							int aptNo = dao.getLastAptNo() + 1;
							aptVO.setAptNo(aptNo + "");
							boolean isSuccess = thatRegPeriod.enQueue(aptVO);
							if (isSuccess) {
								// 那天真的有看診 才能新增掛號
								AptService aptSvc = new AptService();
								aptVO = aptSvc.addApt(aptDate, aptPeriod,
										aptNoSlip, aptRegTime, petNo);

							} else {
								errorMsgs.add("掛號佇列已滿!不能再掛!");
							}
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req
										.getRequestDispatcher("/backend/apt/addApt.jsp");
								failureView.forward(req, res);
								return;
							}
							break;
						} // end of if(判斷是哪個掛號時段)
					}
					// 那天真的有看診 才能新增掛號(寫入DB紀錄該掛號)
					// AptService aptSvc = new AptService();
					// aptVO = aptSvc.addApt(aptDate, aptPeriod, aptNoSlip,
					// aptRegTime, petNo);// end of t2 : for

					JSONObject jsnObj = AptJsonHandler.toJSONObject(aptVO);
					String outStr = jsnObj.toString();
					// 送回response給客戶端
					res.setContentType("text/html; charset=UTF-8");
					PrintWriter out = res.getWriter();
					out.println(outStr);

				}// end of if (i < 20)
				/** end暫存的code ***/
				/*
				 * AptService aptSvc = new AptService(); aptVO =
				 * aptSvc.addApt(aptDate, aptPeriod, aptNoSlip, aptRegTime,
				 * petNo);
				 */

				/*************************** 3.新增完成,準備XXX ***********/

				/*************************** 其他可能的錯誤處理 **********************************/
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
			/*************************** 開始查詢資料 ****************************************/
			AptDAO dao = new AptDAO();
			List<AptVO> list = dao.getAll();

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list); // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/backend/apt/listAllApt_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}

		if ("getOne_For_Display".equals(action)
				|| "getOne_For_DisplayInTheSamePage".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("aptNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入掛號編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String aptNo = null;
				try {
					// 不會去跑這行 因為掛號編號的規則還沒出來!
					aptNo = str;
				} catch (Exception e) {
					errorMsgs.add("掛號編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AptDAO dao = new AptDAO();
				AptVO aptVO = dao.findByPrimaryKey(aptNo);
				if (aptVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = null;
				req.setAttribute("aptVO", aptVO); // 資料庫取出的empVO物件,存入req
				if (action.equals("getOne_For_DisplayInTheSamePage")) {
					url = "/backend/apt/showApt.jsp";
				} else if ("getOne_For_Display".equals(action)) {
					url = "/backend/apt/listOneApt.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交給listOneApt.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/select_page.jsp");
				failureView.forward(req, res);
			}
			return;
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String aptNo = req.getParameter("aptNo");

				/*************************** 2.開始查詢資料 ****************************************/
				AptService aptSvc = new AptService();
				AptVO aptVO = aptSvc.getOneEmp(aptNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("aptVO", aptVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/apt/update_apt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/listAllApt.jsp");
				failureView.forward(req, res);
			}
			return;
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			String requestURL = null;
			System.out.println(requestURL = req.getParameter("requestURL"));

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

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
					errorMsgs.add("應輸入日期Date類型!!");
				}

				String aptPeriod = req.getParameter("aptPeriod");
				if (aptPeriod == null) {
					aptPeriod = (String) (req.getSession()
							.getAttribute("aptPeriod"));
				}
				if (aptPeriod == null || (aptPeriod.trim()).length() == 0
						|| aptPeriod.equals("0")) {
					errorMsgs.add("請選擇時段");
				}

				Integer aptNoSlip = null;

				java.sql.Timestamp aptRegTime = null;

				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0
						|| petNo.equals("0")) {
					errorMsgs.add("請選擇寵物名稱s");
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
						req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
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
							req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front/apt/addApt.jsp");
							failureView.forward(req, res);
							return;
						}
					}
					
				}

				/*************************** 2.開始新增資料 ***************************************/

				/** start暫存的code ***/
				int i;
				t1: for (i = 0; i < 20; i++) { // 找是掛哪一天
					if (regDays[i] == null) { // 如果是休息日，則直接找下一天
						continue t1;
					}
					if (aptDate.toString().equals(
							regDays[i].getDate().toString())) {
						break t1;
					}
				}

				if (i < 20) { // 如果真的找到該天的話
					for (int j = 0; j < regDays[i].getRegPeriods().length; j++) {// 找是掛哪個時段
						System.out.println(regDays[i].getRegPeriods()[j]);

						if (regDays[i].getRegPeriods()[j] == null) {
							j = 2;
							continue;
						}
						if (aptPeriod.equals(regDays[i].getRegPeriods()[j]
								.getPeriod())) {
							RegPeriod thatRegPeriod = regDays[i]
									.getRegPeriods()[j];

							aptRegTime = new Timestamp( // set應到時間
									new GregorianCalendar(
											Integer.parseInt(aptDate.toString()
													.substring(0, 4)), // 似sql.Date.getYear()
											Integer.parseInt(aptDate.toString()
													.substring(5, 7)) - 1, // 似sql.Date.getMonth()
											Integer.parseInt(aptDate.toString()
													.substring(8)), // 似sql.Date.getDayOfMonth()
											Integer.parseInt(aptPeriod
													.substring(0, 2)), // 似
																		// getHour()
											(60 / QUEUE_SIZE * (thatRegPeriod
													.calculateElements())), // 似getMinute
											0).getTimeInMillis());
							aptNoSlip = thatRegPeriod.calculateElements() + 1; // 產生號碼牌,根據先來後到
							aptVO.setAptRegTime(aptRegTime);
							aptVO.setAptNoSlip(aptNoSlip);
							// 新增至掛號陣列
							AptDAO dao = new AptDAO();
							// 取得最新的掛號編號,以讓在掛號陣列中的掛號編號與DB同步,目前已知缺點
							int aptNo = dao.getLastAptNo() + 1;
							aptVO.setAptNo(aptNo + "");
							boolean isSuccess = thatRegPeriod.enQueue(aptVO);
							if (isSuccess) {
								// 那天真的有看診 才能新增掛號
								AptService aptSvc = new AptService();
								aptVO = aptSvc.addApt(aptDate, aptPeriod,
										aptNoSlip, aptRegTime, petNo);

							} else {  
								String shiftPeriod = isAM( aptPeriod ) ? "上午" : "下午";
								System.out.println( "shiftPeriod : " + shiftPeriod);
								req.setAttribute("shiftPeriod", shiftPeriod);
								errorMsgs.add("掛號佇列已滿!不能再掛!");
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
									req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
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
								req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req
										.getRequestDispatcher("/front/apt/addApt.jsp");
								failureView.forward(req, res);
								return;
							}
								
							}
								
							}
							break;
						} // end of if(判斷是哪個掛號時段)
					}

				}// end of if (i < 20)

				// ServletContext context =
				// this.getServletConfig().getServletContext();
				// req.setAttribute("regDays", regDays);
				/** end暫存的code ***/
				/*
				 * AptService aptSvc = new AptService(); aptVO =
				 * aptSvc.addApt(aptDate, aptPeriod, aptNoSlip, aptRegTime,
				 * petNo);
				 */

				/**/

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
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
							.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
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
								.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);
					}
				}

				/*************************** 其他可能的錯誤處理 **********************************/
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

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String aptNo = req.getParameter("aptNo").trim();
				if (aptNo.length() == 0) {
					errorMsgs.add("掛號編號為空!");
				}
				java.sql.Date aptDate = null;
				try {
					aptDate = java.sql.Date.valueOf(req.getParameter("aptDate")
							.trim());
				} catch (IllegalArgumentException e) {
					aptDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("應輸入日期Date類型!!");
				}

				String aptPeriod = req.getParameter("aptPeriod").trim();
				try {
					Integer.parseInt(aptPeriod);
				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇時段");
				}

//				Integer aptNoSlip = null;
//				try {
//					aptNoSlip = new Integer(req.getParameter("aptNoSlip")
//							.trim());
//				} catch (NumberFormatException e) {
//					aptNoSlip = 3;
//					errorMsgs.add("號碼牌要輸入數字!");
//				}

//				java.sql.Timestamp aptRegTime = null;
//				try {
//					aptRegTime = java.sql.Timestamp.valueOf(req.getParameter(
//							"aptRegTime").trim());
//				} catch (IllegalArgumentException e) {
//					aptRegTime = new java.sql.Timestamp(
//							System.currentTimeMillis());
//					errorMsgs.add("應填入Timestamp類型!");
//				}

				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || (petNo.trim()).length() == 0) {
					errorMsgs.add("寵物編號呢!?");
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
					req.setAttribute("aptVO", aptVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/apt/update_apt_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				AptService aptSvc = new AptService();
				aptVO = aptSvc.updateApt(aptNo, aptDate, aptPeriod, aptVO.getAptNoSlip(),
						aptVO.getAptRegTime(), petNo);

//				pirntRegArray();

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("aptVO", aptVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/apt/listOneApt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/apt/update_apt_input.jsp");
				failureView.forward(req, res);
				
			}
			return;
		}
		/*if ("deQueue".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				*//*************************** 1.接收請求參數, 且加上錯誤處理 ***************************************//*

				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (regDays[whichDay].getRegPeriods()[whichPeriod].isEmpty()) {
					errorMsgs.add("隊列已空 無法出隊了!!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					failureView.forward(req, res);
					return;
				}

				*//*************************** 2.開始出隊操作 ***************************************//*
				regDays[whichDay].getRegPeriods()[whichPeriod].deQueue();

				*//*************************** 3.刪除完成,準備轉交(Send the Success view) ***********//*
				String url = "/backend/apt/showApt.jsp";
				fq	
			
			
				fq2
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				// res.sendRedirect(url);

				*//*************************** 其他可能的錯誤處理 **********************************//*
			} catch (Exception e) {
				
				 * errorMsgs.add("刪除資料失敗:"+e.getMessage()); RequestDispatcher
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
				//*************************** 1.接收請求參數, 且加上錯誤處理 ***************************************//*

				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (regDays[whichDay].getRegPeriods()[whichPeriod].isEmpty()) {
					errorMsgs.add("隊列已空 無法出隊了!!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					failureView.forward(req, res);
					return;
				}

				//*************************** 2.開始出隊操作 ***************************************//*
				regDays[whichDay].getRegPeriods()[whichPeriod].deQueue();

				//*************************** 3.刪除完成,準備轉交(Send the Success view) ***********//*
			//	String url = "/backend/apt/showApt.jsp";
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.flush();
				out.close();
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				// res.sendRedirect(url);

				//*************************** 其他可能的錯誤處理 **********************************//*
			} catch (Exception e) {
				
//				  errorMsgs.add("刪除資料失敗:"+e.getMessage()); RequestDispatcher
//				  failureView = req
//				  .getRequestDispatcher("/apt/listAllApt.jsp");
//				  failureView.forward(req, res);
//				 
				e.printStackTrace();
			}
			return;
		}

//		if ("swap".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數, 且加上錯誤處理 ***************************************/
//
//				String[] selectedElements = req
//						.getParameterValues("selectedElements");
//				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
//				int whichPeriod = Integer.parseInt(req
//						.getParameter("whichPeriod"));
//				if (selectedElements == null || selectedElements.length != 2) {
//					errorMsgs.add("請選兩個!!");
//				}
//				if (!errorMsgs.isEmpty()) {
//					// String url = req.getContextPath() + "/apt/showApt.jsp";
//					String url = "/backend/apt/showApt.jsp";
//					RequestDispatcher successView = req
//							.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//					successView.forward(req, res);
//					return;
//				}
//				/*************************** 2.開始交換操作 ***************************************/
//
//				regDays[whichDay].getRegPeriods()[whichPeriod].swap(
//						Integer.parseInt(selectedElements[0]),
//						Integer.parseInt(selectedElements[1]));
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				// String url = req.getContextPath() + "/apt/showApt.jsp";
//				String url = "/backend/apt/showApt.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				// res.sendRedirect(url);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				/*
//				 * errorMsgs.add("刪除資料失敗:"+e.getMessage()); RequestDispatcher
//				 * failureView = req
//				 * .getRequestDispatcher("/apt/listAllApt.jsp");
//				 * failureView.forward(req, res);
//				 */
//				e.printStackTrace();
//			}
//			return;
//		}
		
		if ("swap".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數, 且加上錯誤處理 ***************************************/

				String selectedElementsStr = req
						.getParameter("selectedElements");
				String[] selectedElements = selectedElementsStr.split(",");
				int whichDay = Integer.parseInt(req.getParameter("whichDay"));
				int whichPeriod = Integer.parseInt(req
						.getParameter("whichPeriod"));
				if (selectedElements == null || selectedElements.length != 2) {
					errorMsgs.add("請選兩個!!");
				}
				if (!errorMsgs.isEmpty()) {
					// String url = req.getContextPath() + "/apt/showApt.jsp";
					String url = "/backend/apt/showApt.jsp";
					RequestDispatcher failedView = req
							.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					failedView.forward(req, res);
					return;
				}
				/*************************** 2.開始交換操作 ***************************************/

				regDays[whichDay].getRegPeriods()[whichPeriod].swap(
						Integer.parseInt(selectedElements[0]),
						Integer.parseInt(selectedElements[1]));

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				// String url = req.getContextPath() + "/apt/showApt.jsp";
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.flush();
				out.close();
				// res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				/*
				 * errorMsgs.add("刪除資料失敗:"+e.getMessage()); RequestDispatcher
				 * failureView = req
				 * .getRequestDispatcher("/apt/listAllApt.jsp");
				 * failureView.forward(req, res);
				 */
				e.printStackTrace();
			}
			return;
		}

		if ("listApts_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
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

				/*************************** 2.開始複合查詢 ***************************************/
				AptService aptSvc = new AptService();
				List<AptVO> list = aptSvc.getAll(map);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				if( "backend".equals( req.getRequestURI().substring(9, 16) ) ){
				req.setAttribute("listApts_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req
						.getRequestDispatcher("/backend/apt/listApts_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				} else if ( "front".equals( req.getRequestURI().substring(12, 17) ) ) {      //之後記得改回.subString(9, 14)
					req.setAttribute("listApts_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req
							.getRequestDispatcher("/front/apt/listApts_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 **********************************/
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
		System.out.println("開始印掛號震裂了!");
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
								System.out.println("該掛號陣列日期 : "
										+ regDays[k].getDate()
										+ "時段 : "
										+ regDays[k].getRegPeriods()[j]
												.getPeriod()
										+ "掛號編號 : "
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
		/*** 以下步驟為建立未來20天(包含今天)的RegDay陣列 ****/
		ShiftDAO shiftDao = new ShiftDAO();
		List<ShiftVO> shiftList = shiftDao.getFuture20All();
		// 利用treeSet來排序獲得未來20天的班表日期(from DB,且包含今天)，且Set會將一樣的日期合併為一個!!
		Set<Date> treeSet = new TreeSet<Date>();
		for (ShiftVO shiftVO : shiftList) {
			Date date = shiftVO.getShiftDate();
			treeSet.add(date);
		}
		// 再將treeSet裡的Date物件 放入一個Date陣列:workDates[]
		Date[] dates = new Date[0];
		// System.out.println("treeSet(workDates)" + treeSet);
		Date[] workDates = treeSet.toArray(dates);
		// System.out.println(workDates);
		for (int j = 0; j < workDates.length; j++) {
			System.out.println("workDates[]" + workDates[j]);

		}
		;

		for (int i = 0; i < 20; i++) { // 建立未來二十天(包含今天)(且包含休息日->會留空)的RegDay物件陣列
			java.sql.Date someDay = new java.sql.Date(
					new java.util.Date().getTime() + i
							* ONE_DAY_IN_MILLISECONDS);
			// 如果該天不休息的話(從醫生班表來判斷), 應該開闢一個該天的RegDay物件存入regDays陣列
			// 如果該天休息的話, 應該讓regDays[i]保留為空
			for (int j = 0; j < workDates.length; j++) {
				if (someDay.toString().equals(workDates[j].toString())) {
					regDays[i] = new RegDay(someDay);
					break;
				}
			}
		}
		
		

		/** 將已掛號的紀錄(DB裡的) 塞入RegDay的Period的掛號隊列 **/
		int i = 0;
		AptDAO aptDAO = new AptDAO();
		List<AptVO> aptVOList = aptDAO.getFuture20All();
		/**今天! 從凌晨0點開始!*/
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
			if (  regDay != null ){                /// Yo! 新加的if
			RegPeriod regPeriod = regDay.getRegPeriods()[regDay.returnPeriodInIndex(aptPeriod)];
//			System.out.println( "regPeriod" + regPeriod );
			
				regPeriod.enQueue(aptVO);
			}   								  /// Yo! 新加的if
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
