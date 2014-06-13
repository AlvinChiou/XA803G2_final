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
		
		if ("getAll_For_Display_By_ActNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front/actRegister/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				Integer actNo = null;
				try {
					actNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByActNo(actNo);
				if (actRegVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("allActRegsByActNo", actRegVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/actRegister/listAllActRegistersByActNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Display_By_MemNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByMemNo(memNo);
				if (memNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("allActRegsByMemNo", actRegVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/actRegister/listAllActRegisterBymemNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_For_Display_By_MemNo_For_Delete".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				List<ActRegisterVO> actRegVO = actRegSvc.getAllActRegistersByMemNo(memNo);
				if (memNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("allActRegsByMemNo", actRegVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/actRegister/listAllActRegisterBymemNoForDelete.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actRegNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動報名編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actRegNo = null;
				try {
					actRegNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動報名編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();;
				ActRegisterVO actRegVO = actRegSvc.getOneActRegister(actRegNo);
				if (actRegNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/actRegister/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actRegVO", actRegVO); // 資料庫取出的empVO物件,存入req
				String url = "/actRegister/listOneActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer actRegNo = new Integer(req.getParameter("actRegNo"));
				
				/***************************2.開始查詢資料****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				ActRegisterVO actRegVO = actRegSvc.getOneActRegister(actRegNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actRegVO", actRegVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/actRegister/update_actRegister_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
					errorMsgs.add("請輸入狀態.");
				}
				
				Integer memNo = 0;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
				} catch (Exception e) {
					memNo = 0;
					errorMsgs.add("請輸入會員編號.");
				}
				Integer actNo = 0;
				try {
					actNo = new Integer(req.getParameter("actNo").trim());
				} catch (Exception e) {
					actNo = 0;
					errorMsgs.add("請輸入活動編號.");
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
					req.setAttribute("actRegVO", vo2); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actRegister/update_actRegister_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				vo2 = actRegSvc.updateActRegister(actRegNo , actRegName, actRegDate, actRegTime , actRegPayState, memNo, actNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actRegVO", vo2); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actRegister/update_actRegister_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer actNo = new Integer(req.getParameter("actNo").trim());
				
				
				String actRegName = req.getParameter("actRegName").trim();
				java.sql.Date actRegDateInSqlDate = java.sql.Date.valueOf( ( req.getParameter("actRegDate").trim() ) );//Date指年月日
				java.sql.Timestamp actRegDate = new java.sql.Timestamp( actRegDateInSqlDate.getTime() );
				
				java.sql.Time actRegTimeInSqlTime = java.sql.Time.valueOf(req.getParameter("actRegTime").trim()); //Time指時分秒
				java.sql.Timestamp actRegTime= new java.sql.Timestamp(actRegTimeInSqlTime.getTime());
				

				String	actRegPayState = new String(req.getParameter("actRegPayState").trim());
				Integer	memNo = new Integer(req.getParameter("memNo").trim());
				Integer actNo = new Integer(req.getParameter("actNo").trim());
				
				/***************************2.開始新增資料***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				ActRegisterVO vo3 = actRegSvc.addActRegister(actRegName, actRegDate, actRegTime , actRegPayState, memNo, actNo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				RequestDispatcher successView1 = req.getRequestDispatcher("/front/actRegister/JavaMailProccess.jsp");
//				successView1.include(req, res);				
				String url = "/front/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉回會員報名頁面
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/addActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer actRegNo = new Integer(req.getParameter("actRegNo"));
				
				/***************************2.開始刪除資料***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				actRegSvc.deleteActRegister(actRegNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_By_MemNo_And_ActNo".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer actNo = new Integer(req.getParameter("actNo"));
				Integer memNo = new Integer(req.getParameter("memNo"));
				
				/***************************2.開始刪除資料***************************************/
				ActRegisterService actRegSvc = new ActRegisterService();
				actRegSvc.deleteByMemNoAndActNo(actNo , memNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front/actRegister/listAllActRegister.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/actRegister/listAllActRegister.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
