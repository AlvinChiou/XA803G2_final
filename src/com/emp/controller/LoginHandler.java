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
		
		// 【取得使用者 帳號(empNo) 密碼(empPassword)】
		Integer empNo = new Integer(req.getParameter("empNo"));
		String empPassword = req.getParameter("empPassword");
		
		// 【檢查該帳號 , 密碼是否有效】
		if(!allowuser(empNo, empPassword)){  //帳號 , 密碼無效時
			
			if(employeeVO==null){
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>無效的使用者帳戶!<BR>");
				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/empLogin.jsp>重新登入</A>");
				out.println("</BODY></HTML>");
			}else{
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			    out.println("<BODY>帳號密碼錯誤 請重新輸入!<BR>");
				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/empLogin.jsp>重新登入</A>");
			    out.println("</BODY></HTML>");
			}
			
		}else{                               //帳號 密碼有效時, 才做以下工作
			HttpSession session = req.getSession();
			session.setAttribute("empNo", empNo);                         //*工作1: 才在session內做已經登入過的標識
			session.setAttribute("employeeVO", employeeVO);               //可以從裡面取出memName,顯示在首頁,XXX你好
			
			PowService powSvc = new PowService();
			List<PowVO> list = powSvc.getOnePow(empNo);                   //將權限存入SESSION
			
			session.setAttribute("list", list);                           //將取到的權限存入session
			
			try{                                                          //*工作2: 看看有無來源網頁 (-如有:則重導之)  
				String location = (String)session.getAttribute("location");
				if(location != null){
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch (Exception ignored){	}
			
			res.sendRedirect(req.getContextPath()+"/index.jsp");    //*工作3: (如無來源網頁, 則重導至select_page.jsp網頁)
		}
		
	}
	
}
