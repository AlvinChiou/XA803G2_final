package com.pet.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.apt.model.AptVO;
import com.drRecord.model.DrRecordVO;
import com.oreilly.servlet.MultipartRequest;
import com.pet.model.*;

public class PetServletFront extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// String action = req.getParameter("action");

		String contentType = req.getContentType();

		MultipartRequest multi = null;
		String action = null;
		
		if(contentType != null && contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(req, getServletContext().getRealPath("front/pet/photo"), 5*1024*1024, "UTF-8");
			action = multi.getParameter("action");
		}else{
			action = req.getParameter("action");
		}
		
//		if (contentType != null && req.getMethod().equals("GET")) {
//			if (contentType.startsWith("multipart/form-data")) {
//				multi = new MultipartRequest(req, getServletContext()
//						.getRealPath("front/pet/photo"), 5 * 1024 * 1024, "UTF-8");
//				action = multi.getParameter("action");
//			}
//		} else if (contentType != null && req.getMethod().equals("POST")) {
//			if (contentType.startsWith("multipart/form-data")) {
//				multi = new MultipartRequest(req, getServletContext()
//						.getRealPath("front/pet/photo"), 5 * 1024 * 1024, "UTF-8");
//				action = multi.getParameter("action");
//			} else {
//				action = req.getParameter("action");
//
//			}
//		}

		if ("getAll_For_Display_From_Member".equals(action) || "getAll_For_Display_From_MemberFromApt".equals(action) ) { // �Ӧ�select_page.jsp���ШD
//			System.out.println("hi, i'm in ");
//			System.out.println( req.getParameter("aptDate"));
//			System.out.println( req.getParameter("aptPeriod") );
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
//			String aptDate = req.getParameter("aptDate");
//			String aptPeriod = req.getParameter("aptPeriod");
//			System.out.println( "aptDate = " +  aptDate);
//			System.out.println( "aptPeriod = " +  aptPeriod);


			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL == null ) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/select_page.jsp");
					failureView.forward(req, res);
					} else if ( requestURL.equals( req.getContextPath() +  "/front/apt/addApt2.jsp") ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front/apt/addApt2.jsp");
						failureView.forward(req, res);
					}
					return;// �{�����_
					
				}

				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL == null ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front/select_page.jsp");
						failureView.forward(req, res);
						} else if ( requestURL.equals( req.getContextPath() +  "/front/apt/addApt2.jsp") ) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front/apt/addApt2.jsp");
							failureView.forward(req, res);
						}
						return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PetService petSvc = new PetService();
				List<PetVO> memPetVO = petSvc.getAllPetsFromMember(memNo);
				if (memPetVO==null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL.equals( req.getContextPath() + "/front/select_page.jsp")) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front/select_page.jsp");
						failureView.forward(req, res);
						} else if ( requestURL.equals(  req.getContextPath() +  "/front/apt/addApt2.jsp") ) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front/apt/addApt2.jsp");
							failureView.forward(req, res);
						}
						return;// �{�����_
				}
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("memPetVO", memPetVO); // ��Ʈw���X��empVO����,�s�Jreq
				req.setAttribute("memNo", memNo);
//				req.getSession().setAttribute("aptDate", aptDate);
//				req.getSession().setAttribute("aptPeriod", aptPeriod );

//				String url = "/front/pet/listMemAllPets.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				if ( requestURL.equals(req.getContextPath() + "/front/select_page.jsp" )) {
					RequestDispatcher successView = req.getRequestDispatcher("/front/pet/listMemAllPets.jsp");
					successView.forward(req, res);
				}else if ( requestURL.equals( req.getContextPath() +  "/front/apt/addApt2.jsp") ) {
					RequestDispatcher successView = req.getRequestDispatcher( "/front/apt/addApt2.jsp");
					successView.forward(req, res);
				}
