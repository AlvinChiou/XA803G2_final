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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("empNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer empNo = null;
				try {
					empNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的employeeVO物件,存入req
				String url = "/backend/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
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
				Integer empNo = new Integer(req.getParameter("empNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的employeeVO物件,存入req
				String url = "/backend/emp/update_emp_input.jsp";
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
			
			String requestURL = multi.getParameter("requestURL");

//			try {
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer empNo = new Integer(multi.getParameter("empNo").trim());
				String empName = multi.getParameter("empName").trim();

				java.sql.Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(multi.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String empTel = multi.getParameter("empTel").trim();
				String empSex = multi.getParameter("empSex").trim();
				
				String empPos = null;
				try{
					empPos = multi.getParameter("empPos").trim();
				}catch(Exception e){
					empPos = "";
					errorMsgs.add("請選擇職位");
				}

				Double empSalary = null;
				try {
					empSalary = new Double(multi.getParameter("empSalary")
							.trim());
				} catch (NumberFormatException e) {
					empSalary = 0.0;
					errorMsgs.add("薪資請填數字.");
				}

				java.sql.Date empArrDate = null;
				try {
					empArrDate = java.sql.Date.valueOf(multi.getParameter("empArrDate").trim());
				} catch (IllegalArgumentException e) {
					empArrDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date empOff = null;
				try {
					empOff = java.sql.Date.valueOf(multi.getParameter("empOff")
							.trim());
				} catch (IllegalArgumentException e) {
					empOff = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** * 2.開始修改資料*****************************************/
				// EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.updateEmp(empNo, empName, empBirth, empTel,
						empSex, empPos, empSalary, empArrDate, empOff, empID,
						empAdd, buffer, empPassword, empEmail);

				/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的employeeVO物件,存入req
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/**************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
				String empName = multi.getParameter("empName").trim();

				java.sql.Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(multi.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String empTel = null;
				try{
					empTel = multi.getParameter("empTel").trim();
				}catch(Exception e){
					empTel = "";
					errorMsgs.add("請輸入電話");
				}
				String empSex = multi.getParameter("empSex").trim();
				String empPos = null;
				try{
					empPos = multi.getParameter("empPos").trim();
				}catch(Exception e){
					empPos = "";
					errorMsgs.add("請選擇職位");
				}

				Double empSalary = null;
				try {
					empSalary = new Double(multi.getParameter("empSalary")
							.trim());
				} catch (NumberFormatException e) {
					empSalary = 0.0;
					errorMsgs.add("薪水請填數字.");
				}

				java.sql.Date empArrDate = null;
				try {
					empArrDate = java.sql.Date.valueOf(multi.getParameter(
							"empArrDate").trim());
				} catch (IllegalArgumentException e) {
					empArrDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入到職日期!");
				}

				java.sql.Date empOff = null;
				try {
					empOff = java.sql.Date.valueOf(multi.getParameter("empOff")
							.trim());
				} catch (IllegalArgumentException e) {
					empOff = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入離開日期!");
				}

				String empID = multi.getParameter("empID").trim();
				String empAdd = multi.getParameter("empAdd").trim();

				File empPicFile = multi.getFile("empPic");
				byte[] buffer = null;
				if(empPicFile == null || empPicFile.length()==0){
					errorMsgs.add("圖片不可為空白");
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
					}else if (z == 2) { // 放大寫英文
				        sb.append((char) (((Math.random() * 26) + 65)));
				    }else {             // 放小寫英文
				        sb.append(((char) ((Math.random() * 26) + 97)));
				    }
				}
					
				String empPassword = sb.toString();
				String empEmail = multi.getParameter("empEmail").trim();
				if(empEmail == null || empEmail.length() == 0){
					errorMsgs.add("請輸入EMAIL");
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
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.addEmp(empName, empBirth, empTel, empSex,
						empPos, empSalary, empArrDate, empOff, empID, empAdd,
						buffer, empPassword, empEmail);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("employeeVO", employeeVO);
				String url2 = "/backend/emp/JavaMailProccess.jsp";
				RequestDispatcher sendMail = req.getRequestDispatcher(url2);
				sendMail.include(req, res);
				
				String url = "/backend/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			String whichPage = req.getParameter("whichPage");
			
//			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer empNo = new Integer(req.getParameter("empNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empNo);
				empSvc.deleteEmp(empNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL + "?whichPage" + whichPage ;
				req.setAttribute("listOneEmp", empSvc.getOneEmp(empNo));
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
		}
	}
}
