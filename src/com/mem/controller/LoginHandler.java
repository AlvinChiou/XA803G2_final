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
		
		// 【取得使用者 帳號(account) 密碼(password)】
		String memid = req.getParameter("memid");
		String mempassword = req.getParameter("mempassword");
		
		// 【檢查該帳號 , 密碼是否有效】
		if(!allowuser(memid, mempassword)){  //帳號 , 密碼無效時

			if(memVO==null){
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>無效的使用者帳戶!<BR>");
				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/memLogin.jsp>重新登入</A>");
				out.println("</BODY></HTML>");
				res.setHeader("Refresh", "1;URL="+req.getContextPath()+"/memLogin.jsp");
			}else{
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			    out.println("<BODY>帳號密碼錯誤 請重新輸入!<BR>");
				out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/memLogin.jsp>重新登入</A>");
			    out.println("</BODY></HTML>");
			    res.setHeader("Refresh", "1;URL="+req.getContextPath()+"/memLogin.jsp");
			}
		}else{                              //帳號 密碼有效時, 才做以下工作
			HttpSession session = req.getSession();
			session.setAttribute("memid", memid);           //*工作1: 才在session內做已經登入過的標識
			session.setAttribute("memVO", memVO);           //可以從裡面取出memName,顯示在首頁,XXX你好
			
			try{                                            //*工作2: 看看有無來源網頁 (-如有:則重導之)  
				String location = (String)session.getAttribute("location");
				if(location != null){
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch(Exception ignored){	}
			
			res.sendRedirect(req.getContextPath()+"/iPETCares.jsp");           //*工作3: (如無來源網頁, 則重導至select_page.jsp網頁)
		}
		
	}

}
