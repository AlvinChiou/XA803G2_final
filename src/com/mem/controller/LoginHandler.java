package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;

public class LoginHandler extends HttpServlet{
	
	MemVO memVO = null;

	protected boolean allowuser(String memid, String mempassword){
		
		MemService memSvc = new MemService();
		memVO = memSvc.getOneMem(memid);
		
		if(memVO==null){
			return false;
		}else if(!mempassword.equals(memVO.getMempassword())){
			return false;
		}else{
			return true;
		}
	}
	
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
		PrintWriter out = res.getWriter();
		
		// �i���o�ϥΪ� �b��(account) �K�X(password)�j
		String memid = req.getParameter("memid");
		String mempassword = req.getParameter("mempassword");
		
		// �i�ˬd�ӱb�� , �K�X�O�_���ġj
		if(!allowuser(memid, mempassword)){  //�b�� , �K�X�L�Į�

			if(memVO==null){
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>�L�Ī��ϥΪ̱b��!<BR>");
				out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/memLogin.jsp>���s�n�J</A>");
				out.println("</BODY></HTML>");
				res.setHeader("Refresh", "1;URL="+req.getContextPath()+"/memLogin.jsp");
			}else{
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			    out.println("<BODY>�b���K�X���~ �Э��s��J!<BR>");
				out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/memLogin.jsp>���s�n�J</A>");
			    out.println("</BODY></HTML>");
			    res.setHeader("Refresh", "1;URL="+req.getContextPath()+"/memLogin.jsp");
			}
		}else{                              //�b�� �K�X���Į�, �~���H�U�u�@
			HttpSession session = req.getSession();
			session.setAttribute("memid", memid);           //*�u�@1: �~�bsession�����w�g�n�J�L������
			session.setAttribute("memVO", memVO);           //�i�H�q�̭����XmemName,��ܦb����,XXX�A�n
			
			try{                                            //*�u�@2: �ݬݦ��L�ӷ����� (-�p��:�h���ɤ�)  
				String location = (String)session.getAttribute("location");
				if(location != null){
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch(Exception ignored){	}
			
			res.sendRedirect(req.getContextPath()+"/iPETCares.jsp");           //*�u�@3: (�p�L�ӷ�����, �h���ɦ�select_page.jsp����)
		}
		
	}

}
