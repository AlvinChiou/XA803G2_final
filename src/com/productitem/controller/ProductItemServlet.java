package com.productitem.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.productitem.model.ProdItemService;
import com.productitem.model.ProdItemVO;

public class ProductItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("getone_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ordno = new String(request.getParameter("ordno"));
				/*************************** 2.開始查詢資料 ****************************************/
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.getOneOrder(ordno);
				
				OrderService orderService = new OrderService();
				Set<ProdItemVO> set = orderService.getProdItemByOrdno(ordno);// 傳入訂單編號，型態為String				
				int total = 0;
				for(ProdItemVO prodItemVO : set){
					int price = prodItemVO.getPrice();
					int itemqty = (prodItemVO.getItemqty());
					total += (price * itemqty);
				}
				
				String amount = String.valueOf(total);
				request.setAttribute("amount", amount);
				request.setAttribute("listOrderItemsByOrdNo", set);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("orderVO", orderVO);
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("listOrderItems".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prono = request.getParameter("ordno");
				System.out.println("prono="+prono);
				if (prono == null || (prono.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}				
				/***************************2.開始查詢資料*****************************************/
				ProdItemService proItemService = new ProdItemService();
				ProdItemVO proItemVO = proItemService.getOnProdItemVO(prono);		
				System.out.println("proItemVO="+proItemVO);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("proItemVO", proItemVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("listOrderItems無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listOrderItems.jsp");
				failureView.forward(request, response);
			}
		}
		if ("listOrderItemsByOrdNo".equals(action)) {
			// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數，輸入格式錯誤處理
				String str = request.getParameter("ordno");
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入訂單編號");
				}
				// 如果有錯誤發生，則將錯誤訊息送到表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
					failureView.forward(request, response);
					return; // 因發生錯誤將錯誤訊息送到表單，並中斷程式
				}

				Long ordno = null;
				try {
					ordno = new Long(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號必須為數字");
				}
				// 如果有錯誤發生，則將錯誤訊息送到表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
					failureView.forward(request, response);
					return; // 因發生錯誤將錯誤訊息送到表單，並中斷程式
				}

				// 開始查詢資料

				OrderService orderService = new OrderService();
				Set<ProdItemVO> set = orderService.getProdItemByOrdno(str);// 傳入訂單編號，型態為String				
				int total = 0;
				for(ProdItemVO prodItemVO : set){
					int price = prodItemVO.getPrice();
					int itemqty = (prodItemVO.getItemqty());
					total += (price * itemqty);
				}
				
				String amount = String.valueOf(total);
				request.setAttribute("amount", amount);
				request.setAttribute("listOrderItemsByOrdNo", set);
				// 查詢完成，準備轉交資料給view
				
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				// 以下處理其他可能錯誤
			} catch (Exception e) {
				errorMsgs.add("listOrderItemsByOrdNo無法取得資料: " + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
