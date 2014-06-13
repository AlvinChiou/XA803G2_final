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
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("shiftNo").trim();
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String shiftNo = str;
				try {
					Integer.parseInt(shiftNo);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShiftService shiftSvc = new ShiftService();
				ShiftVO shiftVO = shiftSvc.getOneShift(shiftNo);
				if (shiftVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shift/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shiftVO", shiftVO); // 資料庫取出的empVO物件,存入req
				String url = "/shift/listOneShift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shift/select_page.jsp");
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
				
				/***************************2.開始查詢資料****************************************/
				ShiftService shiftSvc = new ShiftService();
				ShiftVO shiftVO = shiftSvc.getOneShift(shiftNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shiftVO", shiftVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/shift/update_shift_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/listAllShift.jsp");
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
				String drNo = req.getParameter("drNo").trim();				
				String shiftPeriod = req.getParameter("shiftPeriod").trim();
				String shiftNo  = req.getParameter("shiftNo");

				java.sql.Date shiftDate = null;
				try {
					shiftDate = java.sql.Date.valueOf(req.getParameter("shiftDate").trim());
				} catch (IllegalArgumentException e) {
					shiftDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer shiftMaximum = null;
				try {
					shiftMaximum = 5;
				} catch (NumberFormatException e) {
					shiftMaximum = 0;
					errorMsgs.add("薪水請填數字.");
				}


				ShiftVO shiftVO = new ShiftVO();
				shiftVO.setShiftDate(shiftDate);;
				shiftVO.setShiftPeriod(shiftPeriod);
				shiftVO.setShiftMaximum(shiftMaximum);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shiftVO", shiftVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/shift/update_shift_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShiftService shiftSvc = new ShiftService();
				shiftVO = shiftSvc.updateShift(shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shiftVO", shiftVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/shift-Knifeman.jsp";
				//修改後也更新記憶體裡的掛號陣列
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/update_shift_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String shiftPeriod = req.getParameter("shiftPeriod").trim();
				String drNo = req.getParameter("drNo").trim();
				
				java.sql.Date shiftDate = null;
				try {
					shiftDate = java.sql.Date.valueOf(req.getParameter("shiftDate").trim());
				} catch (IllegalArgumentException e) {
					shiftDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				int shiftMaximum = 0;
				try {
					shiftMaximum = 5;
				} catch (NumberFormatException e) {
					shiftMaximum = 0;
					errorMsgs.add("薪水請填數字.");
				}
	
				

				ShiftVO shiftVO = new ShiftVO();
				shiftVO.setShiftDate(shiftDate);;
				shiftVO.setShiftPeriod(shiftPeriod);
				shiftVO.setShiftMaximum(shiftMaximum);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shiftVO", shiftVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/shift/addShift.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ShiftService shiftSvc = new ShiftService();
				shiftVO = shiftSvc.addShift(shiftDate, shiftMaximum, shiftPeriod, drNo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				//新增後也更新記憶體裡的掛號陣列
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));
				
				String url = "/backend/shift-Knifeman.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/addShift.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date shiftDate = null;
			try {
				/***************************1.接收請求參數***************************************/
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
				/***************************2.開始刪除資料***************************************/
				ShiftService shiftSvc = new ShiftService();
				shiftSvc.deleteShift(shiftNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				AptServlet.createRegArrayFromDB((RegDay[])getServletContext().getAttribute("regDays"));

				String url = "/backend/shift/JSTL01_query4_Index_forEach_param2.jsp?shiftDate=" + shiftDate;
				//String url = "/shift/listAllShift.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/shift/listAllShift.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
