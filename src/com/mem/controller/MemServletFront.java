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
							.getRequestDispatcher("/front/select_page.jsp");
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
							.getRequestDispatcher("/front/select_page.jsp");
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
						.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/front/mem/listOneMem.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // ���\��� listOneMem.jsp
				successView.forward(req, res);
			
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
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
						.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				HttpSession session = req.getSession();
//				session.setAttribute("listMem_ByState", list);
				
				req.setAttribute("listMem_ByState", set); // ��Ʈw���X��memVO����,�s�Jreq
				String url = "/front/mem/listMem_ByState.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listMems_ByState.jsp
				successView.forward(req, res);
			
				/***************************��L�i�઺���~�B�z*************************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/select_page.jsp");
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
				String url = "/front/mem/update_mem_input.jsp";
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
				
				String checkpassword = req.getParameter("checkpassword").trim();
				if(!checkpassword.equals(mempassword)){
					errorMsgs.add("�K�X�e�ᤣ�@�P�A�Ч�");
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
							.getRequestDispatcher("/front/mem/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memno, memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // ��Ʈwupdate���\��,���T����memVO����,�s�Jreq
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
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMem.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addMem.jsp���ШD  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String memid = req.getParameter("memid").trim();
				String memReg = "^[(a-zA-Z0-9)]{6,15}$";
				if (memid == null || (memid.trim()).length() == 0) {
					errorMsgs.put("memid", "�п�J�b��");
				}else if(!memid.trim().matches(memReg)){
					errorMsgs.put("memid", "�b���u��O�^��r���B�Ʀr , �B���ץ��ݦb6��15����");
				}
				
				String mempassword = req.getParameter("mempassword").trim();
				if (mempassword == null || (mempassword.trim()).length() == 0) {
					errorMsgs.put("mempassword", "�п�J�K�X");
				}else if(!mempassword.trim().matches(memReg)){
					errorMsgs.put("mempassword", "�K�X�u��O�^��r���B�Ʀr , �B���ץ��ݦb6��15����");
				}
				
				String checkpassword = req.getParameter("checkpassword").trim();
				
				if(!checkpassword.equals(mempassword)){
					errorMsgs.put("mempassword", "�K�X�e�ᤣ�@�P�A�Ч�");
				}
				
				String memname = req.getParameter("memname").trim();
				if (memname == null || (memname.trim()).length() == 0) {
					errorMsgs.put("memname", "�п�J�m�W");
				}
				
				String memidno = req.getParameter("memidno").trim();
				String idnoReg = "^[A-Z]{1}[0-9]{9}$";
				if (memidno == null || (memidno.trim()).length() == 0) {
					errorMsgs.put("memidno", "�п�J�����Ҧr��");
				}else if(!memidno.trim().matches(idnoReg)){
					errorMsgs.put("memidno", "�����Ҧr���榡���~");
				}
				
				String mememail = req.getParameter("mememail").trim();
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (mememail == null || (mememail.trim()).length() == 0) {
					errorMsgs.put("mememail", "�п�Je-mail");
				}else if(!mememail.trim().matches(emailReg)){
					errorMsgs.put("mememail", "e-mail�榡���~");
				}
				
				String memadd = req.getParameter("memadd").trim();
				if (memadd == null || (memadd.trim()).length() == 0) {
					errorMsgs.put("memadd", "�п�J�a�}");
				}
				
				Integer memsex = new Integer(req.getParameter("memsex"));
				
				String memtel = req.getParameter("memtel").trim();
				String telReg = "^09[0-9]{8}$";
				if (memtel == null || (memtel.trim()).length() == 0) {
					errorMsgs.put("memtel", "�п�J�q��");
				}else if(!memtel.trim().matches(telReg)){
					errorMsgs.put("memtel", "������X�榡���~");
				}
				
				Integer memstate = new Integer(req.getParameter("memstate"));
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("membirth", "�п�J���!");
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
							.getRequestDispatcher("/front/mem/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("memVO", memVO);
				String url2 = "/front/mem/JavaMailProccess.jsp";
				RequestDispatcher sendMail = req.getRequestDispatcher(url2);
				sendMail.include(req, res);
				
				String url = "/afterMemCreate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		  
	}
}
