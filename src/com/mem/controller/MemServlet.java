package com.mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.*;

public class MemServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
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
							.getRequestDispatcher("/backend/mem/select_page.jsp");
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
							.getRequestDispatcher("/backend/mem/select_page.jsp");
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
						.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/backend/mem/listOneMem.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
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
						.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				HttpSession session = req.getSession();
//				session.setAttribute("listMem_ByState", list);
				
				req.setAttribute("listMem_ByState", set); // 資料庫取出的memVO物件,存入req
				String url = "/backend/mem/listMem_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listMems_ByState.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
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
				String url = "/backend/mem/update_mem_input.jsp";
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
							.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memno, memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				
				String url = null;
				if(requestURL.equals("/backend/mem/listAllMem.jsp")){
//					req.setAttribute("listOneMem", memSvc.getOneMem(memno));
					url = requestURL;
				}else if(requestURL.equals("/backend/mem/listMem_ByState.jsp")){
					req.setAttribute("listMem_ByState", memSvc.listMem_ByState(memVO.getMemstate()));
					url = requestURL;
				}else if(requestURL.equals("/backend/mem/listOneMem.jsp")){
					url = requestURL;
				}
				
//				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addMem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memid = req.getParameter("memid").trim();
				if (memid == null || (memid.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.add("請輸入姓名");
				}
				
				String memidno = req.getParameter("memidno").trim();
				if (memidno == null || (memidno.trim()).length() == 0) {
					errorMsgs.add("請輸入身分證字號");
				}
				
				String mememail = req.getParameter("mememail").trim();
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.add("請輸入e-mail");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.add("請輸入地址");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.add("請輸入電話");
				}
				
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
							.getRequestDispatcher("/backend/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		 if ("autoInsert".equals(action) ) {
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String memid =  new java.sql.Date(new java.util.Date().getTime()).toString().substring((int)(Math.random() * 10 ) ) + (int)( Math.random() * 100 )  ;
					String mempassword = (int)( Math.random()* 100 )  + "";
					
					String memname = "test";
					
					String memidno = "test" + (int)( Math.random()* 100000 )  ;
					
					String mememail = "test@yahoo.com";
					String memadd = "testCity";
					
					Integer memsex = 0;
					
					String memtel = "1234567";
					
					java.sql.Date membirth = new java.sql.Date(  new java.util.Date().getTime() );
				
					
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
					int memstate = 0 ;
					memVO.setMemstate(memstate);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt2.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					MemService memSvc = new MemService();
					memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
					memVO = new MemDAO().findByMemid(memid);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("memNoFromAuto", memVO.getMemno() );
					String url = "/backend/pet/addPetForApt.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/apt/addApt2.jsp");
//					failureView.forward(req, res);
					e.printStackTrace();
				}
		 }
		  
	}
}
