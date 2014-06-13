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

		if ("getAll_Acts_By_Date_For_startTime".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("actStartDate");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String actStartDate = null;
				try {
					actStartDate =str.substring(8,10)+"-"+Integer.parseInt(str.substring(5,7))+"月 "+"-"+str.substring(2,4);
					System.out.println("***"+actStartDate);
				} catch (Exception e) {
					errorMsgs.add("活動日期不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

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
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front/act/select_page.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_Date_For_endTime".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str =  multi.getParameter("startHour");
//				System.out.println("startHour="+ str);
				int startHour = Integer.parseInt(str.substring(1,3));
				System.out.println("startHour="+ startHour);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String actStartDate = multi.getParameter("actStartDate");
				req.setAttribute("actStartDate", actStartDate);
				/*************************** 2.開始查詢資料 *****************************************/
//				ActService actSvc = new ActService();
//				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
//				if (list == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front/act/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
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
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_Date".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("actStartDate");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String actStartDate = null;
				try {
					actStartDate =str.substring(8,10)+"-"+Integer.parseInt(str.substring(5,7))+"月 "+"-"+str.substring(2,4);
					System.out.println(actStartDate);
				} catch (Exception e) {
					errorMsgs.add("活動日期不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByDate(actStartDate);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				TreeSet<Integer> ts = getOccupiedHour(list);
//				System.out.println(ts);
//				req.setAttribute("occupied", ts);
				
				req.setAttribute("listAllActsByDate", list); // 資料庫取出的empVO物件,存入req
				String url = "/act/listAllActsByDate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getAll_Acts_By_MemNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer memNo = null;
				try {
					memNo =new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動日期不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActService actSvc = new ActService();
				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				TreeSet<Integer> ts = getOccupiedHour(list);
//				System.out.println(ts);
//				req.setAttribute("occupied", ts);
				
				req.setAttribute("listAllActsByMemNo", list); // 資料庫取出的empVO物件,存入req
				String url = "/front/act/listAllActsByMemNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("actNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer actNo = null;
				try {
					actNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/listOneAct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/act/listOneAct.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("actVO1", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/act/update_act_inputORI.jsp";
				System.out.println("URL:" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_back".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);
//System.out.println("actVO:" + actVO.getActNo());
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("fromListActsByUpdate", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/act/update_act_input.jsp";
//				System.out.println("URL:" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_ActRegister".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("actVO1", actVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/actRegister/addActRegister.jsp";
				System.out.println("URL:" + url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

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

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
					errorMsgs.add("請輸入日期!");
				}

				
				try {
//					actEndTime = java.sql.Timestamp.valueOf(multi.getParameter("actEndTime").trim());
					String endDate = multi.getParameter("endDate").trim();
					String endHour = multi.getParameter("endHour").trim();
					String actEnd = endDate + " " +endHour ;
					actEndTime = java.sql.Timestamp.valueOf(actEnd);
				} catch (IllegalArgumentException e) {
					actEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請輸入器材名稱，若沒有，則輸入'無'");
				}

				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("請填入押金費用");
				}

				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = 0.0;
					errorMsgs.add("請填入主辦人費用");
				}

				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = 0.0;
					errorMsgs.add("請填入報名費用");
				}
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "N";
					errorMsgs.add("請填入活動狀態");
				}
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("請填入會員編號");
				}
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("請填入報名費用");
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
					req.setAttribute("actVO1", vo2); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				// ActService actSvc = new ActService();
				vo2 = actSvc.updateAct(actNo, actName, actContent,
						actStartTime, actEndTime, buffer, actEquipment,
						actDeposit, actHostFee, actRegFee, actStatus, memNo,
						empNo);

				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				req.setAttribute("listAllActsByMemNo", list); // 資料庫取出的empVO物件,存入req
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("actVO1", vo2); // 資料庫update成功後,正確的的empVO物件,存入req
				
				
			//	String url = requestURL;
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} 
			catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front/act/update_act_input.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("updateForBack".equals(action)) { // 來自update_emp_input.jsp的請求
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

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
					errorMsgs.add("請輸入日期!");
				}

				
				try {
					actEndTime = java.sql.Timestamp.valueOf(multi.getParameter("actEndTime").trim());
//					String endDate = multi.getParameter("endDate").trim();
//					String endHour = multi.getParameter("endHour").trim();
//					String actEnd = endDate + " " +endHour ;
//					actEndTime = java.sql.Timestamp.valueOf(actEnd);
				} catch (IllegalArgumentException e) {
					actEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請輸入器材名稱，若沒有，則輸入'無'");
				}

				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("請填入押金費用");
				}

				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = 0.0;
					errorMsgs.add("請填入主辦人費用");
				}

				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = 0.0;
					errorMsgs.add("請填入報名費用");
				}
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "N";
					errorMsgs.add("請填入活動狀態");
				}
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("請填入會員編號");
				}
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("請填入報名費用");
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
					req.setAttribute("fromBackUpdateInput", vo2); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				// ActService actSvc = new ActService();
				vo2 = actSvc.updateAct(actNo, actName, actContent,
						actStartTime, actEndTime, buffer, actEquipment,
						actDeposit, actHostFee, actRegFee, actStatus, memNo,
						empNo);

				List<ActVO> list = actSvc.getAllActsByMemNo(memNo);
				req.setAttribute("listAllActsByMemNo", list); // from listAll
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("fromBackUpdateInput", vo2); // 從update_act_input傳過來的資訊再轉送回去
				
				ActService actSvc1 = new ActService();
				List<ActVO> listAgain  = actSvc.getAll((Map<String, String[]>)req.getSession().getAttribute("map"));
