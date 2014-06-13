package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmployeeService;
import com.emp.model.EmployeeVO;
import com.pow.model.PowDAO;
import com.pow.model.PowService;
import com.pow.model.PowVO;

public class LoginHandler extends HttpServlet {

	EmployeeVO employeeVO = null;
	
	protected boolean allowuser(Integer empNo, String empPassword){
		
		EmployeeService employeeSvc = new EmployeeService();
		employeeVO = employeeSvc.getOneEmp(empNo);
		
		if(employeeVO==null){
			return false;
		}else if(!empPassword.equals(employeeVO.getEmpPassword())){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req,res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		
		// �i���o�ϥΪ� �b��(empNo) �K�X(empPassword)�j
		Integer empNo = new Integer(req.getParameter("empNo"));
		String empPassword = req.getParameter("empPassword");
		
		// �i�ˬd�ӱb�� , �K�X�O�_���ġj
		if(!allowuser(empNo, empPassword)){  //�b�� , �K�X�L�Į�
			
			if(employeeVO==null){
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>�L�Ī��ϥΪ̱b��!<BR>");
				out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/empLogin.jsp>���s�n�J</A>");
				out.println("</BODY></HTML>");
			}else{
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			    out.println("<BODY>�b���K�X���~ �Э��s��J!<BR>");
				out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/empLogin.jsp>���s�n�J</A>");
			    out.println("</BODY></HTML>");
			}
			
		}else{                               //�b�� �K�X���Į�, �~���H�U�u�@
			HttpSession session = req.getSession();
			session.setAttribute("empNo", empNo);                         //*�u�@1: �~�bsession�����w�g�n�J�L������
			session.setAttribute("employeeVO", employeeVO);               //�i�H�q�̭����XmemName,��ܦb����,XXX�A�n
			
			PowService powSvc = new PowService();
			List<PowVO> list = powSvc.getOnePow(empNo);                   //�N�v���s�JSESSION
			
			session.setAttribute("list", list);                           //�N���쪺�v���s�Jsession
			
			try{                                                          //*�u�@2: �ݬݦ��L�ӷ����� (-�p��:�h���ɤ�)  
				String location = (String)session.getAttribute("location");
				if(location != null){
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch (Exception ignored){	}
			
			res.sendRedirect(req.getContextPath()+"/index.jsp");    //*�u�@3: (�p�L�ӷ�����, �h���ɦ�select_page.jsp����)
		}
		
	}
	
}
