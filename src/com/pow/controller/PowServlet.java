package com.pow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;

import com.pow.model.PowService;
import com.pow.model.PowVO;

public class PowServlet extends HttpServlet {

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
		
		if("getOne_For_Display".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.getOnePow(empno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的powVO物件,存入req
				//req.setAttribute("empno", empno);
				
				String url = "/backend/pow/listOnePow.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url); // 成功轉交 listOnePow.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e){
				throw new ServletException(e);
			}
		}
		
		if("listPow_Byfuncname".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer funcno = new Integer(req.getParameter("funcno"));
				
				/***************************2.開始查詢資料****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.listPow_Byfuncname(funcno);
				if(list.isEmpty()){                                 //list不會是null 不可用list==null 改用isEmpty或size或length
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend//select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				
				String url = "/backend//pow/listPow_Byfuncname.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e){
				throw new ServletException(e);
			}
		}
		
		if("getOne_For_Update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try{
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer (req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				PowService powSvc = new PowService();
				List<PowVO> list = powSvc.getOnePow(empno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("list", list);
				String url = "/backend/pow/update_pow_input.jsp";
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【/pow/listAllPow.jsp】或【/pow/listOnePow.jsp】或【/pow/listPow_Byfuncname.jsp】
			
//			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				PowVO powVO = new PowVO();
				Integer empno = new Integer(req.getParameter("empno"));
				
				String[] funcnoFile = req.getParameterValues("funcno");
				if(funcnoFile == null || funcnoFile.length == 0){
					errorMsgs.add("權限至少要有查詢");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("powVO", powVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/pow/update_pow_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PowService powSvc = new PowService();
				powSvc.deletePow(empno);
				
				int[] data = new int[funcnoFile.length];
				Integer funcno = null;
				
				for(int i=0;i<funcnoFile.length;i++){
					int temp = Integer.parseInt(funcnoFile[i]);
//					data[i] += temp;
					funcno = temp;
				
				powVO.setEmpno(empno);
				powVO.setFuncno(funcno);
				
				powVO = powSvc.addPow(empno, funcno);
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("powVO", powVO);
				String url = requestURL;
				
				//再插入Session的list一次
				HttpSession session= req.getSession();
				List<PowVO> list = powSvc.getOnePow( (Integer)session.getAttribute("empNo") );                   //將權限存入SESSION
				session.setAttribute("list", list);       
				
				
				
				if(requestURL.equals("listAllPow.jsp")){
					req.setAttribute("listOnePow", powSvc.getOnePow(empno));
					url = requestURL;
				}else if(requestURL.equals("/backend/pow/listOnePow.jsp")){
					req.setAttribute("listOnePow", powSvc.getOnePow(empno));
					url = requestURL;
				}else if(requestURL.equals("/backend/listPow_Byfuncname.jsp")){
					req.setAttribute("listPow_Byfuncname", powSvc.listPow_Byfuncname(funcno));
					url = requestURL;
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
//			}catch (Exception e){
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/mem/update_mem_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}

}