//				System.out.println("List:" + listAgain);
				req.setAttribute("listActs_ByCompositeQuery", listAgain); // 重抓一次資料By第一次查詢就放在session的map...一起傳送到下頁(因為第一次 Query傳送用req..只會送到/backend/act/listActsByCompositeQuery.jsp	)
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交/backend/act/listActsByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} 
			catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/act/update_act_input.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

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
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請上傳愛寵圖片!!");
				}

				String actEquipment = null;
				try {
					actEquipment = new String(multi.getParameter("actEquipment").trim());
				} catch (NumberFormatException e) {
					actEquipment = null;
					errorMsgs.add("請輸入器材名稱，若沒有，則輸入'無'");
				}

				Double actDeposit = null;
				try {
					actDeposit = new Double(multi.getParameter("actDeposit")
							.trim());
				} catch (NumberFormatException e) {
					actDeposit = 0.0;
					errorMsgs.add("費用請填數字.");
				}

				Double actHostFee = null;
				try {
					actHostFee = new Double(multi.getParameter("actHostFee")
							.trim());
				} catch (NumberFormatException e) {
					actHostFee = null;
					errorMsgs.add("費用請填數字.");
				}

				Double actRegFee = null;
				try {
					actRegFee = new Double(multi.getParameter("actRegFee")
							.trim());
				} catch (NumberFormatException e) {
					actRegFee = null;
					errorMsgs.add("費用請填數字.");
				}
				String actStatus = "";
				try {
					actStatus = new String(multi.getParameter("actStatus")
							.trim());
				} catch (NumberFormatException e) {
					actStatus = "";
					errorMsgs.add("請輸入活動狀態");
				}
				Integer memNo = 0000;
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					memNo = 0000;
					errorMsgs.add("請輸入會員編號");
				}
				Integer empNo = 0000;
				try {
					empNo = new Integer(multi.getParameter("empNo").trim());
				} catch (NumberFormatException e) {
					empNo = 0000;
					errorMsgs.add("請輸入職員編號");
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
					req.setAttribute("ActVO", vo3); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActService actSvc = new ActService();
				vo3 = actSvc.addAct(actName, actContent, actStartTime,
						actEndTime, buffer, actEquipment, actDeposit,
						actHostFee, actRegFee, actStatus, memNo, empNo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/act/addAct.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		if ("delete_back".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				ActService actSvc = new ActService();
				actSvc.deleteAct(actNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/act/listAllAct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listActs_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				ActService actSvc = new ActService();
				List<ActVO> list  = actSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listActs_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front/act/listActs_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listActs_ByCompositeQueryForBack".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				/***************************2.開始複合查詢***************************************/
				ActService actSvc = new ActService();
				List<ActVO> list  = actSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listActs_ByCompositeQuery", list); // from select to listQuery
				RequestDispatcher successView = req.getRequestDispatcher("/backend/act/listActsByCompositeQuery.jsp"); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
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


