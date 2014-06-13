package com.service.controller;

import java.sql.Timestamp;
import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.productitem.model.ProdItemVO;
import com.utilities.*;

public class SendmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("notification".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// 根據傳入訂單編號查詢訂單與其會員
			String ordno = request.getParameter("ordno");
			Integer ordstate = new Integer(request.getParameter("ordstate"));
			OrderService orderService = new OrderService();
			OrderVO orderVO = orderService.getOneOrder(ordno);
			Integer memno = new Integer(orderVO.getMemno()); // 取出會員編號
			Timestamp ordtime = orderVO.getOrdtime();

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memno);

			String mememail = memVO.getMememail(); // 取出該會員的Email
			System.out.println("mememail=" + mememail);
			String memname = memVO.getMemname(); // 取出該會員的姓名

			OrderService ordSvc = new OrderService();
			// OrderVO orderVO = ordSvc.getOneOrder(ordno);
			// OrderService orderService = new OrderService();
			// 取出訂單細項以及金額
			Set<ProdItemVO> set = orderService.getProdItemByOrdno(ordno);// 傳入訂單編號，型態為String
			int total = 0;
			for (ProdItemVO prodItemVO : set) {
				int price = prodItemVO.getPrice();
				int itemqty = (prodItemVO.getItemqty());
				total += (price * itemqty);
			}

			String amount = String.valueOf(total);

			// 以下修改訂單狀態
			OrderService ordService = new OrderService();
			OrderVO ordVo = ordService.updateStatus(ordno, ordstate);
			ordVo.setOrdno(ordno);
			ordVo.setOrdstate(ordstate);
			ordVo = orderService.updateStatus(ordno, ordstate);

			String ch_name = memname; // String ch_name =
			// request.getParameter("ch_name");
			String mailserver = getServletContext().getInitParameter(
					"MailServerAddress");
			String From = getServletContext().getInitParameter("fromMail");
			System.out.println("From=" + From);
			// String password = getInitParameter("Password");
			String to = mememail; // String to = request.getParameter("email");
			String Subject = "【iPetCare付款通知】您的訂單(編號:" + ordno + ")已經受理，請盡速繳款";
			String messageText = "親愛的" + ch_name
					+ " 您好!\n您的訂單已經成立，以下為您的訂單資訊:\n" + "訂購時間:" + ordtime + "\n"
					+ "訂單編號:" + ordno + "\n" + "總金額:" + amount + "\n"
					+ "繳款帳號:70000415890432512";
			
			//呼叫com.utilities.SmtpMailSender發送信件
			SmtpMailSender sendMail = new SmtpMailSender();
			sendMail.Sendmail(ch_name, mailserver, From, to, Subject,
					messageText);
			
			//送信完畢畫面轉交
			String url = "/backend/ORDER/listAllOrder.jsp";
			RequestDispatcher successView = request
					.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
		if("COMPLETE".equals(action)){

			String ch_name = request.getAttribute("memname").toString(); // String ch_name =
			// request.getParameter("ch_name");
			String mailserver = getServletContext().getInitParameter(
					"MailServerAddress");
			String From = getServletContext().getInitParameter("fromMail");
			System.out.println("From=" + From);
			// String password = getInitParameter("Password");
			String to = request.getAttribute("mememail").toString(); // String to = request.getParameter("email");
			String Subject = "【iPetCare訂購成功】謝謝您的惠顧！";
			String messageText = "親愛的" + ch_name
					+ " 您好!\n我們已經收到您的訂購需求，我們將以最快的速度處裡您的訂單。\n"
					+ "敬祝　身體健康，事事順心！";			
			//呼叫com.utilities.SmtpMailSender發送信件
			SmtpMailSender sendMail = new SmtpMailSender();
			System.out.println("V1_ch_name="+ch_name);
			System.out.println("V1_mailserver="+mailserver);
			System.out.println("V1_From="+From);
			System.out.println("V1_to="+to);
			System.out.println("V1_Subject="+Subject);
			System.out.println("V1_messageText="+messageText);
			sendMail.Sendmail(ch_name, mailserver, From, to, Subject,
					messageText);
			String url = "/frontend/ORDERITEM/OrderProcessResult.jsp";
			RequestDispatcher successView = request
					.getRequestDispatcher(url);
			successView.forward(request, response);
		}
	}

}
