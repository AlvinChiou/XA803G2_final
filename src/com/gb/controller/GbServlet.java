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
		
		if("getOne_For_Display".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("gbno");
				if(str == null || (str.trim().length() == 0)){
					errorMsgs.add("�п�J�峹�s��");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer gbno = null;
				try{
					gbno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
				if(gbVO == null){
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("gbVO", gbVO); // ��Ʈw���X��lostVO����,�s�Jreq
				String url = "/backend/gb/listOneGb.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);// ���\��� listOneLost.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
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
			
			String requestURL = req.getParameter("requestURL");// �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
			req.setAttribute("requestURL", requestURL);
			
			try{
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer lostno = new Integer( req.getParameter("lostno"));
				
				/*************************** 2.�}�l�d�߸�� ****************************************/
				GbService gbSvc = new GbService();
				List<GbVO> listGb_Bylostno = gbSvc.listGb_Bylostno(lostno);
				
				if(listGb_Bylostno.isEmpty()){       //list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listGb_Bylostno", listGb_Bylostno);
				String url = "/backend/gb/listGb_Bylostno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/gb/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if("listGb_Bymemno".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
			req.setAttribute("requestURL", requestURL);
			
			try{
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer memno = new Integer( req.getParameter("memno"));
				
				/*************************** 2.�}�l�d�߸�� ****************************************/
				GbService gbSvc = new GbService();
				List<GbVO> listGb_Bymemno = gbSvc.listGb_Bymemno(memno);
				
				if(listGb_Bymemno.isEmpty()){                                 //list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listGb_Bymemno", listGb_Bymemno);
				String url = "/backend/gb/listGb_Bymemno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/gb/select_page.jsp");
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
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer gbno = new Integer(req.getParameter("gbno"));
//				System.out.println(gbno);
				/***************************2.�}�l�d�߸��****************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("gbVO", gbVO);         // ��Ʈw���X��lostVO����,�s�Jreq
				String url = "/backend/gb/update_gb_input.jsp";
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
				
				String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
				req.setAttribute("requestURL", requestURL);           // �e�X�ק諸�ӷ��������|, �s�Jreq
				
				String whichPage = req.getParameter("whichPage");// �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllLost.jsp)
				req.setAttribute("whichPage", whichPage);          // �e�X�ק諸�ӷ��������ĴX��, �s�Jreq(�u�Ω�:listAllLost.jsp)
				
				try {
					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					Integer gbno = new Integer(req.getParameter("gbno").trim());
					String gbtitle = req.getParameter("gbtitle").trim();
					if (gbtitle == null || gbtitle.length() == 0) {
						errorMsgs.add("�峹���D���i���ť�");
					}
					
					String gbcontent = req.getParameter("gbcontent").trim();
					if (gbcontent == null || gbcontent.length() == 0) {
						errorMsgs.add("�峹���e���i���ť�");
					}
					
					java.sql.Timestamp gbtime = new java.sql.Timestamp(System.currentTimeMillis());
					try {
						gbtime = java.sql.Timestamp.valueOf(req.getParameter("gbtime").trim());
					} catch (IllegalArgumentException e) {
						gbtime = new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
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
						req.setAttribute("gbVO", gbVO); // �t����J�榡���~��lostVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/gb/update_gb_input.jsp");
						failureView.forward(req, res);
						return; //�{�����_
					}
					
					/***************************2.�}�l�ק���*****************************************/
//					LostService lostSvc = new LostService();
					gbVO = gbSvc.updateGb(gbno, gbtitle, gbcontent, gbtime, lostno, memno);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
					req.setAttribute("gbno", gbno); // ��Ʈwupdate���\��,���T����lostVO����,�s�Jreq
					
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
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneLost.jsp
					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z*************************************/
				} catch (Exception e) {
					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/gb/update_gb_input.jsp");
					failureView.forward(req, res);
				}
			}
			
		if ("insert".equals(action)) { // �Ӧ�addLost.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/lost/listAllLost.jsp�j
				req.setAttribute("requestURL", requestURL);           // �e�X�ק諸�ӷ��������|, �s�Jreq

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					String gbtitle = req.getParameter("gbtitle").trim();
					if (gbtitle == null || gbtitle.length() == 0) {
						errorMsgs.add("�峹���D���i���ť�");
					}
					
					String gbcontent = req.getParameter("gbcontent").trim();
					if (gbcontent == null || gbcontent.length() == 0) {
						errorMsgs.add("�峹���e���i���ť�");
					}
					
					java.sql.Timestamp gbtime = new java.sql.Timestamp(System.currentTimeMillis());
					try {
						gbtime = java.sql.Timestamp.valueOf(req.getParameter("gbtime").trim());
					} catch (IllegalArgumentException e) {
						gbtime = new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					Integer lostno = new Integer(req.getParameter("lostno").trim());
					Integer memno = new Integer(req.getParameter("memno").trim());
					if(memno == null || memno.toString().length()==0){
						errorMsgs.add("�|���s�����i���ť�");
					}
					
					GbVO gbVO = new GbVO();
					
					gbVO.setGbtitle(gbtitle);
					gbVO.setGbcontent(gbcontent);
					gbVO.setGbtime(gbtime);
					gbVO.setLostno(lostno);
					gbVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("gbVO", gbVO); // �t����J�榡���~��lostVO����,�]�s�Jreq
						RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					GbService gbSvc = new GbService();
					gbVO = gbSvc.addGb(gbtitle, gbcontent, gbtime, lostno, memno);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/backend/lost/listAllLost.jsp";
					
//					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					System.out.println(e);
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		
		if ("delete".equals(action)) { // �Ӧ�listAllLost.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/lost/listAllLost.jsp�j ��  �i/listLost_ByMemno.jsp�j �� �i /listLost_ByState.jsp�j
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer gbno = new Integer(req.getParameter("gbno"));
				
				/***************************2.�}�l�R�����***************************************/
				GbService gbSvc = new GbService();
				GbVO gbVO = gbSvc.getOneGb(gbno);
				gbSvc.deleteGb(gbno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
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
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/gb/listGb_Bymemno.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listGbs_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
//			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				/***************************2.�}�l�ƦX�d��***************************************/
				GbService gbSvc = new GbService();
				List<GbVO> list  = gbSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listGb_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/backend/gb/listGbs_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/gb/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}		
		
	}

}
