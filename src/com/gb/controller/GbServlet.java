package com.gb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gb.model.GbService;
import com.gb.model.GbVO;
import com.lost.model.LostService;
import com.lost.model.LostVO;
import com.news.model.NewsService;
import com.news.model.NewsVO;

public class GbServlet extends HttpServlet {

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
		
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("gbno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer gbno = null;
				try{
					gbno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
				if(gbVO == null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("gbVO", gbVO); // 資料庫取出的lostVO物件,存入req
				String url = "/backend/gb/listOneGb.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// 成功轉交 listOneLost.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/gb/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listGb_Bylostno".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
			req.setAttribute("requestURL", requestURL);
			
			try{
				/*************************** 1.接收請求參數 ****************************************/
				Integer lostno = new Integer( req.getParameter("lostno"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				GbService gbSvc = new GbService();
				List<GbVO> listGb_Bylostno = gbSvc.listGb_Bylostno(lostno);
				
				if(listGb_Bylostno.isEmpty()){       //list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGb_Bylostno", listGb_Bylostno);
				String url = "/backend/gb/listGb_Bylostno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/gb/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if("listGb_Bymemno".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
			req.setAttribute("requestURL", requestURL);
			
			try{
				/*************************** 1.接收請求參數 ****************************************/
				Integer memno = new Integer( req.getParameter("memno"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				GbService gbSvc = new GbService();
				List<GbVO> listGb_Bymemno = gbSvc.listGb_Bymemno(memno);
				
				if(listGb_Bymemno.isEmpty()){                                 //list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGb_Bymemno", listGb_Bymemno);
				String url = "/backend/gb/listGb_Bymemno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/gb/select_page.jsp");
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
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer gbno = new Integer(req.getParameter("gbno"));
//				System.out.println(gbno);
				/***************************2.開始查詢資料****************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("gbVO", gbVO);         // 資料庫取出的lostVO物件,存入req
				String url = "/backend/gb/update_gb_input.jsp";
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
				
				String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
				req.setAttribute("requestURL", requestURL);           // 送出修改的來源網頁路徑, 存入req
				
				String whichPage = req.getParameter("whichPage");// 送出修改的來源網頁的第幾頁(只用於:listAllLost.jsp)
				req.setAttribute("whichPage", whichPage);          // 送出修改的來源網頁的第幾頁, 存入req(只用於:listAllLost.jsp)
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer gbno = new Integer(req.getParameter("gbno").trim());
					String gbtitle = req.getParameter("gbtitle").trim();
					if (gbtitle == null || gbtitle.length() == 0) {
						errorMsgs.add("文章標題不可為空白");
					}
					
					String gbcontent = req.getParameter("gbcontent").trim();
					if (gbcontent == null || gbcontent.length() == 0) {
						errorMsgs.add("文章內容不可為空白");
					}
					
					java.sql.Timestamp gbtime = new java.sql.Timestamp(System.currentTimeMillis());
					try {
						gbtime = java.sql.Timestamp.valueOf(req.getParameter("gbtime").trim());
					} catch (IllegalArgumentException e) {
						gbtime = new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Integer lostno = new Integer(req.getParameter("lostno").trim());
					Integer memno = new Integer(req.getParameter("memno").trim());
					
					GbService gbSvc = new GbService();
					GbVO gbVO = gbSvc.getOneGb(gbno);
					gbVO.setGbno(gbno);
					gbVO.setGbtitle(gbtitle);
					gbVO.setGbcontent(gbcontent);
					gbVO.setGbtime(gbtime);
					gbVO.setLostno(lostno);
					gbVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("gbVO", gbVO); // 含有輸入格式錯誤的lostVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/gb/update_gb_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
//					LostService lostSvc = new LostService();
					gbVO = gbSvc.updateGb(gbno, gbtitle, gbcontent, gbtime, lostno, memno);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("gbno", gbno); // 資料庫update成功後,正確的的lostVO物件,存入req
					
					String url = null;
					if(requestURL.equals("/backend/gb/listAllGb.jsp")){
						url = "/backend/gb/listAllGb.jsp";
					}else if(requestURL.equals("/backend/gb/listGb_Bylostno.jsp")){
						req.setAttribute("listGb_Bylostno", gbSvc.listGb_Bylostno(lostno));
						url = requestURL;
					}else if(requestURL.equals("/backend/gb/listGb_Bymemno.jsp")){
						req.setAttribute("listGb_Bymemno", gbSvc.listGb_Bymemno(memno));
						url = requestURL;
					}
//					System.out.println("url=" + url);
//					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLost.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/update_gb_input.jsp");
					failureView.forward(req, res);
				}
			}
			
		if ("insert".equals(action)) { // 來自addLost.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/lost/listAllLost.jsp】
				req.setAttribute("requestURL", requestURL);           // 送出修改的來源網頁路徑, 存入req

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String gbtitle = req.getParameter("gbtitle").trim();
					if (gbtitle == null || gbtitle.length() == 0) {
						errorMsgs.add("文章標題不可為空白");
					}
					
					String gbcontent = req.getParameter("gbcontent").trim();
					if (gbcontent == null || gbcontent.length() == 0) {
						errorMsgs.add("文章內容不可為空白");
					}
					
					java.sql.Timestamp gbtime = new java.sql.Timestamp(System.currentTimeMillis());
					try {
						gbtime = java.sql.Timestamp.valueOf(req.getParameter("gbtime").trim());
					} catch (IllegalArgumentException e) {
						gbtime = new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Integer lostno = new Integer(req.getParameter("lostno").trim());
					Integer memno = new Integer(req.getParameter("memno").trim());
					if(memno == null || memno.toString().length()==0){
						errorMsgs.add("會員編號不可為空白");
					}
					
					GbVO gbVO = new GbVO();
					
					gbVO.setGbtitle(gbtitle);
					gbVO.setGbcontent(gbcontent);
					gbVO.setGbtime(gbtime);
					gbVO.setLostno(lostno);
					gbVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("gbVO", gbVO); // 含有輸入格式錯誤的lostVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					GbService gbSvc = new GbService();
					gbVO = gbSvc.addGb(gbtitle, gbcontent, gbtime, lostno, memno);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/backend/lost/listAllLost.jsp";
					
//					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					System.out.println(e);
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		
		if ("delete".equals(action)) { // 來自listAllLost.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/lost/listAllLost.jsp】 或  【/listLost_ByMemno.jsp】 或 【 /listLost_ByState.jsp】
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer gbno = new Integer(req.getParameter("gbno"));
				
				/***************************2.開始刪除資料***************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
				gbSvc.deleteGb(gbno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = null;
//				req.setAttribute("list", gbSvc.listGb_Bymemno(gbVO.getMemno()));
				
				if(requestURL.equals("/backend/gb/listGb_Bymemno.jsp")){
					req.setAttribute("listGb_Bymemno", gbSvc.listGb_Bymemno(gbVO.getMemno()));
				}else if(requestURL.equals("/backend/gb/listGb_Bylostno.jsp")){
					req.setAttribute("listGb_Bylostno", gbSvc.listGb_Bylostno(gbVO.getLostno()));
				}else if(requestURL.equals("/backend/gb/listAllGb.jsp")){
					requestURL = "/backend/gb/listAllGb.jsp";
				}else if(requestURL.equals("/backend/gb/listGbs_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<GbVO> list = gbSvc.getAll(map);
					req.setAttribute("listGb_ByCompositeQuery", list);
				}
//				}else if(requestURL.equals("/front/lost/listLost_ByState.jsp")){
//				    req.setAttribute("listLost_ByState", lostSvc.listLost_ByState(lostVO.getLoststate()));
//					url = requestURL;
//				}
				url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/gb/listGb_Bymemno.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listGbs_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
//			try {
				
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
				GbService gbSvc = new GbService();
				List<GbVO> list  = gbSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listGb_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/backend/gb/listGbs_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/gb/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}		
		
	}

}
