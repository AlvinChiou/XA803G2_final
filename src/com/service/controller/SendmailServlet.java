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

			// �ھڶǤJ�q��s���d�߭q��P��|��
			String ordno = request.getParameter("ordno");
			Integer ordstate = new Integer(request.getParameter("ordstate"));
			OrderService orderService = new OrderService();
			OrderVO orderVO = orderService.getOneOrder(ordno);
			Integer memno = new Integer(orderVO.getMemno()); // ���X�|���s��
			Timestamp ordtime = orderVO.getOrdtime();

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memno);

			String mememail = memVO.getMememail(); // ���X�ӷ|����Email
			System.out.println("mememail=" + mememail);
			String memname = memVO.getMemname(); // ���X�ӷ|�����m�W

			OrderService ordSvc = new OrderService();
			// OrderVO orderVO = ordSvc.getOneOrder(ordno);
			// OrderService orderService = new OrderService();
			// ���X�q��Ӷ��H�Ϊ��B
			Set<ProdItemVO> set = orderService.getProdItemByOrdno(ordno);// �ǤJ�q��s���A���A��String
			int total = 0;
			for (ProdItemVO prodItemVO : set) {
				int price = prodItemVO.getPrice();
				int itemqty = (prodItemVO.getItemqty());
				total += (price * itemqty);
			}

			String amount = String.valueOf(total);

			// �H�U�ק�q�檬�A
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
			String Subject = "�iiPetCare�I�ڳq���j�z���q��(�s��:" + ordno + ")�w�g���z�A�кɳtú��";
			String messageText = "�˷R��" + ch_name
					+ " �z�n!\n�z���q��w�g���ߡA�H�U���z���q���T:\n" + "�q�ʮɶ�:" + ordtime + "\n"
					+ "�q��s��:" + ordno + "\n" + "�`���B:" + amount + "\n"
					+ "ú�ڱb��:70000415890432512";
			
			//�I�scom.utilities.SmtpMailSender�o�e�H��
			SmtpMailSender sendMail = new SmtpMailSender();
			sendMail.Sendmail(ch_name, mailserver, From, to, Subject,
					messageText);
			
			//�e�H�����e�����
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
			String Subject = "�iiPetCare�q�ʦ��\�j���±z���f�U�I";
			String messageText = "�˷R��" + ch_name
					+ " �z�n!\n�ڭ̤w�g����z���q�ʻݨD�A�ڭ̱N�H�̧֪��t�׳B�̱z���q��C\n"
					+ "�q���@���鰷�d�A�ƨƶ��ߡI";			
			//�I�scom.utilities.SmtpMailSender�o�e�H��
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
