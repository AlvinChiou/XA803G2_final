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
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("drRecNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer drRecNo = null;
				try {
					drRecNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				DrRecordVO drRecVO = drRecSvc.getOneDrRecord(drRecNo);
				if (drRecVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("drRecVO", drRecVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/drRecord/listOneDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/select_page.jsp");
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
				Integer drRecNo = new Integer(req.getParameter("drRecNo"));
				
				/***************************2.開始查詢資料****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				DrRecordVO drRecVO = drRecSvc.getOneDrRecord(drRecNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("drRecVO", drRecVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/drRecord/update_drRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/listAllDrRecord.jsp");
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
				Integer drRecNo = new Integer(req.getParameter("drRecNo").trim());
				
				
				Integer petNo = null ;
				try {
					petNo =new Integer(req.getParameter("petNo").trim());
				} catch (Exception e) {
					petNo = null;
					errorMsgs.add("請輸入寵物編號");
				}
				
				java.sql.Date drRecTime = null;
				try {
					drRecTime = java.sql.Date.valueOf(req.getParameter("drRecTime").trim());
				} catch (IllegalArgumentException e) {
					drRecTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請輸入醫生編號");
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
					req.setAttribute("drRecVO", vo2); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/update_drRecord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DrRecordService drRecSvc = new DrRecordService();
				vo2 = drRecSvc.updateDrRecord(drRecNo , drRecTime, drRecPres,drNo,petNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("drRecVO", vo2); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/drRecord/listOneDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/update_drRecord_input.jsp");
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
//				Integer actNo = new Integer(multi.getParameter("actNo").trim());
				
				Integer petNo = null;
				try {
					petNo = new Integer(multi.getParameter("petNo").trim());
				} catch (NumberFormatException e) {
					petNo = null;
					errorMsgs.add("請輸入寵物編號");
				}
				
				java.sql.Date drRecTime = null;
				try {
					drRecTime = java.sql.Date.valueOf(multi.getParameter("drRecTime").trim());
				} catch (IllegalArgumentException e) {
					drRecTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					errorMsgs.add("請輸入處方，若沒有，則輸入'無'");
				}
				
				Integer drNo = null;
				try {
					drNo = new Integer(multi.getParameter("drNo").trim());
				} catch (NumberFormatException e) {
					drNo = null;
					errorMsgs.add("請輸入寵物編號");
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
					req.setAttribute("drRecVO", vo3); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/drRecord/addDrRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DrRecordService drRecSvc = new DrRecordService();
				vo3 = drRecSvc.addDrRecord(drRecTime, drRecPres,drNo,petNo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/drRecord/listAllDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/addDrRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer drRecNo = new Integer(req.getParameter("drRecNo"));
				
				/***************************2.開始刪除資料***************************************/
				DrRecordService drRecSvc = new DrRecordService();
				drRecSvc.deleteDrRecord(drRecNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/drRecord/listAllDrRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/drRecord/listAllDrRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
