package com.news.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.news.model.NewsService;
import com.news.model.NewsVO;
import com.oreilly.servlet.MultipartRequest;

public class NewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();

		MultipartRequest multi = null;
		String action = null;

		if (contentType != null && contentType.startsWith("multipart/form-data")) {
			multi = new MultipartRequest(req, getServletContext().getRealPath("backend/news/photo"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("newsno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入消息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer newsno = null;
				try {
					newsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("消息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交														
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listNews_Bytype".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/*************************** 1.接收請求參數 ****************************************/
				Integer newstype = new Integer( req.getParameter("newstype"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				NewsService newsSvc = new NewsService();
				List<NewsVO> listNews_Bytype = newsSvc.listNews_Bytype(newstype);
				
				if(listNews_Bytype.isEmpty()){                                 //list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listNews_Bytype", listNews_Bytype);
				String url = "/backend/news/listNews_Bytype.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
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
				Integer newsno = new Integer(req.getParameter("newsno"));

				/*************************** 2.開始查詢資料 ****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件,存入req
				String url = "/backend/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer newsno = new Integer(multi.getParameter("newsno").trim());
				String newstitle = multi.getParameter("newstitle").trim();
				if (newstitle == null || newstitle.length() == 0) {
					errorMsgs.add("文章標題不可為空白");
				}
				
				Integer newstype = new Integer (multi.getParameter("newstype").trim());
				String newscontent = multi.getParameter("newscontent").trim();
				if(newscontent == null || newscontent.length() == 0){
					errorMsgs.add("文章內容不可為空白");
				}
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);

				byte[] buffer = null;
				File newspicFile = multi.getFile("newspic");
				
				if (newspicFile == null) {
					buffer = newsVO.getNewspic();
				} else {
					FileInputStream fis = new FileInputStream(newspicFile);
					int len = fis.available();
					buffer = new byte[len];
					fis.read(buffer);
				}

				java.sql.Date newspotime = null;
				try {
					newspotime = java.sql.Date.valueOf(multi.getParameter("newspotime").trim());
				} catch (IllegalArgumentException e) {
					newspotime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer empno = new Integer(multi.getParameter("empno").trim());

				newsVO.setNewstitle(newstitle);
				newsVO.setNewstype(newstype);
				newsVO.setNewscontent(newscontent);
				newsVO.setNewspic(buffer);
				newsVO.setNewspotime(newspotime);
				newsVO.setEmpno(empno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/update_news_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				// NewsService newsSvc = new NewsService();
				newsVO = newsSvc.updateNews(newsno, newstitle, newstype, newscontent, buffer, newspotime, empno);

				/**************************** 3.修改完成,準備轉交(Send the Success view)*************/
				//req.setAttribute("newsVO", newsVO); //
				// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/news/listAllNews.jsp";
				if(requestURL.equals("/backend/news/listAllNews.jsp")){
					req.setAttribute("listOneNews", newsSvc.getOneNews(newsno));
					url = "/backend/news/listAllNews.jsp";
				}else if(requestURL.equals("/backend/news/listNews_Bytype.jsp")){
					req.setAttribute("listNews_Bytype", newsSvc.listNews_Bytype(newstype));
					url = "/backend/news/listNews_Bytype.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/news/update_news_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String newstitle = multi.getParameter("newstitle").trim();
				if(newstitle == null || newstitle.length()==0){
					errorMsgs.put("newstitle", "文章標題不可為空白");
				}
				Integer newstype = new Integer (multi.getParameter("newstype").trim());
				
				String newscontent = multi.getParameter("newscontent").trim();
				if(newscontent == null || newscontent.length()==0){
					errorMsgs.put("newscontent", "文章內容不可為空白");
				}
				
				byte[] buffer = null;
				File newspicFile = multi.getFile("newspic");
				if (newspicFile == null || newspicFile.length() == 0) {
					errorMsgs.put("newspic", "圖片不可為空白");
				}else{
					FileInputStream fis = new FileInputStream(newspicFile);
					int len = fis.available();
					buffer = new byte[len];
					fis.read(buffer);
					fis.close();
				}
				
				java.sql.Date newspotime = null;
				try {
					newspotime = java.sql.Date.valueOf(multi.getParameter("newspotime").trim());
				} catch (IllegalArgumentException e) {
					newspotime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("newspotime", "請輸入日期!");
				}

				Integer empno = new Integer(multi.getParameter("empno").trim());

				NewsVO newsVO = new NewsVO();

				newsVO.setNewstitle(newstitle);
				newsVO.setNewstype(newstype);
				newsVO.setNewscontent(newscontent);
				newsVO.setNewspic(buffer);
				newsVO.setNewspotime(newspotime);
				newsVO.setEmpno(empno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(newstitle, newstype, newscontent,
						 buffer, newspotime, empno);

				/*************************** 3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/news/addNews.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer newsno = new Integer(req.getParameter("newsno"));

				/*************************** 2.開始刪除資料 ***************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				newsSvc.deleteNews(newsno);

				/**************************** 3.刪除完成,準備轉交(Send the Success view)***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
