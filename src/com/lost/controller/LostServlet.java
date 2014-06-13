package com.lost.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lost.model.LostService;
import com.lost.model.LostVO;
import com.oreilly.servlet.MultipartRequest;

public class LostServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();
		
		MultipartRequest multi = null;
		String action = null;
		
//		System.out.println(req.getContentType());
//		System.out.println(req.getMethod());
		
		if(contentType != null && contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(req, getServletContext().getRealPath("lost/images"), 5*1024*1024, "UTF-8");
			action = multi.getParameter("action");
		}else{
			action = req.getParameter("action");
		}
				
			
		if("getOne_For_Display".equals(action)){// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("lostno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer lostno = null;
				try{
					lostno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
				if(lostVO == null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("lostVO", lostVO); // 資料庫取出的lostVO物件,存入req
				String url = "/backend/lost/listOneLost.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// 成功轉交 listOneLost.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listLost_ByMemno".equals(action)){// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memno = null;
				try{
					memno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LostService lostSvc = new LostService();
				Set<LostVO> set = lostSvc.listLost_ByMemno(memno);
				if(set.isEmpty()){ //list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listLost_ByMemno", set); // 資料庫取出的lostVO物件,存入req
				String url = "/backend/lost/listLost_ByMemno.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// 成功轉交  listLosts_ByMemno.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listLost_ByState".equals(action)){ // 來自listAllMem.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer loststate = new Integer(req.getParameter("loststate")); 
				
				/***************************2.開始查詢資料*****************************************/
				LostService lostSvc = new LostService();
				Set<LostVO> set = lostSvc.listLost_ByState(loststate);
				
				if(set.isEmpty()){//list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listLost_ByState", set); // 資料庫取出的memVO物件,存入req
				String url = "/backend/lost/listLost_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listLosts_ByState.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)){ // 來自listAllLost.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
			req.setAttribute("requestURL", requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);          // 送出修改的來源網頁的第幾頁, 存入req(只用於:listAllLost.jsp)
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer lostno = new Integer(req.getParameter("lostno"));
				
				/***************************2.開始查詢資料****************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lostVO", lostVO);         // 資料庫取出的lostVO物件,存入req
				String url = "/backend/lost/update_lost_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_lost_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_lost_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = multi.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
				req.setAttribute("requestURL", requestURL);           // 送出修改的來源網頁路徑, 存入req
				
				String whichPage = multi.getParameter("whichPage");// 送出修改的來源網頁的第幾頁(只用於:listAllLost.jsp)
				req.setAttribute("whichPage", whichPage);          // 送出修改的來源網頁的第幾頁, 存入req(只用於:listAllLost.jsp)
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer lostno = new Integer(multi.getParameter("lostno").trim());
					String losttitle = multi.getParameter("losttitle").trim();
					if (losttitle == null || losttitle.length() == 0) {
						errorMsgs.add("文章標題不可為空白");
					}
					
					LostService lostSvc = new LostService();
					LostVO lostVO = lostSvc.getOneLost(lostno);//自選取修改時，取得lostVO資料
					
					File lostpicFile = multi.getFile("lostpic");
					byte[] buffer = null;
					
					if(lostpicFile==null){                     //判斷是否有新照片要上傳
						buffer = lostVO.getLostpic();
					}else{
						FileInputStream fis = new FileInputStream(lostpicFile);
						int len = fis.available();
						buffer = new byte[len];
						fis.read(buffer);
					}		
					
					
					String lostcontent = multi.getParameter("lostcontent").trim();
					if (lostcontent == null || lostcontent.length() == 0) {
						errorMsgs.add("文章內容不可為空白");
					}
					
					java.sql.Date losttime = null;
					try {
						losttime = java.sql.Date.valueOf(multi.getParameter("losttime").trim());
					} catch (IllegalArgumentException e) {
						losttime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Integer loststate = new Integer(multi.getParameter("loststate").trim());
					
					Integer memno = new Integer(multi.getParameter("memno").trim());
					
					lostVO.setLostno(lostno);
					lostVO.setLosttitle(losttitle);
					lostVO.setLostpic(buffer);
					lostVO.setLostcontent(lostcontent);
					lostVO.setLosttime(losttime);
					lostVO.setLoststate(loststate);
					lostVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lostVO", lostVO); // 含有輸入格式錯誤的lostVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/lost/update_lost_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
//					LostService lostSvc = new LostService();
					lostVO = lostSvc.updateLost(lostno, losttitle, buffer, lostcontent, losttime, loststate, memno);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("lostVO", lostVO); // 資料庫update成功後,正確的的lostVO物件,存入req
					
					String url = "/backend/lost/listAllLost.jsp";
					if(requestURL.equals("/backend/lost/listAllLost.jsp")){
						req.setAttribute("listOneLost", lostSvc.getOneLost(lostno)); // 資料庫取出的list物件,存入request
						url = "/backend/lost/listAllLost.jsp?whichPage=" + whichPage + "&lostno=" + lostno;
					}else if(requestURL.equals("/backend/lost/listLost_ByState.jsp")){
						req.setAttribute("listLost_ByState", lostSvc.listLost_ByState(lostVO.getLoststate()));
						url = requestURL + "?lostno=" + lostno;
					}else if(requestURL.equals("/backend/lost/listLost_ByMemno.jsp")){
						req.setAttribute("listLost_ByMemno", lostSvc.listLost_ByMemno(lostVO.getMemno()));
						url = requestURL + "?lostno=" + lostno;
					}
					System.out.println("url=" + url);
//					String url = requestURL + "?lostno=" + lostno;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLost.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/update_lost_input.jsp");
					failureView.forward(req, res);
				}
			}
			
		if ("insert".equals(action)) { // 來自addLost.jsp的請求  
				
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String losttitle = multi.getParameter("losttitle").trim();
					if (losttitle == null || losttitle.length() == 0) {
						errorMsgs.put("losttitle", "文章標題不可為空白");
					}
					
					byte[] buffer = null;
					File lostpic = multi.getFile("lostpic");
					if(lostpic == null || lostpic.length()==0){
						errorMsgs.put("lostpic", "圖片不可為空白");
					}else{
						FileInputStream fis = new FileInputStream(lostpic);
						int len = fis.available();
						buffer = new byte[len];
						fis.read(buffer);
						fis.close();
					}
					
					String lostcontent = multi.getParameter("lostcontent").trim();
					if (lostcontent == null || lostcontent.length() == 0) {
						errorMsgs.put("lostcontent", "文章內容不可為空白");
					}
					
					java.sql.Date losttime = null;
					try {
						losttime = java.sql.Date.valueOf(multi.getParameter("losttime").trim());
					} catch (IllegalArgumentException e) {
						losttime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.put("losttime", "請輸入日期!");
					}
					
					Integer loststate = new Integer(multi.getParameter("loststate").trim());
					if(loststate == null || loststate.toString().length()==0){
						errorMsgs.put("loststate", "狀態不可為空白");
					}
					
					Integer memno = new Integer(multi.getParameter("memno").trim());
					if(memno == null || memno.toString().length()==0){
						errorMsgs.put("memno", "會員編號不可為空白");
					}
					
					LostVO lostVO = new LostVO();
					
					lostVO.setLosttitle(losttitle);
					lostVO.setLostpic(buffer);
					lostVO.setLostcontent(lostcontent);
					lostVO.setLosttime(losttime);
					lostVO.setLoststate(loststate);
					lostVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("lostVO", lostVO); // 含有輸入格式錯誤的lostVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/lost/addLost.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					LostService lostSvc = new LostService();
					lostVO = lostSvc.addLost(losttitle, buffer, lostcontent, losttime, loststate, memno);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/backend/lost/listAllLost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception", e.getMessage());
					System.out.println(e);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/addLost.jsp");
					failureView.forward(req, res);
				}
			}
		
		if ("delete".equals(action)) { // 來自listAllLost.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/lost/listAllLost.jsp】 或  【/listLost_ByMemno.jsp】 或 【 /listLost_ByState.jsp】
			String whichPage = req.getParameter("whichPage");   // 送出刪除的來源網頁的第幾頁(只用於:listAllLost.jsp)
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer lostno = new Integer(req.getParameter("lostno"));
				
				/***************************2.開始刪除資料***************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
				lostSvc.deleteLost(lostno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = null;
				if(requestURL.equals("/backend/lost/listAllLost.jsp")){
					req.setAttribute("listOneLost", lostSvc.getOneLost(lostno));
					url = requestURL + "?whichPage" + whichPage;
				}else if(requestURL.equals("/backend/lost/listLost_ByMemno.jsp")){
					req.setAttribute("listLost_ByMemno", lostSvc.listLost_ByMemno(lostVO.getMemno()));
				    url = requestURL;
				}else if(requestURL.equals("/backend/lost/listLost_ByState.jsp")){
				    req.setAttribute("listLost_ByState", lostSvc.listLost_ByState(lostVO.getLoststate()));
					url = requestURL;
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/listAllLost.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
