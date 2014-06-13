package com.pet.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.apt.model.AptVO;
import com.drRecord.model.DrRecordVO;
import com.oreilly.servlet.MultipartRequest;
import com.pet.model.*;

public class PetServlet extends HttpServlet {

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
		if (contentType != null && req.getMethod().equals("GET")) {
			if (contentType.startsWith("multipart/form-data")) {
				multi = new MultipartRequest(req, getServletContext()
						.getRealPath("backend/pet/photo"), 5 * 1024 * 1024, "UTF-8");
				action = multi.getParameter("action");
			}
		} else if (contentType != null && req.getMethod().equals("POST")) {
			if (contentType.startsWith("multipart/form-data")) {
				multi = new MultipartRequest(req, getServletContext()
						.getRealPath("backend/pet/photo"), 5 * 1024 * 1024, "UTF-8");
				action = multi.getParameter("action");
			} else {
				action = req.getParameter("action");

			}
		}

		if ("getAll_For_Display_From_Member".equals(action) || "getAll_For_Display_From_MemberFromApt".equals(action) ) { // 來自select_page.jsp的請求
//			System.out.println( req.getParameter("aptDate"));
//			System.out.println( req.getParameter("aptPeriod") );
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
//			String aptDate = req.getParameter("aptDate");
//			
//			String aptPeriod = req.getParameter("aptPeriod");
//			
//			System.out.println( "aptDate = " +  aptDate);
//			System.out.println( "aptPeriod = " +  aptPeriod);


			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL == null ) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					} else if ( requestURL.equals( req.getContextPath() +  "/backend/apt/addApt2.jsp") ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt2.jsp");
						failureView.forward(req, res);
					}
					return;// 程式中斷
					
				}

				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL == null ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/pet/select_page.jsp");
						failureView.forward(req, res);
						} else if ( requestURL.equals( req.getContextPath() +  "/backend/apt/addApt2.jsp") ) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/apt/addApt2.jsp");
							failureView.forward(req, res);
						}
						return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PetService petSvc = new PetService();
				List<PetVO> memPetVO = petSvc.getAllPetsFromMember(memNo);
				if (memPetVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ( requestURL == null ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/pet/select_page.jsp");
						failureView.forward(req, res);
						} else if ( requestURL.equals(  req.getContextPath() +  "/backend/apt/addApt2.jsp") ) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/apt/addApt2.jsp");
							failureView.forward(req, res);
						}
						return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memPetVO", memPetVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("memNo", memNo);
