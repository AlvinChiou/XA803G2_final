package com.mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.*;

public class MemServletFront extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memno");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memno = null;
				try{
					memno = new Integer(str);
				}catch (Exception e){
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memno);
				if(memVO == null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/front/mem/listOneMem.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listMem_ByState".equals(action)){// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				Set<MemVO> set = memSvc.listMem_ByState(memstate);
				
				if(set.size()==0){
//				if(list.isEmpty()){list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				HttpSession session = req.getSession();
//				session.setAttribute("listMem_ByState", list);
				
				req.setAttribute("listMem_ByState", set); // 資料庫取出的memVO物件,存入req
				String url = "/front/mem/listMem_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listMems_ByState.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/mem/listAllMem.jsp】
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:listAllMem.jsp)
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer memno = new Integer(req.getParameter("memno"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的memVO物件,存入req
				String url = "/front/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
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
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/mem/listAllMem.jsp】
			//String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:listAllMem.jsp)
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memno = new Integer(req.getParameter("memno").trim());
				String memid = req.getParameter("memid").trim();
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.add("密碼不可為空白");
				}
				
				String checkpassword = req.getParameter("checkpassword").trim();
				if(!checkpassword.equals(mempassword)){
					errorMsgs.add("密碼前後不一致，請更正");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.add("姓名不可為空白");
				}
				
				String memidno = req.getParameter("memidno").trim();
				
				String mememail = req.getParameter("mememail").trim();
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.add("信箱不可為空白");
				}
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.add("地址不可為空白");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.add("請輸入電話號碼");
				}
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				MemVO memVO = new MemVO();
				memVO.setMemno(memno);
				memVO.setMemid(memid);
				memVO.setMempassword(mempassword);
				memVO.setMemname(memname);
				memVO.setMemidno(memidno);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setMemadd(memadd);
				memVO.setMemsex(memsex);
				memVO.setMemtel(memtel);
				memVO.setMemstate(memstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memno, memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				
				String url = "/front/mem/listOneMem.jsp";
				if(requestURL.equals("/front/mem/listAllMem.jsp")){
//					req.setAttribute("listOneMem", memSvc.getOneMem(memno));
					url = requestURL;
				}else if(requestURL.equals("/front/mem/listMem_ByState.jsp")){
					req.setAttribute("listMem_ByState", memSvc.listMem_ByState(memVO.getMemstate()));
					url = requestURL;
				}else if(requestURL.equals("/front/mem/listOneMem.jsp")){
					url = requestURL;
				}
				
//				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addMem.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memid = req.getParameter("memid").trim();
				String memReg = "^[(a-zA-Z0-9)]{6,15}$";
				if (memid == null || (memid.trim()).length() == 0) {
					errorMsgs.put("memid", "請輸入帳號");
				}else if(!memid.trim().matches(memReg)){
					errorMsgs.put("memid", "帳號只能是英文字母、數字 , 且長度必需在6到15之間");
				}
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.put("mempassword", "請輸入密碼");
				}else if(!mempassword.trim().matches(memReg)){
					errorMsgs.put("mempassword", "密碼只能是英文字母、數字 , 且長度必需在6到15之間");
				}
				
				String checkpassword = req.getParameter("checkpassword").trim();
				
				if(!checkpassword.equals(mempassword)){
					errorMsgs.put("mempassword", "密碼前後不一致，請更正");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.put("memname", "請輸入姓名");
				}
				
				String memidno = req.getParameter("memidno").trim();
				String idnoReg = "^[A-Z]{1}[0-9]{9}$";
				if (memidno == null || (memidno.trim()).length() == 0) {
					errorMsgs.put("memidno", "請輸入身分證字號");
				}else if(!memidno.trim().matches(idnoReg)){
					errorMsgs.put("memidno", "身份證字號格式錯誤");
				}
				
				String mememail = req.getParameter("mememail").trim();
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.put("mememail", "請輸入e-mail");
				}else if(!mememail.trim().matches(emailReg)){
					errorMsgs.put("mememail", "e-mail格式錯誤");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.put("memadd", "請輸入地址");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				String telReg = "^09[0-9]{8}$";
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.put("memtel", "請輸入電話");
				}else if(!memtel.trim().matches(telReg)){
					errorMsgs.put("memtel", "手機號碼格式錯誤");
				}
				
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("membirth", "請輸入日期!");
				}
				
				MemVO memVO = new MemVO();

				memVO.setMemid(memid);
				memVO.setMempassword(mempassword);
				memVO.setMemname(memname);
				memVO.setMemidno(memidno);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setMemadd(memadd);
				memVO.setMemsex(memsex);
				memVO.setMemtel(memtel);
				memVO.setMemstate(memstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("memVO", memVO);
				String url2 = "/front/mem/JavaMailProccess.jsp";
				RequestDispatcher sendMail = req.getRequestDispatcher(url2);
				sendMail.include(req, res);
				
				String url = "/afterMemCreate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		  
	}
}
