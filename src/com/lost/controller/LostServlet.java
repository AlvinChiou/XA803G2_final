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
				
			
		if("getOne_For_Display".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("lostno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer lostno = null;
				try{
					lostno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
				if(lostVO == null){
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("lostVO", lostVO); // ��Ʈw���X��lostVO����,�s�Jreq
				String url = "/backend/lost/listOneLost.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// ���\��� listOneLost.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listLost_ByMemno".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("memno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer memno = null;
				try{
					memno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				LostService lostSvc = new LostService();
				Set<LostVO> set = lostSvc.listLost_ByMemno(memno);
				if(set.isEmpty()){ //list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listLost_ByMemno", set); // ��Ʈw���X��lostVO����,�s�Jreq
				String url = "/backend/lost/listLost_ByMemno.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// ���\���  listLosts_ByMemno.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listLost_ByState".equals(action)){ // �Ӧ�listAllMem.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer loststate = new Integer(req.getParameter("loststate")); 
				
				/***************************2.�}�l�d�߸��*****************************************/
				LostService lostSvc = new LostService();
				Set<LostVO> set = lostSvc.listLost_ByState(loststate);
				
				if(set.isEmpty()){//list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("listLost_ByState", set); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/backend/lost/listLost_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listLosts_ByState.jsp
				successView.forward(req, res);
			
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)){ // �Ӧ�listAllLost.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
			req.setAttribute("requestURL", requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);          // �e�X�ק諸�ӷ��������ĴX��, �s�Jreq(�u�Ω�:listAllLost.jsp)
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer lostno = new Integer(req.getParameter("lostno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("lostVO", lostVO);         // ��Ʈw���X��lostVO����,�s�Jreq
				String url = "/backend/lost/update_lost_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_lost_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_lost_input.jsp���ШD
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = multi.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
				req.setAttribute("requestURL", requestURL);           // �e�X�ק諸�ӷ��������|, �s�Jreq
				
				String whichPage = multi.getParameter("whichPage");// �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllLost.jsp)
				req.setAttribute("whichPage", whichPage);          // �e�X�ק諸�ӷ��������ĴX��, �s�Jreq(�u�Ω�:listAllLost.jsp)
				
				try {
					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					Integer lostno = new Integer(multi.getParameter("lostno").trim());
					String losttitle = multi.getParameter("losttitle").trim();
					if (losttitle == null || losttitle.length() == 0) {
						errorMsgs.add("�峹���D���i���ť�");
					}
					
					LostService lostSvc = new LostService();
					LostVO lostVO = lostSvc.getOneLost(lostno);//�ۿ���ק�ɡA���olostVO���
					
					File lostpicFile = multi.getFile("lostpic");
					byte[] buffer = null;
					
					if(lostpicFile==null){                     //�P�_�O�_���s�Ӥ��n�W��
						buffer = lostVO.getLostpic();
					}else{
						FileInputStream fis = new FileInputStream(lostpicFile);
						int len = fis.available();
						buffer = new byte[len];
						fis.read(buffer);
					}		
					
					
					String lostcontent = multi.getParameter("lostcontent").trim();
					if (lostcontent == null || lostcontent.length() == 0) {
						errorMsgs.add("�峹���e���i���ť�");
					}
					
					java.sql.Date losttime = null;
					try {
						losttime = java.sql.Date.valueOf(multi.getParameter("losttime").trim());
					} catch (IllegalArgumentException e) {
						losttime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
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
						req.setAttribute("lostVO", lostVO); // �t����J�榡���~��lostVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/lost/update_lost_input.jsp");
						failureView.forward(req, res);
						return; //�{�����_
					}
					
					/***************************2.�}�l�ק���*****************************************/
//					LostService lostSvc = new LostService();
					lostVO = lostSvc.updateLost(lostno, losttitle, buffer, lostcontent, losttime, loststate, memno);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
					req.setAttribute("lostVO", lostVO); // ��Ʈwupdate���\��,���T����lostVO����,�s�Jreq
					
					String url = "/backend/lost/listAllLost.jsp";
					if(requestURL.equals("/backend/lost/listAllLost.jsp")){
						req.setAttribute("listOneLost", lostSvc.getOneLost(lostno)); // ��Ʈw���X��list����,�s�Jrequest
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
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneLost.jsp
					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z*************************************/
				} catch (Exception e) {
					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/update_lost_input.jsp");
					failureView.forward(req, res);
				}
			}
			
		if ("insert".equals(action)) { // �Ӧ�addLost.jsp���ШD  
				
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					String losttitle = multi.getParameter("losttitle").trim();
					if (losttitle == null || losttitle.length() == 0) {
						errorMsgs.put("losttitle", "�峹���D���i���ť�");
					}
					
					byte[] buffer = null;
					File lostpic = multi.getFile("lostpic");
					if(lostpic == null || lostpic.length()==0){
						errorMsgs.put("lostpic", "�Ϥ����i���ť�");
					}else{
						FileInputStream fis = new FileInputStream(lostpic);
						int len = fis.available();
						buffer = new byte[len];
						fis.read(buffer);
						fis.close();
					}
					
					String lostcontent = multi.getParameter("lostcontent").trim();
					if (lostcontent == null || lostcontent.length() == 0) {
						errorMsgs.put("lostcontent", "�峹���e���i���ť�");
					}
					
					java.sql.Date losttime = null;
					try {
						losttime = java.sql.Date.valueOf(multi.getParameter("losttime").trim());
					} catch (IllegalArgumentException e) {
						losttime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.put("losttime", "�п�J���!");
					}
					
					Integer loststate = new Integer(multi.getParameter("loststate").trim());
					if(loststate == null || loststate.toString().length()==0){
						errorMsgs.put("loststate", "���A���i���ť�");
					}
					
					Integer memno = new Integer(multi.getParameter("memno").trim());
					if(memno == null || memno.toString().length()==0){
						errorMsgs.put("memno", "�|���s�����i���ť�");
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
						req.setAttribute("lostVO", lostVO); // �t����J�榡���~��lostVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/lost/addLost.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					LostService lostSvc = new LostService();
					lostVO = lostSvc.addLost(losttitle, buffer, lostcontent, losttime, loststate, memno);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/backend/lost/listAllLost.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception", e.getMessage());
					System.out.println(e);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/lost/addLost.jsp");
					failureView.forward(req, res);
				}
			}
		
		if ("delete".equals(action)) { // �Ӧ�listAllLost.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/lost/listAllLost.jsp�j ��  �i/listLost_ByMemno.jsp�j �� �i /listLost_ByState.jsp�j
			String whichPage = req.getParameter("whichPage");   // �e�X�R�����ӷ��������ĴX��(�u�Ω�:listAllLost.jsp)
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer lostno = new Integer(req.getParameter("lostno"));
				
				/***************************2.�}�l�R�����***************************************/
				LostService lostSvc = new LostService();
				LostVO lostVO = lostSvc.getOneLost(lostno);
				lostSvc.deleteLost(lostno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
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
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/lost/listAllLost.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
