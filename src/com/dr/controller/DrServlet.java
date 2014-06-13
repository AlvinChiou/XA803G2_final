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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("drNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入醫師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer drNo = null;
				try {
					drNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("醫師編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);
				if (doctorVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("doctorVO", doctorVO); // 資料庫取出的doctorloyeeVO物件,存入req
				String url = "/backend/dr/listOneDr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer drNo = new Integer(req.getParameter("drNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("doctorVO", doctorVO); // 資料庫取出的doctorVO物件,存入req
				String url = "/backend/dr/update_dr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_dr_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_dr_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = multi.getParameter("requestURL");

			try {
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

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
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("doctorVO", doctorVO); // 含有輸入格式錯誤的drVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/dr/update_dr_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** * 2.開始修改資料*****************************************/
				
				doctorVO = drSvc.updateDr(drNo, drName, drExp, drSex, buffer, drBirth, drAdd, drTel);

				/**************************** 3.修改完成,準備轉交(Send the Success view)*************/

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/**************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dr/update_dr_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
				String drName = multi.getParameter("drName").trim();
				String drExp = multi.getParameter("drExp").trim();
				String drSex = multi.getParameter("drSex").trim();
				
				File drPicFile = multi.getFile("drPic");
				byte[] buffer = null;
				if(drPicFile == null || drPicFile.length()==0){
					errorMsgs.add("圖片不可為空白");
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
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("doctorVO", doctorVO); // 含有輸入格式錯誤的doctorVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/dr/addDr.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				
				DoctorService drSvc = new DoctorService();
			
				doctorVO = drSvc.addDr(drName, drExp, drSex, buffer, drBirth, drAdd, drTel);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/dr/listAllDr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/dr/addDr.jsp");
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
				/*************************** 1.接收請求參數 ***************************************/
				Integer drNo = new Integer(req.getParameter("drNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				DoctorService drSvc = new DoctorService();
				DoctorVO doctorVO = drSvc.getOneDr(drNo);
				drSvc.deleteDr(drNo);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL ;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
