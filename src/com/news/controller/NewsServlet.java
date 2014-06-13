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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("newsno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer newsno = null;
				try {
					newsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				if (newsVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("newsVO", newsVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���														
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
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
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer newstype = new Integer( req.getParameter("newstype"));
				
				/*************************** 2.�}�l�d�߸�� ****************************************/
				NewsService newsSvc = new NewsService();
				List<NewsVO> listNews_Bytype = newsSvc.listNews_Bytype(newstype);
				
				if(listNews_Bytype.isEmpty()){                                 //list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/news/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listNews_Bytype", listNews_Bytype);
				String url = "/backend/news/listNews_Bytype.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer newsno = new Integer(req.getParameter("newsno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("newsVO", newsVO); // ��Ʈw���X��newsVO����,�s�Jreq
				String url = "/backend/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = multi.getParameter("requestURL");

//			try {
				/**************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer newsno = new Integer(multi.getParameter("newsno").trim());
				String newstitle = multi.getParameter("newstitle").trim();
				if (newstitle == null || newstitle.length() == 0) {
					errorMsgs.add("�峹���D���i���ť�");
				}
				
				Integer newstype = new Integer (multi.getParameter("newstype").trim());
				String newscontent = multi.getParameter("newscontent").trim();
				if(newscontent == null || newscontent.length() == 0){
					errorMsgs.add("�峹���e���i���ť�");
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
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("newsVO", newsVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/update_news_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				// NewsService newsSvc = new NewsService();
				newsVO = newsSvc.updateNews(newsno, newstitle, newstype, newscontent, buffer, newspotime, empno);

				/**************************** 3.�ק粒��,�ǳ����(Send the Success view)*************/
				//req.setAttribute("newsVO", newsVO); //
				// ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/news/listAllNews.jsp";
				if(requestURL.equals("/backend/news/listAllNews.jsp")){
					req.setAttribute("listOneNews", newsSvc.getOneNews(newsno));
					url = "/backend/news/listAllNews.jsp";
				}else if(requestURL.equals("/backend/news/listNews_Bytype.jsp")){
					req.setAttribute("listNews_Bytype", newsSvc.listNews_Bytype(newstype));
					url = "/backend/news/listNews_Bytype.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/news/update_news_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/************************ 1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String newstitle = multi.getParameter("newstitle").trim();
				if(newstitle == null || newstitle.length()==0){
					errorMsgs.put("newstitle", "�峹���D���i���ť�");
				}
				Integer newstype = new Integer (multi.getParameter("newstype").trim());
				
				String newscontent = multi.getParameter("newscontent").trim();
				if(newscontent == null || newscontent.length()==0){
					errorMsgs.put("newscontent", "�峹���e���i���ť�");
				}
				
				byte[] buffer = null;
				File newspicFile = multi.getFile("newspic");
				if (newspicFile == null || newspicFile.length() == 0) {
					errorMsgs.put("newspic", "�Ϥ����i���ť�");
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
					errorMsgs.put("newspotime", "�п�J���!");
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
					req.setAttribute("newsVO", newsVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(newstitle, newstype, newscontent,
						 buffer, newspotime, empno);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/backend/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front/news/addNews.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer newsno = new Integer(req.getParameter("newsno"));

				/*************************** 2.�}�l�R����� ***************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				newsSvc.deleteNews(newsno);

				/**************************** 3.�R������,�ǳ����(Send the Success view)***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