//				req.getSession().setAttribute("aptDate", aptDate);
//				req.getSession().setAttribute("aptPeriod", aptPeriod );

				String url = "/backend/drRecord/select_page.jsp";
				if ( requestURL == null ) {
					RequestDispatcher successView = req
							.getRequestDispatcher(url);
					successView.forward(req, res);
					} else if ( requestURL.equals( req.getContextPath() +  "/backend/apt/addApt2.jsp") ) {
						RequestDispatcher successView = req
								.getRequestDispatcher( "/backend/apt/addApt2.jsp");
						successView.forward(req, res);
					}
					return;// 程式中斷

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				if ( requestURL == null ) {
					RequestDispatcher failureView = req
							.getRequestDispatcher( req.getContextPath() + "/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					} else if ( requestURL.equals( "/backend/apt/addApt2.jsp") ) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/apt/addApt2.jsp");
						failureView.forward(req, res);
					}
					return;// 程式中斷
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入寵物編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer petNo = null;
				try {
					petNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("寵物編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);
				if (petVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("petVO", petVO);
				req.setAttribute("petVO1", petVO);// 資料庫取出的empVO物件,存入req
				String url = "/backend/pet/update_pet_input.jsp";
				if ("/backend/apt/showApt.jsp".equals(requestURL)
						|| "/backend/apt/listApts_ByCompositeQuery.jsp"
								.equals(requestURL)) {
					url = "/backend/pet/listOnePet.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				 errorMsgs.add("無法取得資料:" + e.getMessage());
				 RequestDispatcher failureView = req
				 .getRequestDispatcher("/pet/select_page.jsp");
				 failureView.forward(req, res);
				
			}
		}

		if ("getOne_For_Display_From_PetNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入寵物編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer petNo = null;
				try {
					petNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("寵物編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PetService petSvc = new PetService();
				Set<DrRecordVO> petVO = petSvc.getDrRecordsByPetNo(petNo);
				if (petVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("getRecordByPetNo", petVO);
				// req.setAttribute("memPetVO", memPetVO);// 資料庫取出的empVO物件,存入req
				String url = "/backend/drRecord/listAllRecordsByPetNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer petNo = new Integer(req.getParameter("petNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("petVO", petVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/pet/update_pet_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/listAllPet.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer petNo = new Integer(req.getParameter("petNo").trim());
				String petName = req.getParameter("petName").trim();
				String petSex = req.getParameter("petSex").trim();

				String petType = null;
				try {
					petType = req.getParameter("petType").trim();
				} catch (Exception e) {
					petType = "";
					errorMsgs.add("請輸入種類");
				}

				byte[] petPic = { 0 };
				// try {
				// petPic = new Double(req.getParameter("petPic").trim());
				// } catch (NumberFormatException e) {
				// petPic = 0.0;
				// errorMsgs.add(".);
				// }

				Double petAge = 0.0;
				try {
					petAge = new Double(req.getParameter("petAge").trim());
				} catch (Exception e) {
					petAge = 0.0;
					errorMsgs.add("請輸入年齡");
				}

				Integer memNo = 0;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
				} catch (Exception e) {
					memNo = 0;
					errorMsgs.add("請輸入員工編號");
				}

				// Integer deptno = new
				// Integer(req.getParameter("deptno").trim());

				PetVO vo2 = new PetVO();
				vo2.setPetNo(petNo);
				vo2.setPetName(petName);
				vo2.setPetSex(petSex);
				vo2.setPetType(petType);
				vo2.setPetPic(petPic);
				vo2.setPetAge(petAge);
				vo2.setMemNo(memNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petVO", vo2); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/update_pet_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PetService petSvc = new PetService();
				vo2 = petSvc.updatePet(petNo, petName, petSex, petType, petPic,
						petAge, memNo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("petVO", vo2);// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/pet/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/update_pet_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// Integer actNo = new
				// Integer(multi.getParameter("actNo").trim());
				String petName = multi.getParameter("petName").trim();
				String petNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";//練習正則(規)表示式(regular-expression)
				if (petName == null || petName.trim().length() == 0) {
					errorMsgs.put("petName","寵物名字: 請勿空白");
				} else if(!petName.trim().matches(petNameReg)) { //練習正則(規)表示式(regular-expression)
					errorMsgs.put("petName","寵物名字: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String petSex = multi.getParameter("petSex").trim();

				String petType = multi.getParameter("petType").trim();
				
				byte[] buffer = null;
				File petPicFile = multi.getFile("petPic");
				if(petPicFile == null || petPicFile.length() == 0){
					errorMsgs.put("petPic","請上傳愛寵圖片!!");
				}else{
				FileInputStream fis = new FileInputStream(petPicFile);
				int len = fis.available();
				buffer = new byte[len];
				fis.read(buffer);
				}
				

				String petAgeStr = multi.getParameter("petAge").trim();
				Double petAge=0.0;
				if( petAgeStr == null || petAgeStr.trim().length() == 0){
					errorMsgs.put("petAge","請輸入寵物年齡");
				} else {
				      try {
				    	  petAge = new Double(petAgeStr.trim());
				      } catch (NumberFormatException e) {
				    	  petAge = 0.0;
					      errorMsgs.put("petAge","寵物年齡的月份請轉換成小數點");
				      }
				}

				String memNoStr = multi.getParameter("memNo").trim();
				Integer memNo = 0000;
				if(memNoStr == null || memNoStr.trim().length() == 0){
					errorMsgs.put("memNo","請輸入會員編號");
				}else{
					try {
						memNo = new Integer(memNoStr.trim());
					} catch (NumberFormatException e) {
						
						errorMsgs.put("memNo","查無此人");
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
					req.setAttribute("petVO", vo3); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pet/addPet.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PetService petSvc = new PetService();
				petSvc.addPet(petName, petSex, petType, buffer, petAge,memNo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/pet/listAllPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
			/*	errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/pet/addPet.jsp");
				failureView.forward(req, res);*/
				e.printStackTrace();
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer petNo = new Integer(req.getParameter("petNo"));

				/*************************** 2.開始刪除資料 ***************************************/
				PetService petSvc = new PetService();
				petSvc.deletePet(petNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/pet/listAllPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pet/listAllPet.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listPets_ByCompositeQueryForBack".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
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
				PetService petSvc = new PetService();
				List<PetVO> list  = petSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPets_ByCompositeQuery", list); // from select to listQuery
				RequestDispatcher successView = req.getRequestDispatcher("/backend/pet/listPetsByCompositeQuery.jsp"); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/act/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
		/* 自己新增的 */
		if ("listApts_ByPetNo_A".equals(action)
				|| "listApts_ByPetNo_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String petNo = req.getParameter("petNo");

				/*************************** 2.開始查詢資料 ****************************************/
				PetService petSvc = new PetService();
				Set<AptVO> set = petSvc.getAptsByPetNo(petNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listApts_ByPetNo", set); // 資料庫取出的list物件,存入request

				String url = null;
				if ("listApts_ByPetNo_A".equals(action))
					url = "/pet/listApts_ByPetNo.jsp"; // 成功轉交
														// dept/listEmps_ByDeptno.jsp
				else if ("listApts_ByPetNo_B".equals(action))
					url = "/apt/showApt.jsp"; // 成功轉交apt/showApt.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
