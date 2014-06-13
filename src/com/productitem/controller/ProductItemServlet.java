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
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String ordno = new String(request.getParameter("ordno"));
				/*************************** 2.�}�l�d�߸�� ****************************************/
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.getOneOrder(ordno);
				
				OrderService orderService = new OrderService();
				Set<ProdItemVO> set = orderService.getProdItemByOrdno(ordno);// �ǤJ�q��s���A���A��String				
				int total = 0;
				for(ProdItemVO prodItemVO : set){
					int price = prodItemVO.getPrice();
					int itemqty = (prodItemVO.getItemqty());
					total += (price * itemqty);
				}
				
				String amount = String.valueOf(total);
				request.setAttribute("amount", amount);
				request.setAttribute("listOrderItemsByOrdNo", set);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				request.setAttribute("orderVO", orderVO);
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("listOrderItems".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String prono = request.getParameter("ordno");
				System.out.println("prono="+prono);
				if (prono == null || (prono.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}				
				/***************************2.�}�l�d�߸��*****************************************/
				ProdItemService proItemService = new ProdItemService();
				ProdItemVO proItemVO = proItemService.getOnProdItemVO(prono);		
				System.out.println("proItemVO="+proItemVO);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				request.setAttribute("proItemVO", proItemVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(request, response);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("listOrderItems�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listOrderItems.jsp");
				failureView.forward(request, response);
			}
		}
		if ("listOrderItemsByOrdNo".equals(action)) {
			// �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.�����ШD�ѼơA��J�榡���~�B�z
				String str = request.getParameter("ordno");
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("�п�J�q��s��");
				}
				// �p�G�����~�o�͡A�h�N���~�T���e����
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
					failureView.forward(request, response);
					return; // �]�o�Ϳ��~�N���~�T���e����A�ä��_�{��
				}

				Long ordno = null;
				try {
					ordno = new Long(str);
				} catch (Exception e) {
					errorMsgs.add("�q��s���������Ʀr");
				}
				// �p�G�����~�o�͡A�h�N���~�T���e����
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
					failureView.forward(request, response);
					return; // �]�o�Ϳ��~�N���~�T���e����A�ä��_�{��
				}

				// �}�l�d�߸��

				OrderService orderService = new OrderService();
				Set<ProdItemVO> set = orderService.getProdItemByOrdno(str);// �ǤJ�q��s���A���A��String				
				int total = 0;
				for(ProdItemVO prodItemVO : set){
					int price = prodItemVO.getPrice();
					int itemqty = (prodItemVO.getItemqty());
					total += (price * itemqty);
				}
				
				String amount = String.valueOf(total);
				request.setAttribute("amount", amount);
				request.setAttribute("listOrderItemsByOrdNo", set);
				// �d�ߧ����A�ǳ�����Ƶ�view
				
				String url = "/backend/ORDER/listOrderItems.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				// �H�U�B�z��L�i����~
			} catch (Exception e) {
				errorMsgs.add("listOrderItemsByOrdNo�L�k���o���: " + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