//					return;// �{�����_

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				if ( requestURL == null ) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher( req.getContextPath() + "/front/pet/select_page.jsp");
//					failureView.forward(req, res);
//					} else if ( requestURL.equals( "/front/apt/addApt2.jsp") ) {
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/front/apt/addApt2.jsp");
//						failureView.forward(req, res);
//					}
//					return;// �{�����_
				e.printStackTrace();
			}
		}

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer petNo = null;
				try {
					petNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);
				if (petVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("petVO", petVO);
				req.setAttribute("petVO1", petVO);// ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/pet/update_pet_input.jsp";
				if ("/backend/apt/showApt.jsp".equals(requestURL)|| "/backend/apt/listApts_ByCompositeQuery.jsp".equals(requestURL)) {
					url = "/backend/pet/listOnePet.jsp";
				}else if("/front/apt/listApts_ByCompositeQuery.jsp".equals(requestURL)){
					url = "/front/pet/listOnePet.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				 errorMsgs.add("�L�k���o���:" + e.getMessage());
				 RequestDispatcher failureView = req.getRequestDispatcher("/pet/select_page.jsp");
				 failureView.forward(req, res);
				
			}
		}

		if ("getOne_For_Display_From_PetNo".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer petNo = null;
				try {
					petNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PetService petSvc = new PetService();
				Set<DrRecordVO> petVO = petSvc.getDrRecordsByPetNo(petNo);
				if (petVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("getRecordByPetNo", petVO);
				// req.setAttribute("memPetVO", memPetVO);// ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/pet/listAllRecordsByPetNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer petNo = new Integer(req.getParameter("petNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("petVO", petVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/pet/update_pet_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/pet/listMemAllPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer petNo = new Integer(multi.getParameter("petNo").trim());
				String petName = multi.getParameter("petName").trim();
				String petSex = multi.getParameter("petSex").trim();

				String petType = null;
				try {
					petType = multi.getParameter("petType").trim();
				} catch (Exception e) {
					petType = "";
					errorMsgs.add("�п�J����");
				}

				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);
				
				byte[] buffer = null;
				File file = multi.getFile("petPic");
				if(file == null){
					buffer=petVO.getPetPic();
				}else{
					FileInputStream fis = new FileInputStream(file);
					int len = fis.available();
					buffer = new byte[len];
						fis.read(buffer);
				}

				Double petAge = 0.0;
				try {
					petAge = new Double(multi.getParameter("petAge").trim());
				} catch (Exception e) {
					petAge = 0.0;
					errorMsgs.add("�п�J�~��");
				}

				Integer memNo = 0;
				try {
					memNo = new Integer(multi.getParameter("memNo").trim());
				} catch (Exception e) {
					memNo = 0;
					errorMsgs.add("�п�J���u�s��");
				}

				// Integer deptno = new
				// Integer(multi.getParameter("deptno").trim());

				PetVO vo2 = new PetVO();
				vo2.setPetNo(petNo);
				vo2.setPetName(petName);
				vo2.setPetSex(petSex);
				vo2.setPetType(petType);
				vo2.setPetPic(buffer);
				vo2.setPetAge(petAge);
				vo2.setMemNo(memNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petVO", vo2); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front/pet/update_pet_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				//PetService petSvc = new PetService();
				vo2 = petSvc.updatePet(petNo, petName, petSex, petType, buffer,	petAge, memNo);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("petVO", vo2);// ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				req.setAttribute("memPetVO", petSvc.getAllPetsFromMember(petVO.getMemNo()));// ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front/pet/listMemAllPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front/pet/update_pet_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				// Integer actNo = new
				// Integer(multi.getParameter("actNo").trim());
				String petName = multi.getParameter("petName").trim();
				String petNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";//�m�ߥ��h(�W)��ܦ�(regular-expression)
				if (petName == null || petName.trim().length() == 0) {
					errorMsgs.put("petName","�d���W�r: �ФŪť�");
				} else if(!petName.trim().matches(petNameReg)) { //�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("petName","�d���W�r: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String petSex = multi.getParameter("petSex").trim();

				String petType = multi.getParameter("petType").trim();
				
				byte[] buffer = null;
				File petPicFile = multi.getFile("petPic");
				if(petPicFile == null || petPicFile.length() == 0){
					errorMsgs.put("petPic","�ФW�ǷR�d�Ϥ�!!");
				}else{
				FileInputStream fis = new FileInputStream(petPicFile);
				int len = fis.available();
				buffer = new byte[len];
				fis.read(buffer);
				}
				

				String petAgeStr = multi.getParameter("petAge").trim();
				Double petAge=0.0;
				if( petAgeStr == null || petAgeStr.trim().length() == 0){
					errorMsgs.put("petAge","�п�J�d���~��");
				} else {
				      try {
				    	  petAge = new Double(petAgeStr.trim());
				      } catch (NumberFormatException e) {
				    	  petAge = 0.0;
					      errorMsgs.put("petAge","�d���~�֪�������ഫ���p���I");
				      }
				}

				String memNoStr = multi.getParameter("memNo").trim();
				Integer memNo = 0000;
				if(memNoStr == null || memNoStr.trim().length() == 0){
					errorMsgs.put("memNo","�п�J�|���s��");
				}else{
					try {
						memNo = new Integer(memNoStr.trim());
					} catch (NumberFormatException e) {
						
						errorMsgs.put("memNo","�d�L���H");
					}
				}
				
				PetVO vo3 = new PetVO();
				// vo3.setActNo(actNo);
				vo3.setPetName(petName);
				vo3.setPetSex(petSex);
				vo3.setPetType(petType);
				vo3.setPetPic(buffer);
				vo3.setPetAge(petAge);
				vo3.setMemNo(memNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petVO", vo3); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front/pet/addPet.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PetService petSvc = new PetService();
				petSvc.addPet(petName, petSex, petType, buffer, petAge,memNo);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("vo3", vo3);
				
				String url = "/front/pet/listMemAllPets.jsp";
				req.setAttribute("memPetVO", petSvc.getAllPetsFromMember(vo3.getMemNo()));
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
			/*	errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/addPet.jsp");
				failureView.forward(req, res);*/
				e.printStackTrace();
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer petNo = new Integer(req.getParameter("petNo"));

				/*************************** 2.�}�l�R����� ***************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);
				petSvc.deletePet(petNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front/pet/listMemAllPets.jsp";
//				if(requestURL.equals(req.getContextPath() + "/front/pet/listMemAllPets.jsp")){
					req.setAttribute("memPetVO", petSvc.getAllPetsFromMember(petVO.getMemNo()));
//					url = requestURL;
//				}
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/pet/listMemAllPets.jsp");
				failureView.forward(req, res);
			}
		}
		/* �ۤv�s�W�� */
		if ("listApts_ByPetNo_A".equals(action)
				|| "listApts_ByPetNo_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String petNo = req.getParameter("petNo");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PetService petSvc = new PetService();
				Set<AptVO> set = petSvc.getAptsByPetNo(petNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listApts_ByPetNo", set); // ��Ʈw���X��list����,�s�Jrequest

				String url = null;
				if ("listApts_ByPetNo_A".equals(action))
					url = "/pet/listApts_ByPetNo.jsp"; // ���\���
														// dept/listEmps_ByDeptno.jsp
				else if ("listApts_ByPetNo_B".equals(action))
					url = "/apt/showApt.jsp"; // ���\���apt/showApt.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
