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
		
		if("getOne_For_Display".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("memno");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer memno = null;
				try{
					memno = new Integer(str);
				}catch (Exception e){
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memno);
				if(memVO == null){
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/backend/mem/listOneMem.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // ���\��� listOneMem.jsp
				successView.forward(req, res);
			
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("listMem_ByState".equals(action)){// �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemService memSvc = new MemService();
				Set<MemVO> set = memSvc.listMem_ByState(memstate);
				
				if(set.size()==0){
//				if(list.isEmpty()){list���|�Onull ���i��list==null ���isEmpty��size��length
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				HttpSession session = req.getSession();
//				session.setAttribute("listMem_ByState", list);
				
				req.setAttribute("listMem_ByState", set); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/backend/mem/listMem_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listMems_ByState.jsp
				successView.forward(req, res);
			
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMem.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/mem/listAllMem.jsp�j
			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMem.jsp)
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer memno = new Integer(req.getParameter("memno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/backend/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_mem_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|���i/mem/listAllMem.jsp�j
			//String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:listAllMem.jsp)
			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer memno = new Integer(req.getParameter("memno").trim());
				String memid = req.getParameter("memid").trim();
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.add("�K�X���i���ť�");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.add("�m�W���i���ť�");
				}
				
				String memidno = req.getParameter("memidno").trim();
				
				String mememail = req.getParameter("mememail").trim();
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.add("�H�c���i���ť�");
				}
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.add("�a�}���i���ť�");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.add("�п�J�q�ܸ��X");
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
					req.setAttribute("memVO", memVO); // �t����J�榡���~��memVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memno, memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈwupdate���\��,���T����memVO����,�s�Jreq
				
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
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addMem.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String memid = req.getParameter("memid").trim();
				if (memid == null || (memid.trim()).length() == 0) {
					errorMsgs.add("�п�J�b��");
				}
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.add("�п�J�m�W");
				}
				
				String memidno = req.getParameter("memidno").trim();
				if (memidno == null || (memidno.trim()).length() == 0) {
					errorMsgs.add("�п�J�����Ҧr��");
				}
				
				String mememail = req.getParameter("mememail").trim();
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.add("�п�Je-mail");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.add("�п�J�a�}");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��");
				}
				
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
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
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
						req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt2.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					MemService memSvc = new MemService();
					memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
					memVO = new MemDAO().findByMemid(memid);
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					req.setAttribute("memNoFromAuto", memVO.getMemno() );
					String url = "/backend/pet/addPetForApt.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
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
